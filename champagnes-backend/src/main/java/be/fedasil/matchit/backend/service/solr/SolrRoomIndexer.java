package be.fedasil.matchit.backend.service.solr;

import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.process.indexer.RoomIndexer;
import be.fedasil.matchit.backend.service.solr.beans.SolrRoomBean;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.RecoverableIndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.UnrecoverableIndexerException;
import be.fedasil.matchit.backend.service.solr.indexer.RegistrySolrIndexer;
import be.fedasil.matchit.backend.service.solr.indexer.SolrIndexer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class augments the SolrClient class to make easier to create, update,
 * delete and query Solr rooms.
 *
 * @author gvginder
 */
public class SolrRoomIndexer implements RoomIndexer {
	private static final MatchitLogger LOGGER = MatchitLoggerFactory
			.getLogger(SolrRoomIndexer.class);

	private final SolrClient client;
	private final SolrIndexer indexer;

	public SolrRoomIndexer(SolrClient client) {
		this(client, new RegistrySolrIndexer());
	}

	public SolrRoomIndexer(SolrClient client, SolrIndexer indexer) {
		this.client = client;
		this.indexer = indexer;
	}

	/**
	 * Create the Solr room identified by the given room, its location and its
	 * reception center.
	 *
	 * @param center
	 * @param location
	 * @param room
	 * @throws IndexerException
	 */
	@Override
	public void createRoomIndex(ReceptionCenter center, Location location, Room room)
			throws IndexerException {
		SolrInputDocument document = buildSolrRoom(center, location, room);
		UpdateResponse response;
		try {
			response = client.add(document);

			LOGGER.debug("Added room with id " + document.getField("id") + " to Solr");
		} catch (SolrServerException | IOException exception) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException(exception);
		}
		if (response.getStatus() != 0) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException("Add document returned result "
					+ response.getStatus());
		}
	}

	/**
	 * Update the Solr room identified by the given room, its location and its
	 * reception center.
	 *
	 * @param center
	 * @param location
	 * @param room
	 * @throws IndexerException
	 */
	@Override
	public void updateRoomIndex(ReceptionCenter center, Location location, Room room)
			throws IndexerException {
		String solrId = createSolrId(room.getRoomId());
		SolrRoomBean solrRoom = findSolrRoomById(room.getRoomId());
		if (solrRoom == null) {
			// TODO Verify recoverability
			throw new UnrecoverableIndexerException(
					"Updating room that doesn't exist");
		}

		if (isDirty(solrRoom, center, location, room)) {
			createRoomIndex(center, location, room);
		} else {
			// TODO Should we throw an exception here?
			LOGGER.debug("Skipping document update with ID " + solrId);
		}
	}

	/**
	 * Delete the Solr room identified by the given room, its location and its
	 * reception center.
	 *
	 * @param center
	 * @param location
	 * @param room
	 * @throws IndexerException
	 */
	@Override
	public void deleteRoomIndex(ReceptionCenter center, Location location, Room room)
			throws IndexerException {
		deleteSolrRoomById(room.getRoomId());
	}

	/**
	 * Delete the Solr room identified by the given room ID, its location and
	 * its reception center.
	 *
	 * @param roomId
	 * @throws IndexerException
	 */
	public void deleteSolrRoomById(long roomId)
			throws IndexerException {
		String solrId = createSolrId(roomId);
		try {
			client.deleteById(solrId);

			LOGGER.debug("Deleted room with ID " + roomId);
		} catch (SolrServerException | IOException exception) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException(exception);
		}
	}

	/**
	 * Delete all Solr rooms.
	 *
	 * @throws RecoverableIndexerException
	 */
	public void deleteAllSolrRooms() throws RecoverableIndexerException {
		try {
			client.deleteByQuery("*:*");

			LOGGER.debug("Deleted all rooms");
		} catch (SolrServerException | IOException exception) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException(exception);
		}
	}

	/**
	 * Return the Solr room identified by the given persistence room ID or null
	 * when the Solr room could not be found.
	 *
	 * @param roomId
	 * @return
	 */
	public SolrRoomBean findSolrRoomById(long roomId) {
		String solrId = createSolrId(roomId);
		List<? extends SolrRoomBean> rooms = findSolrRooms("content_type_s:room AND id:" + solrId);
		if (rooms.size() > 0) {
			return rooms.get(0);
		}
		return null;
	}

	/**
	 * Returns all Solr rooms, including the matching score.
	 *
	 * @return
	 */
	public List<? extends SolrRoomBean> findAllSolrRooms() {
		return findSolrRooms("content_type_s:room");
	}

	/**
	 * Return all Solr rooms with children that match the given query.
	 * Specifically, it just prepends `{!parent which="content_type_s:room"}` to
	 * the given query.
	 *
	 * @param childQuery
	 */
	public List<? extends SolrRoomBean> findSolrRoomsWithChildren(String childQuery) {
		return findSolrRooms("{!parent which=\"content_type_s:room\"}" + childQuery);
	}

	/**
	 * Returns all Solr rooms matching the query.
	 *
	 * For example to find all Solr rooms with a hospital_distance between 5 and
	 * 15: `{!parent which="content_type_s:room"}key_s:hospital_distance AND
	 * value_is:[5 TO 15]`
	 *
	 * @return
	 * @see <a href="https://cwiki.apache.org/confluence/display/solr/Transforming+Result+Documents">
	 * More documentation on ĉhild] transformer can be found here</a>.
	 */
	public List<? extends SolrRoomBean> findSolrRooms(String query) {
		return findSolrRooms(new SolrQuery()
				.setQuery(query)
				.setFields("*", "[child parentFilter=content_type_s:room]"));
	}

	/**
	 * Returns all Solr rooms matching the query.
	 *
	 * @param query
	 * @return
	 */
	public List<? extends SolrRoomBean> findSolrRooms(SolrQuery query) {
		try {
			QueryResponse response = client.query(query);
			return response.getBeans(SolrRoomBean.class);
		} catch (SolrServerException | IOException exception) {
			LOGGER.error("Unable to find rooms", exception);

			// TODO Is it correct to return an empty list on failure?
			return Collections.emptyList();
		}
	}

	/**
	 * Commit the performed changes to Solr.
	 *
	 * @throws IndexerException
	 */
	@Override
	public void commit() throws IndexerException {
		try {
			client.commit();
		} catch (SolrServerException | IOException exception) {
			// TODO Verify recoverability
			throw new RecoverableIndexerException(exception);
		}
	}

	/**
	 * Check if the Solr room is dirty by comparing it with a room, its location
	 * and its reception center.
	 *
	 * @param bean
	 * @param center
	 * @param location
	 * @param room
	 * @return
	 */
	protected boolean isDirty(SolrRoomBean bean, ReceptionCenter center,
			Location location, Room room) {
		return (bean.getReceptionCenterId() != center.getReceptionCenterId()
				|| bean.getReceptionCenterVersion() < center.getVersion()
				|| bean.getLocationId() != location.getLocationId()
				|| bean.getLocationVersion() < location.getVersion() || bean
				.getRoomVersion() < room.getVersion());
	}

	/**
	 * Create a Solr room instance based on a room, its location and its
	 * reception center.
	 *
	 * @param center
	 * @param location
	 * @param room
	 * @return
	 */
	protected SolrInputDocument buildSolrRoom(ReceptionCenter center,
			Location location, Room room) throws IndexerException {
		String solrId = createSolrId(room.getRoomId());

		SolrRoomBean bean = new SolrRoomBean();
		bean.setId(solrId);
		bean.setRoomVersion(room.getVersion());
		bean.setReceptionCenterId(center.getReceptionCenterId());
		bean.setReceptionCenterVersion(center.getVersion());
		bean.setLocationId(location.getLocationId());
		bean.setLocationVersion(location.getVersion());

		bean.setStructureType(center.getStructureType());
		bean.setReceptionName(center.getReceptionName());
		bean.setOrganization(center.getOrganization());
		bean.setEmailAddress(center.getEmailAddress());
		bean.setPhoneNumber(center.getPhoneNumber());
		bean.setCodeLocation(center.getCodeLocation());
		bean.setNetwork(center.getNetwork());

		bean.setBlock(location.getBlock());
		bean.setFloor(location.getFloor());

		bean.setRoomIdentification(room.getIdentification());
		bean.setNumberOfBeds(room.getNumberOfBeds());
		bean.setMaxNumberOfBeds(room.getMaxNumberOfBeds());
		bean.setNumberOfAvailableBeds(room.getNumberOfAvailableBeds());

		DocumentObjectBinder binder = client.getBinder();
		SolrInputDocument solrRoom = binder.toSolrInputDocument(bean);

		buildSolrProps(solrRoom, solrId, center, location, room);

		return solrRoom;
	}

	/**
	 * Create a list of Solr properties using the indexer of the class.
	 *
	 * @param solrId
	 * @param center
	 * @param location
	 * @param room
	 * @return
	 * @throws IndexerException
	 */
	protected void buildSolrProps(SolrInputDocument document, String solrId, ReceptionCenter center, Location location, Room room) throws IndexerException {
		// Combine all properties into one
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

		for (Map.Entry<String, PropertiesHolder.Category> categoryMapEntry : props.entrySet()) {
			String categoryName = categoryMapEntry.getKey();
			PropertiesHolder.Category category = categoryMapEntry.getValue();

			// We need to keep track of the entry indexer to generate a unique Solr property ID.
			for (Map.Entry<String, PropertiesHolder.Entry> entryMapEntry : category.entrySet()) {
				String entryName = entryMapEntry.getKey();
				PropertiesHolder.Entry entry = entryMapEntry.getValue();

				// Write property to document
				indexer.buildSolrProperty(document, solrId, categoryName, entryName, entry);
			}
		}
	}

	/**
	 * Create an Solr room ID based on the persistence room ID.
	 *
	 * @param roomId
	 */
	protected String createSolrId(long roomId) {
		return String.valueOf(roomId);
	}
}
