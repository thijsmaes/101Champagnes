package be.fedasil.matchit.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract DAO class from which all the other DAO classes must inherit.
 * 
 * @author dtoch
 */
public abstract class AbstractDAO {

	// TODO not automatically injected in JBoss when used in DAO POJO
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * @return The entity manager to use. If it wasn't injected automatically,
	 *         then it is programmatically instantiated.
	 */
	public EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = PersistenceManager.INSTANCE.getEntityManager();
		}
		return entityManager;
	}
}
