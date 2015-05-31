package be.fedasil.matchit.backend.facade.impl;

import java.util.List;

import javax.ejb.Stateless;

import be.fedasil.matchit.backend.dao.RoomDAO;
import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.facade.RoomFacade;
import be.fedasil.matchit.backend.model.Room;

@Stateless
public class RoomFacadeBean implements RoomFacade {
	private final RoomDAO dao;

	public RoomFacadeBean() {
		dao = new RoomDAO();
	}

	@Override
	public List<Room> findAllRooms() {
		throw new NotImplementedException();
	}

	@Override
	public Room findRoomById(long id) {
		return dao.findRoomById(id);
	}

	@Override
	public void createRoom(Room room) {
		throw new NotImplementedException();
	}

	@Override
	public void updateRoom(Room room) {
		throw new NotImplementedException();
	}

	@Override
	public void deleteRoom(Room room) {
		throw new NotImplementedException();
	}
}
