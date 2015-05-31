package be.fedasil.matchit.backend.facade;

import java.util.List;

import be.fedasil.matchit.backend.model.Room;

public interface RoomFacade {
	
	/**
	 * 
	 * @return Returns list of  Rooms
	 */

	List<Room> findAllRooms();
	
	/***
	 * Find Room by id 
	 * @param id
	 * @return Returns the Room
	 */		
	Room findRoomById(long id);
	
	/**
	 *  Create Room in the database
	 *  @param Room
	 */
	void createRoom(Room room);
	
	/**
	 * Update Room in database
	 * @param Room
	 * 
	 */
	void updateRoom(Room room);
	
	/**
	 * Delete Room from database
	 * @param Room
	 * 
	 */
	void deleteRoom(Room room);
}
