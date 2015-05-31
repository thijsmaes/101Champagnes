package be.fedasil.matchit.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import be.fedasil.matchit.backend.model.PropertyMetadata;

/**
 * DAO for JPA entity PropertyMetadata.
 * 
 * @author dtoch
 */
public class PropertyMetadataDAO extends AbstractDAO {

	/**
	 * @return All property metadatas.
	 */
	public List<PropertyMetadata> findAllPropertyMetadatas() {
		try {
			EntityManager em = getEntityManager();
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<PropertyMetadata> query = cb.createQuery(PropertyMetadata.class);
			query.from(PropertyMetadata.class);
			return getEntityManager().createQuery(query).getResultList();
		} catch (NoResultException nu) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}
	}
}
