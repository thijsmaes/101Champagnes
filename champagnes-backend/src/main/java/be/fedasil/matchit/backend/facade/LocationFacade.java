package be.fedasil.matchit.backend.facade;

import java.util.List;

import be.fedasil.matchit.backend.model.Location;

public interface LocationFacade {

	/**
	 * 
	 * @return Returns list of Locations
	 */

	List<Location> findAllLocations();

	/***
	 * Find Location by id
	 * 
	 * @param id
	 * @return Returns the Location
	 */
	Location findLocationById(long id);

	/**
	 * Create Location in the database
	 * 
	 * @param location
	 */
	void createLocation(Location location);

	/**
	 * Update Location in database
	 * 
	 * @param location
	 * 
	 */
	void updateLocation(Location location);

	/**
	 * Delete Location from database
	 * 
	 * @param location
	 * 
	 */
	void deleteLocation(Location location);
}
