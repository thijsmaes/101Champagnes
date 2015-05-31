package be.fedasil.matchit.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.model.ReceptionCenter;
/**
 * DAO for JPA entity ReceptionCenter.
 * 
 * @author dtoch
 */
public class ReceptionCenterDAO extends AbstractDAO {

	public List<ReceptionCenter> findAllReceptionCenters() {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ReceptionCenter> query = cb
					.createQuery(ReceptionCenter.class);
			Root<ReceptionCenter> receptionCenter = query
					.from(ReceptionCenter.class);
			query.select(receptionCenter);

			return em.createQuery(query).getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public ReceptionCenter findReceptionCenterById(long id) {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ReceptionCenter> query = cb.createQuery(ReceptionCenter.class);
			Root<ReceptionCenter> receptionCenter = query.from(ReceptionCenter.class);
			query.where(cb.equal(receptionCenter.get("receptionCenterId"), id));
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException nu) {
			return null;
		} catch (NonUniqueResultException nue) {
			return null;
		}
	}

	public void createReceptionCenterById(ReceptionCenter receptionCenter) {
		throw new NotImplementedException();
//		EntityManager em = getEntityManager();
//		em.persist(receptionCenter);
//		em.flush();
	}

	public void updateReceptionCenterById(ReceptionCenter receptionCenter) {
		throw new NotImplementedException();
//		EntityManager em = getEntityManager();
//		
//		ReceptionCenter updateReceptionCenter=findReceptionCenterById(receptionCenter.getReceptionCenterId());
//		
//		if( updateReceptionCenter !=null )
//		{
//			updateReceptionCenter.setAddressPlaces(receptionCenter.getAddressPlaces());
//			updateReceptionCenter.setCodeLocation(receptionCenter.getCodeLocation());
//			updateReceptionCenter.setNetwork(receptionCenter.getNetwork());
//			
//			updateReceptionCenter.setAddressPlaces(receptionCenter.getAddressPlaces());
//			updateReceptionCenter.setContacts(receptionCenter.getContacts());
//			
//			receptionCenter.setOrganization(receptionCenter.getOrganization());			
//			updateReceptionCenter.setEmailAddress(receptionCenter.getEmailAddress());
//			updateReceptionCenter.setMaxNumberOfPlaces(receptionCenter.getMaxNumberOfPlaces());
//			updateReceptionCenter.setNumberOfLastPlace(receptionCenter.getNumberOfLastPlace());
//		}
//
//		em.persist(updateReceptionCenter);
//		em.flush();
	}

	public void deleteReceptionCenterById(ReceptionCenter receptionCenter) {
		throw new NotImplementedException();
//		EntityManager em = getEntityManager();
//		ReceptionCenter deleteReceptionCenter = findReceptionCenterById(receptionCenter
//				.getReceptionCenterId());
//		em.remove(deleteReceptionCenter);
	}

}
