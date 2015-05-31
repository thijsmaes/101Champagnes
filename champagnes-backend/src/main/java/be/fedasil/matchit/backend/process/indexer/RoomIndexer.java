package be.fedasil.matchit.backend.process.indexer;

import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;

/**
 * @author gvginder
 */
public interface RoomIndexer {
	void createRoomIndex(ReceptionCenter center, Location location, Room room) throws IndexerException;

	void updateRoomIndex(ReceptionCenter center, Location location, Room room) throws IndexerException;

	void deleteRoomIndex(ReceptionCenter center, Location location, Room room) throws IndexerException;

	void commit() throws IndexerException;
}
