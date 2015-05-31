package be.fedasil.matchit.backend.process.indexer;

import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.UnrecoverableIndexerException;
import be.fedasil.matchit.backend.model.events.messages.AbstractMessage;
import be.fedasil.matchit.backend.model.events.messages.LocationMessage;
import be.fedasil.matchit.backend.model.events.messages.MessageVisitor;
import be.fedasil.matchit.backend.model.events.messages.ReceptionCenterMessage;
import be.fedasil.matchit.backend.model.events.messages.RoomMessage;
import be.fedasil.matchit.backend.dao.LocationDAO;
import be.fedasil.matchit.backend.dao.RoomDAO;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;

import java.util.List;

/**
 * This class implements the MessageVisitor interface and creates, updates and
 * deletes room indexes based on the type of message that was received.
 *
 * @author gvginder
 */
public class RoomClientMessageVisitor implements MessageVisitor {
	private static final MatchitLogger LOGGER = MatchitLoggerFactory
			.getLogger(RoomClientMessageVisitor.class);

	private final RoomIndexer client;

	private final LocationDAO locationDAO;
	private final RoomDAO roomDAO;

	public RoomClientMessageVisitor(RoomIndexer client) {
		this.client = client;

		locationDAO = new LocationDAO();
		roomDAO = new RoomDAO();
	}

	@Override
	public void visit(ReceptionCenterMessage message) throws Exception {
		List<Room> rooms =
				roomDAO.findAllRoomsByReceptionCenterId(message.receptionCenterId);

		try {
			handleChange(message.type, rooms);
			client.commit();
		} catch (UnrecoverableIndexerException exception) {
			// TODO Put message on error queue?
			LOGGER.error("Unable to store message in Solr", exception);
		}
	}

	@Override
	public void visit(LocationMessage message) throws Exception {
		Location location = locationDAO.findLocationById(message.locationId);

		try {
			handleChange(message.type, location.getRooms());
			client.commit();
		} catch (UnrecoverableIndexerException exception) {
			// TODO Put message on error queue?
			LOGGER.error("Unable to store message in Solr", exception);
		}
	}

	@Override
	public void visit(RoomMessage message) throws Exception {
		Room room = roomDAO.findRoomById(message.roomId);

		try {
			handleChange(message.type, room);
			client.commit();
		} catch (UnrecoverableIndexerException exception) {
			// TODO Put message on error queue?
			LOGGER.error("Unable to store message in Solr", exception);
		}
	}

	/**
	 * @throws IndexerException
	 */
	protected void handleChange(AbstractMessage.Type type, Iterable<Room> rooms) throws Exception {
		for (Room room : rooms) {
			handleChange(type, room);
		}
	}

	/**
	 * @throws IndexerException
	 */
	protected void handleChange(AbstractMessage.Type type, Room room) throws Exception {
		Location location = room.getLocation();
		ReceptionCenter center = location.getReceptionCenter();

		switch (type) {
			case CREATED:
				client.createRoomIndex(center, location, room);
				break;
			case UPDATED:
				client.updateRoomIndex(center, location, room);
				break;
			case DELETED:
				client.deleteRoomIndex(center, location, room);
				break;
		}
	}
}
