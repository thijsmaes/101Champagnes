package be.fedasil.matchit.backend.facade;

import javax.ejb.Local;

import be.fedasil.matchit.backend.model.Place;

/**
* See <b>Fedasil Match-IT Conceptual data model Place.docx v0.05</b>.
* @author :Deo
* */
@Local
public interface PlaceFacade {

	/***
	 * Find Place by id
	 * 
	 * @param id
	 * @return Returns the Place
	 */
	Place findPlaceById(int id);

	/**
	 * Find a place by its unique code.
	 * 
	 * @param placeCode
	 * @return
	 */
	Place findPlaceByCode(String placeCode);


	/**
	 * Create Place in the database
	 * 
	 * @param Place
	 */
	void createPlace(Place place);

	/**
	 * Update Place in database
	 * 
	 * @param Place
	 * 
	 */
	void updatePlace(Place place);

	/**
	 * Delete Place from database
	 * 
	 * @param Place
	 * 
	 */
	void deletePlace(Place place);

}
