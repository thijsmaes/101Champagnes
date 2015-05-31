package be.fedasil.matchit.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.fedasil.matchit.backend.model.Place;

/**
 * DAO for JPA entity Place.
 * 
 * @author dtoch
 */
public class PlaceDAO extends AbstractDAO {

	/**
	 * @param placeCode
	 *            Unique code of the place to find.
	 * @return Found place.
	 */
	public Place findPlaceByCode(String placeCode) {
		try {
			EntityManager em = getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Place> query = cb.createQuery(Place.class);
			Root<Place> place = query.from(Place.class);
			query.where(cb.equal(place.get("placeCode"), placeCode));

			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException nu) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}

	}
	
	public void updatePlace(Place place) {
		EntityManager em = getEntityManager();
		em.persist(place);
		em.flush();
	}

}
