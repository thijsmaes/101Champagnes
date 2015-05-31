package be.fedasil.matchit.backend.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.backend.model.Room;

/**
 * DAO for JPA entity Room.
 *
 * @author dtoch
 */
public class RoomDAO extends AbstractDAO {

	public Place findRoomByCode(String placeCode) {
		throw new NotImplementedException();
		// Place place = new Place();
		// place.setPlaceCode(placeCode);
		// return place;
	}

	public void createRoom(Room room) {
		throw new NotImplementedException();
		// EntityManager em = getEntityManager();
		// if (room.getAddressPlace() != null) {
		// em.persist(room);
		// em.flush();
		// }
	}

	public Room findRoomById(long id) {
		try {
			EntityManager em = getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Room> query = cb.createQuery(Room.class);
			Root<Room> room = query.from(Room.class);
			query.where(cb.equal(room.get("roomId"), id));

			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException nu) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}
	}

	public List<Room> findAllRoomsByReceptionCenterId(long receptionCenterId) {
		EntityManager em = getEntityManager();
		return em.createQuery(
				"SELECT r FROM Room r " +
						"JOIN r.location l " +
						"JOIN l.receptionCenter c " +
						"WHERE c.receptionCenterId = :receptionCenterId")
				.setParameter("receptionCenterId", receptionCenterId)
				.getResultList();
	}
}
