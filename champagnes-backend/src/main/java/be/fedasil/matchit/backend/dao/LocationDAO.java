package be.fedasil.matchit.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.model.Location;

/**
 * DAO for JPA entity Location.
 * 
 * @author gvginder
 */
public class LocationDAO extends AbstractDAO {

	public void createLocation(Location location) {
		throw new NotImplementedException();
	}

	public List<Location> findAll() {
		throw new NotImplementedException();
	}

	public Location findLocationById(long id) {
		try {
			EntityManager em = getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Location> query = cb.createQuery(Location.class);
			Root<Location> location = query.from(Location.class);
			query.where(cb.equal(location.get("locationId"), id));

			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException nu) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}
	}
}
