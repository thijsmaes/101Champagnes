package be.fedasil.matchit.backend.facade.impl;

import javax.ejb.Stateless;

import be.fedasil.matchit.backend.dao.PlaceDAO;
import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.facade.PlaceFacade;
import be.fedasil.matchit.backend.model.Place;

@Stateless
public class PlaceFacadeBean implements PlaceFacade {

	private PlaceDAO placeDAO;
	
	public PlaceFacadeBean(PlaceDAO placeDAO) {
		this.placeDAO = placeDAO;
	}
	
	public PlaceFacadeBean() {
		placeDAO = new PlaceDAO();
	}
	
	@Override
	public Place findPlaceById(int id) {
		throw new NotImplementedException();
	}

	@Override
	public Place findPlaceByCode(String placeCode) {
		return placeDAO.findPlaceByCode(placeCode);
	}

	@Override
	/**
	 *Create Place in the database
	 *  @param Place
	 * search a last number of place in reception center, corresponds to Network and location.
	 * 	 * 
	 */
	public void createPlace(Place place) {
		throw new NotImplementedException();
	}

	@Override
	public void updatePlace(Place place ) {
		throw new NotImplementedException();
	}

	@Override
	public void deletePlace(Place place) {
		throw new NotImplementedException();
	}

}
