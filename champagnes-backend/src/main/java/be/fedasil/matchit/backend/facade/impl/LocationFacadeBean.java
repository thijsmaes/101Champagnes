package be.fedasil.matchit.backend.facade.impl;

import java.util.List;

import javax.ejb.Stateless;

import be.fedasil.matchit.backend.dao.LocationDAO;
import be.fedasil.matchit.backend.exception.NotImplementedException;
import be.fedasil.matchit.backend.facade.LocationFacade;
import be.fedasil.matchit.backend.model.Location;

@Stateless
public class LocationFacadeBean implements LocationFacade {
	private final LocationDAO dao;

	public LocationFacadeBean() {
		dao = new LocationDAO();
	}

	@Override
	public List<Location> findAllLocations() {
		throw new NotImplementedException();
	}

	@Override
	public Location findLocationById(long id) {
		return dao.findLocationById(id);
	}

	@Override
	public void createLocation(Location location) {
		throw new NotImplementedException();
	}

	@Override
	public void updateLocation(Location location) {
		throw new NotImplementedException();
	}

	@Override
	public void deleteLocation(Location location) {
		throw new NotImplementedException();
	}
}
