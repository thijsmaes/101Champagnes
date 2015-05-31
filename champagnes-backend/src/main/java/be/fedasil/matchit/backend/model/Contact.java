package be.fedasil.matchit.backend.model;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.model.properties.PropertyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * See <b>Fedasil Match-IT Conceptual data model Place.docx</b>.
 *
 * @author dtoch
 */
@Entity
@Table(name = "\"CONTACT\"")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"CONTACT_ID\"")
	private long contactId;

	@Version
	@Column(name = "\"VERSION\"")
	private long version;

	@NotNull
	@Column(name = "\"CONTACT_TYPE\"")
	private String contactType;

	@NotNull
	@Column(name = "\"SERVICE\"")
	private String service;

	@NotNull
	@Column(name = "\"NAME\"")
	private String name;

	@Column(name = "\"EMAIL_ADDRESS\"")
	private String emailAddress;

	@NotNull
	@Column(name = "\"PHONE_NUMBER\"")
	private String PhoneNumber;

	@ManyToOne(optional = false)
	@JoinColumn(name = "\"RECEPTION_CENTER_ID\"")
	private ReceptionCenter receptionCenterContact;

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public long getVersion() {
		return version;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public ReceptionCenter getReceptionCenterContact() {
		return receptionCenterContact;
	}

	public void setReceptionCenterContact(ReceptionCenter receptionCenter) {
		this.receptionCenterContact = receptionCenter;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	@Override
	public String toString() {
		return "Contact{" + "contactId=" + contactId + ", version=" + version
				+ ", service='" + service + '\'' + ", name='" + name + '\''
				+ ", emailAddress='" + emailAddress + '\'' + ", PhoneNumber='"
				+ PhoneNumber + '\'' + ", receptionCenter="
				+ receptionCenterContact + '}';
	}

}
