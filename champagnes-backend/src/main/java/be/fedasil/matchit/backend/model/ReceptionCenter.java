package be.fedasil.matchit.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import be.fedasil.matchit.backend.dao.AbstractProperty;
import be.fedasil.matchit.backend.model.events.MessageGeneratorListener;

/**
 * See <b>Fedasil Match-IT Conceptual data model Place.docx</b>.
 * 
 * @author dtoch
 */
@Entity
@EntityListeners(MessageGeneratorListener.class)
@Table(name = "\"RECEPTION_CENTER\"")
public class ReceptionCenter extends AbstractProperty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"RECEPTION_CENTER_ID\"")
	private long receptionCenterId;

	@Version
	@Column(name = "\"VERSION\"")
	private long version;

	@NotNull
	@Column(name = "\"RECEPTION_NAME\"")
	private String receptionName;

	/**
	 * we have forgotten this attribute values : collective or individual
	 * */

	@NotNull
	@Column(name = "\"STRUCTURE_TYPE\"")
	private String structureType;

	@NotNull
	@Column(name = "\"ORGANIZATION\"")
	private String organization;

	@Column(name = "\"EMAIL_ADDRESS\"")
	private String emailAddress;

	@NotNull
	@Column(name = "\"PHONE_NUMBER\"")
	private String phoneNumber;

	@Column(name = "\"MAX_NUMBER_OF_PLACES\"")
	private int maxNumberOfPlaces;

	@Column(name = "\"ACTIVE\"")
	private Boolean active;

	/* Reseau fedasil:FED , rode kuis:RKV */
	@NotNull
	@Column(name = "\"NETWORK\"")
	private String network;

	@OneToOne
	 @JoinColumn(name = "\"BILLING_ADDRESS_ID\"", nullable = true)
	//@JoinColumn(name = "\"ADDRESS_ID\"")
	private Address billingAddressId;

	@OneToOne
	 @JoinColumn(name = "\"CONTACT_ADDRESS_ID\"", nullable = true)
	//@JoinColumn(name = "\"ADDRESS_ID\"")
	private Address contactAddressId;

	/* Centre Fedasil d’Arendonk:ARE , Centre Rode Kruis de Brugge:BRUG */
	@NotNull
	@Column(name = "\"CODE_LOCATION\"")
	private String codeLocation;

	/* FED-ARE LAST NUMBER IS 00012 , RKV-BRUG LAST NUMBER IS 00112 */
	@NotNull
	@Column(name = "\"NUMBER_OF_LAST_PLACE\"")
	private long numberOfLastPlace;
	

	@OneToMany(mappedBy = "receptionCenterContact", targetEntity = Contact.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Contact> contacts;

	@OneToMany(mappedBy = "receptionCenter", targetEntity = Location.class, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Location> locations;

	public ReceptionCenter() {
	}

	public ReceptionCenter(String receptionName, String structureType,
			String organization, int maxNumberOfPlaces, String network,

			String codeLocation, long numberOfLastPlace) {
		super();

		this.receptionName = receptionName;
		this.structureType = structureType;
		this.organization = organization;
		this.maxNumberOfPlaces = maxNumberOfPlaces;
		this.network = network;

		this.codeLocation = codeLocation;
		this.numberOfLastPlace = numberOfLastPlace;
	}

	public long getReceptionCenterId() {
		return receptionCenterId;
	}

	public void setReceptionCenterId(long receptionCenterId) {
		this.receptionCenterId = receptionCenterId;
	}

	public long getVersion() {
		return version;
	}

	public String getReceptionName() {
		return receptionName;
	}

	public void setReceptionName(String receptionName) {
		this.receptionName = receptionName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getMaxNumberOfPlaces() {
		return maxNumberOfPlaces;
	}

	public void setMaxNumberOfPlaces(int maxNumberOfPlaces) {
		this.maxNumberOfPlaces = maxNumberOfPlaces;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getCodeLocation() {
		return codeLocation;
	}

	public void setCodeLocation(String codeLocation) {
		this.codeLocation = codeLocation;
	}

	public long getNumberOfLastPlace() {
		return numberOfLastPlace;
	}

	public void setNumberOfLastPlace(long numberOfLastPlace) {
		this.numberOfLastPlace = numberOfLastPlace;
	}

	public Address getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(Address billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public Address getContactAddressId() {
		return contactAddressId;
	}

	public void setContactAddressId(Address contactAddressId) {
		this.contactAddressId = contactAddressId;

	}

	public String getStructureType() {
		return structureType;
	}

	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ReceptionCenter{" + "receptionCenterId=" + receptionCenterId
				+ ", version=" + version + ", organization='" + organization
				+ '\'' + ", emailAddress='" + emailAddress + '\''
				+ ", phoneNumber='" + phoneNumber + '\''
				+ ", maxNumberOfPlaces=" + maxNumberOfPlaces + ", network='"
				+ network + '\'' + '\'' + ", codeLocation='" + codeLocation
				+ '\'' + ", numberOfLastPlace=" + numberOfLastPlace + '}';
	}

}
