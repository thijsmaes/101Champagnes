package be.fedasil.matchit.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"CONFIGURATION\"")
public class ConfigurationItem {

	@Column(name = "\"KEY\"")
	@NotNull
	@Id
	private String key;

	@Column(name = "\"VALUE\"")
	@NotNull
	private String value;

	public ConfigurationItem() {
	}

	public ConfigurationItem(String key, String value) {
		this.value = value;
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "ConfigurationItem{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}

}
