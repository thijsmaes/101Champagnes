package be.fedasil.matchit.backend.model.events;

import be.fedasil.matchit.backend.model.events.messages.AbstractMessage;
import be.fedasil.matchit.backend.model.events.messages.LocationMessage;
import be.fedasil.matchit.backend.model.events.messages.ReceptionCenterMessage;
import be.fedasil.matchit.backend.model.events.messages.RoomMessage;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * This class will be used by Room, Location and ReceptionCenter entities to
 * automatically generate JMS messages when the entity is persisted, updated or
 * removed.
 *
 * @author gvginder
 */
public class MessageGeneratorListener {
	private static final MatchitLogger LOGGER = MatchitLoggerFactory
			.getLogger(MessageGeneratorListener.class);

	/**
	 * We have to make sure no exception are thrown in this method. If so, the active transaction will be rolled back.
	 *
	 * @param object
	 */
	@PostPersist
	public void postPersist(Object object) {
		LOGGER.debug("postPersist " + object);

		try {
			AbstractMessage message = createMessage(AbstractMessage.Type.CREATED, object);
			// TODO Post message on JMS queue
		} catch (Throwable throwable) {
			LOGGER.error("Exception in postPersist", throwable);
		}
	}

	/**
	 * We have to make sure no exception are thrown in this method. If so, the active transaction will be rolled back.
	 *
	 * @param object
	 */
	@PostUpdate
	public void postUpdate(Object object) {
		LOGGER.debug("postUpdate " + object);

		try {
			AbstractMessage message = createMessage(AbstractMessage.Type.UPDATED, object);
			// TODO Post message on JMS queue
		} catch (Throwable throwable) {
			LOGGER.error("Exception in postUpdate", throwable);
		}
	}

	/**
	 * We have to make sure no exception are thrown in this method. If so, the active transaction will be rolled back.
	 *
	 * @param object
	 */
	@PostRemove
	public void postRemove(Object object) {
		LOGGER.debug("postRemove " + object);

		try {
			AbstractMessage message = createMessage(AbstractMessage.Type.DELETED, object);
			// TODO Post message on JMS queue
		} catch (Throwable throwable) {
			LOGGER.error("Exception in postRemove", throwable);
		}
	}

	protected AbstractMessage createMessage(AbstractMessage.Type type, Object object) {
		// TODO Add visitor pattern to models too, or is this overkill? Then we
		// wouldn't have to use instanceof.
		if (object instanceof Room) {
			return createRoomMessage(type, (Room) object);
		} else if (object instanceof Location) {
			return createLocationMessage(type, (Location) object);
		} else if (object instanceof ReceptionCenter) {
			return createReceptionCenterMessage(type, (ReceptionCenter) object);
		}
		return null;
	}

	protected ReceptionCenterMessage createReceptionCenterMessage(AbstractMessage.Type type,
			ReceptionCenter center) {
		return new ReceptionCenterMessage(type, center.getReceptionCenterId(),
				center.getVersion());
	}

	protected LocationMessage createLocationMessage(AbstractMessage.Type type, Location location) {
		ReceptionCenter center = location.getReceptionCenter();

		return new LocationMessage(type, center.getReceptionCenterId(),
				center.getVersion(), location.getLocationId(),
				location.getVersion());
	}

	protected RoomMessage createRoomMessage(AbstractMessage.Type type, Room room) {
		Location location = room.getLocation();
		ReceptionCenter center = location.getReceptionCenter();

		return new RoomMessage(type, center.getReceptionCenterId(),
				center.getVersion(), location.getLocationId(),
				location.getVersion(), room.getRoomId(), room.getVersion());
	}
}
