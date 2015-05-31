package be.fedasil.matchit.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton instance to get JPA EntityManager pointing on Match-IT database.
 * 
 * @author dtoch
 */
public enum PersistenceManager {
	
	INSTANCE;

	private EntityManagerFactory emFactory;

	PersistenceManager() {
		emFactory = Persistence.createEntityManagerFactory("MatchitJpa");
	}

	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}

}
