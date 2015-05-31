package be.fedasil.matchit.backend.service.elasticsearch;

import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.process.indexer.RoomIndexer;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.RecoverableIndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.UnrecoverableIndexerException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author gvginder
 */
public class ElasticRoomIndexer implements RoomIndexer {
	private static final MatchitLogger LOGGER = MatchitLoggerFactory
			.getLogger(ElasticRoomIndexer.class);

	private final Client client;
	private final String indexName;
	private final String typeName;

	public ElasticRoomIndexer(Client client) {
		this(client, "fedasil", "room");
	}

	public ElasticRoomIndexer(Client client, String indexName, String typeName) {
		this.client = client;
		this.indexName = indexName;
		this.typeName = typeName;
	}

	public void initIndex() {
		if (getIndexExists()) {
			CreateIndexResponse response = client.admin()
					.indices()
					.create(new CreateIndexRequest(this.indexName))
					.actionGet();
		}
	}

	public GetResponse getRoomIndexById(long roomId) {
		String esId = createIndexId(roomId);
		GetResponse response = client.prepareGet(this.indexName, this.typeName, esId)
				.execute()
				.actionGet();
		return response;
	}

	@Override
	public void createRoomIndex(ReceptionCenter center, Location
			location, Room room) throws IndexerException {
		XContentBuilder builder;
		try {
			builder = jsonBuilder();
			buildIndex(builder, center, location, room);
		} catch (IOException exception) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException(exception);
		}

		String esId = createIndexId(room.getRoomId());
		try {
			IndexResponse response = client.prepareIndex(this.indexName, this.typeName, esId)
					.setSource(builder)
					.execute()
					.actionGet();
		} catch (ElasticsearchException exception) {
			throw new RecoverableIndexerException(exception);
		}
	}

	@Override
	public void updateRoomIndex(ReceptionCenter center, Location
			location, Room room) throws IndexerException {
		if (!getIndexExists()) {
			throw new UnrecoverableIndexerException(
					"Updating room index that doesn't exist");
		}

		GetResponse response = getRoomIndexById(room.getRoomId());
		if (!response.isExists()) {
			throw new UnrecoverableIndexerException(
					"Updating room index that doesn't exist");
		}

		if (isDirty(response, center, location, room)) {
			createRoomIndex(center, location, room);
		} else {
			// TODO Should we throw an exception here?
			LOGGER.debug("Skipping room update with ID " + room.getRoomId());
		}
	}

	@Override
	public void deleteRoomIndex(ReceptionCenter center, Location
			location, Room room) throws IndexerException {
		deleteRoomIndexById(room.getRoomId());
	}

	public void deleteRoomIndexById(long roomId) {
		String esId = createIndexId(roomId);

		DeleteResponse response = client.prepareDelete(this.indexName, this.typeName, esId)
				.execute()
				.actionGet();
	}

	public void deinitIndex() {
		if (getIndexExists()) {
			DeleteIndexResponse delete = client.admin()
					.indices()
					.delete(new DeleteIndexRequest(this.indexName))
					.actionGet();
			initIndex();
		}
	}

	@Override
	public void commit() {
	}

	/**
	 * Create an room index ID based on the persistence room ID.
	 *
	 * @param roomId
	 */
	protected String createIndexId(long roomId) {
		return String.valueOf(roomId);
	}

	protected void buildIndex(XContentBuilder builder, ReceptionCenter center, Location location, Room room) throws IOException {
		PropertiesHolder props = new PropertiesHolder();

		PropertiesHolder centerProps = center.getPropertiesHolder();
		if (centerProps != null) {
			props.addPropertiesHolder(centerProps);
		}

		PropertiesHolder locationProps = location.getPropertiesHolder();
		if (locationProps != null) {
			props.addPropertiesHolder(locationProps);
		}

		PropertiesHolder roomProps = room.getPropertiesHolder();
		if (roomProps != null) {
			props.addPropertiesHolder(roomProps);
		}

		builder.startObject()
				.field("room_version", room.getVersion())
				.field("reception_center_id", center.getReceptionCenterId())
				.field("reception_center_version", center.getVersion())
				.field("location_id", location.getLocationId())
				.field("location_version", location.getVersion())
				.field("structure_type", center.getStructureType())
				.field("reception_name", center.getReceptionName())
				.field("organization", center.getOrganization())
				.field("email_address", center.getEmailAddress())
				.field("phone_number", center.getPhoneNumber())
				.field("code_location", center.getCodeLocation())
				.field("network", center.getNetwork())
				.field("block", location.getBlock())
				.field("floor", location.getFloor())
				.field("room_identification", room.getIdentification())
				.field("bed_count", room.getNumberOfBeds())
				.field("available_bed_count", room.getNumberOfAvailableBeds())
				.field("maximum_bed_count", room.getMaxNumberOfBeds())
				.field("properties", props)
				.endObject();
	}

	/**
	 * Check if the room index is dirty by comparing it with a room, its
	 * location and its reception center.
	 *
	 * @param response
	 * @param center
	 * @param location
	 * @param room
	 * @return
	 */
	protected boolean isDirty(GetResponse response, ReceptionCenter center,
			Location location, Room room) {
		Map<String, Object> source = response.getSource();
		return ((int) source.get("reception_center_id") != center.getReceptionCenterId()
				|| (int) source.get("reception_center_version") < center.getVersion()
				|| (int) source.get("location_id") != location.getLocationId()
				|| (int) source.get("location_version") < location.getVersion()
				|| (int) source.get("room_version") < room.getVersion());
	}

	protected boolean getIndexExists() {
		IndicesExistsResponse response = client.admin()
				.indices()
				.exists(new IndicesExistsRequest(this.indexName))
				.actionGet();
		return response.isExists();
	}
}
