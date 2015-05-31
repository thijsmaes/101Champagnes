package be.fedasil.matchit.backend.dao;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.model.properties.PropertyUtil;

@MappedSuperclass
public abstract class AbstractProperty {

	@Column(name = "\"PROPERTIES\"", length = 1000000)
	protected String properties;

	@Transient
	private PropertiesHolder propertiesHolder;
	
	@PostLoad
	public void load() {
		propertiesHolder = PropertyUtil.jsonToPropertiesHolder(properties);
	}

	@PrePersist
	public void persist() {
		properties = PropertyUtil.propertiesHolderToJson(propertiesHolder);
	}

	@PreUpdate
	public void update() {
		properties = PropertyUtil.propertiesHolderToJson(propertiesHolder);
	}

	public String getProperties() {
		return properties;
	}

	public PropertiesHolder getPropertiesHolder() {
		return propertiesHolder;
	}

	public void setPropertiesHolder(PropertiesHolder propertiesHolder) {
		this.propertiesHolder = propertiesHolder;
	}

}
