package be.fedasil.matchit.backend.facade;

import java.util.List;

import javax.ejb.Local;

import be.fedasil.matchit.backend.model.ReceptionCenter;

@Local
public interface ReceptionCenterFacade {
	
	/**
	 * 
	 * @return Returns list of  ReceptionCenters in network
	 */

	List<ReceptionCenter> findAllReceptionCenters();
	
	/***
	 * Find ReceptionCenter by id 
	 * @param id
	 * @return Returns the ReceptionCenter
	 */

	ReceptionCenter findReceptionCenterById(long id);
	
	/**
	 *  Create ReceptionCenter in the database
	 *  @param ReceptionCenter
	 */

	void createReceptionCenterById(ReceptionCenter receptionCenter);
	
	/**
	 * Update ReceptionCenter in database
	 * @param ReceptionCenter
	 * 
	 */
	void updateReceptionCenterById(ReceptionCenter receptionCenter);
	
	
	
	/**
	 * Delete ReceptionCenter from database
	 * @param ReceptionCenter
	 * 
	 */
	void deleteReceptionCenterById(ReceptionCenter receptionCenter);
	


}
