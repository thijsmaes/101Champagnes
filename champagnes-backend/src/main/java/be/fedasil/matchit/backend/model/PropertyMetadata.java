package be.fedasil.matchit.backend.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity representing the allowed properties, the category they belong to, the
 * data type, .... Based on <b>Fedasil Match-IT Data dictionary.xlsx</b>, tab
 * "Properties".
 * 
 * Purpose of having the metadata in the database is to have a generic mechanism
 * allowing to add new properties dynamically in the database when needed.
 * 
 * @author dtoch
 */
@Entity
@IdClass(PropertyMetadata.UniqueKey.class)
@Table(name = "\"PROPERTY_METADATA\"")
@Cacheable(true)
public class PropertyMetadata {
	
	@SuppressWarnings("serial")
	public static class UniqueKey implements Serializable{
		String entity;
		String category;
		String propertyKey;
	}
	
	// The entity to which this property belongs.
	@Id
	@Column(name = "\"ENTITY\"")
	private String entity;

	// The category (= frame) to which this property belongs.
	@Id
	@Column(name = "\"CATEGORY\"")
	private String category;

	// Indicates whether the given entry is a property (if not : attribute)
	@Column(name = "\"IS_PROPERTY\"")
	private Boolean isProperty;
	
	// The name of the property.
	@Id
	@Column(name = "\"PROPERTY_KEY\"")
	private String propertyKey;

	// The data type of the value of the property.
	@Column(name = "\"PROPERTY_VALUE_DATATYPE\"")
	private String propertyValueDatatype;

	// To indicate whether the property is required or not.
	@Column(name = "\"MANDATORY\"")
	private Boolean mandatory;

	// Are multiple values allowed for the property (1 or n)?
	@Column(name = "\"MULTIPLE\"")
	private Boolean multiple;
	
	// Label code to use for showing the translated message of the
	// property key (links to tab "Labels" in <b>Fedasil Match-IT Data dictionary.xlsx</b>).
	@Column(name = "\"LABEL_CODE\"")
	private String labelCode;
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}
	
	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	
	public String getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}

	public String getPropertyValueDatatype() {
		return propertyValueDatatype;
	}

	public void setPropertyValueDatatype(String propertyValueDatatype) {
		this.propertyValueDatatype = propertyValueDatatype;
	}
}
