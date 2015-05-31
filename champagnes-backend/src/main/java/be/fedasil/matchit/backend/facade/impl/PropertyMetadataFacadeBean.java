package be.fedasil.matchit.backend.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import be.fedasil.matchit.backend.dao.PropertyMetadataDAO;
import be.fedasil.matchit.backend.facade.PropertyMetadataFacade;
import be.fedasil.matchit.backend.model.PropertyMetadata;

@Stateless
public class PropertyMetadataFacadeBean implements PropertyMetadataFacade {

	private PropertyMetadataDAO propertyMetadataDAO;
	
	public PropertyMetadataFacadeBean(PropertyMetadataDAO propertyMetadataDAO) {
		this.propertyMetadataDAO = propertyMetadataDAO;
	}
	
	public PropertyMetadataFacadeBean() {
		propertyMetadataDAO = new PropertyMetadataDAO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<PropertyMetadata> findAllPropertyMetadatas() {
		return propertyMetadataDAO.findAllPropertyMetadatas();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PropertyMetadata findPropertyMetadata(String entityFQCN, String category, String propertyKey) {
		List<PropertyMetadata> pmds = findAllPropertyMetadatas();
		for (PropertyMetadata pmd : pmds) {
			if (pmd.getEntity().equals(entityFQCN) && 
				pmd.getCategory().equals(category) &&
				pmd.getPropertyKey().equals(propertyKey)) {
				return pmd;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropertyMetadata> findPropertyMetadata(String entityFQCN,
			String category) {
		List<PropertyMetadata> result=new ArrayList<PropertyMetadata>(); 
		List<PropertyMetadata> pmds = findAllPropertyMetadatas();
		for (PropertyMetadata pmd : pmds) {
			if (pmd.getCategory().equals(category) && (entityFQCN==null || pmd.getEntity().equals(entityFQCN))) {
				result.add(pmd);
			}
		}
		return result;
	}
}
