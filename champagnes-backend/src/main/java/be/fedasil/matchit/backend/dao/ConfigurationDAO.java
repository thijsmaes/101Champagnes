package be.fedasil.matchit.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.fedasil.matchit.backend.model.ConfigurationItem;

/**
 * DAO for JPA entity Configuration.
 * 
 * @author dtoch
 */
public class ConfigurationDAO extends AbstractDAO {

	/**
	 * @return List of all configurations loaded in the database.
	 */
	public List<ConfigurationItem> findConfigurations() {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ConfigurationItem> query = cb
					.createQuery(ConfigurationItem.class);
			Root<ConfigurationItem> items = query
					.from(ConfigurationItem.class);
			query.select(items);
			return em.createQuery(query).getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
