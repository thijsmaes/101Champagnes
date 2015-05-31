package be.fedasil.matchit.backend.facade;

import java.util.List;

import javax.ejb.Local;

import be.fedasil.matchit.backend.model.PropertyMetadata;

/**
 * Facade allowing to retrieve information of allowed properties the category
 * they belong to, the data type, .... Based on <b>Fedasil Match-IT Data
 * dictionary.xlsx</b>, tab "Properties".
 * 
 * Purpose of having the metadata in the database is to have a generic mechanism
 * allowing to add new properties dynamically in the database when needed.
 * 
 * @author dtoch
 */
@Local
public interface PropertyMetadataFacade {

	/**
	 * @return All property metadatas.
	 */
	public List<PropertyMetadata> findAllPropertyMetadatas();
	
	/**
	 * @param entityFQCN
	 * @param category
	 * @param propertyKey
	 * @return The metadata for the given property key belonging to a category and entity
	 */
	public PropertyMetadata findPropertyMetadata(String entityFQCN, String category, String propertyKey);
	
	/**
	 * Returns all property definitions for the specified category within the requested entity type.
	 * @param entityFQCN entity type in which the category is used
	 * @param category required Name of the category
	 * @return list of PropertyMetadata for the property types defined in the system.
	 */
	public List<PropertyMetadata> findPropertyMetadata(String entityFQCN, String category);
}
