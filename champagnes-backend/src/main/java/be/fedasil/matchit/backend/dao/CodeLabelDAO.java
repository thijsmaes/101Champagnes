package be.fedasil.matchit.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.CodeLabel;

public class CodeLabelDAO {

	private static MatchitLogger logger=MatchitLoggerFactory.getLogger(CodeLabelDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Returns all CodeLabel entries of the specified CodeType in the requested language
	 * @param codeType identifier of the code list
	 * @param language requested language (nl,fr,..)
	 * @return
	 */
	public List<CodeLabel> findAllByCodeTypeLanguage(String codeType, String language) {

		try {
			return entityManager.createNamedQuery("CodeLabel.findAllByCodeTypeLanguage",CodeLabel.class)//
					.setParameter("codeType",codeType)//
					.setParameter("language",language)//
					.getResultList();
		} catch (NoResultException nre) {
			logger.debug("No results found for codeType="+codeType+" and language:"+language);
			return null;
		} catch (IllegalArgumentException illexp) {
			logger.error("Illegal query parameter value set",illexp);
			return null;
		}
	}

	public List<CodeLabel> findAll()
	{
		try {
			return entityManager.createNamedQuery("CodeLabel.findAll",CodeLabel.class)//
					.getResultList();
		} catch (NoResultException nre) {
			logger.debug("No results found in CodeLabel");
			return null;
		} catch (IllegalArgumentException illexp) {
			logger.error("Illegal query parameter value set",illexp);
			return null;
		}
		
	}
}
