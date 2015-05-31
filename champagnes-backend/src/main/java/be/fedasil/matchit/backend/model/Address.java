package be.fedasil.matchit.backend.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
* See <b>Fedasil Match-IT Conceptual data model Place.docx v0.05</b>.
* @author :Deo
* */
@Entity
@Table(name = "\"ADDRESS\"")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ADDRESS_ID\"")
	private long addressId;

	@NotNull
	@Column(name = "\"ADDRESS_NAME\"")
	private String addressName;

	@NotNull
	@Column(name = "\"STREET\"")
	private String street;

	@NotNull
	@Column(name = "\"NUMBER\"")
	private String number;

	@NotNull
	@Column(name = "\"POSTAL_BOX\"")
	private String postalBox;

	@NotNull
	@Column(name = "\"ZIP_CODE\"")
	private String zipCode;

	@NotNull
	@Column(name = "\"CITY\"")
	private String city;

	@NotNull
	@Column(name = "\"COUNTRY\"")
	private String country;

	// TODO Add version field.
	@Version
	@Column(name = "\"VERSION\"")
	private long vesrion;

	public Address(String addressName, String street, String number,
			String postalBox, String zipCode, String city, String country) {

		super();
		this.addressName = addressName;
		this.street = street;
		this.number = number;
		this.postalBox = postalBox;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public Address() {
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalBox() {
		return postalBox;
	}

	public void setPostalBox(String postalBox) {
		this.postalBox = postalBox;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;

	}

	@Override
	public String toString() {
		return street + " " + number + "/" + postalBox + ", " +
				zipCode + " " + city + ", " + country;
	}

}
