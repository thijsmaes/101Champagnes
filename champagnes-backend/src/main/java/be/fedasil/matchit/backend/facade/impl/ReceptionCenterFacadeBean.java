package be.fedasil.matchit.backend.facade.impl;

import java.util.List;

import javax.ejb.Stateless;

import be.fedasil.matchit.backend.dao.ReceptionCenterDAO;
import be.fedasil.matchit.backend.facade.ReceptionCenterFacade;
import be.fedasil.matchit.backend.model.ReceptionCenter;

/**
 * 
 * @author fdn
 *
 */

@Stateless
public class ReceptionCenterFacadeBean implements ReceptionCenterFacade {
	private final ReceptionCenterDAO dao;
	
	public ReceptionCenterFacadeBean() {
		dao = new ReceptionCenterDAO();
	}

	@Override
	public List<ReceptionCenter> findAllReceptionCenters() {
		return dao.findAllReceptionCenters();
	}

	@Override
	public ReceptionCenter findReceptionCenterById(long id) {
		return dao.findReceptionCenterById(id);
	}

	@Override
	public void createReceptionCenterById(ReceptionCenter receptionCenter) {
		dao.createReceptionCenterById(receptionCenter);
	}

	@Override
	public void updateReceptionCenterById(ReceptionCenter receptionCenter) {
		dao.updateReceptionCenterById(receptionCenter);
	}

	@Override
	public void deleteReceptionCenterById(ReceptionCenter receptionCenter) {
		dao.deleteReceptionCenterById(receptionCenter);		
	}
}
