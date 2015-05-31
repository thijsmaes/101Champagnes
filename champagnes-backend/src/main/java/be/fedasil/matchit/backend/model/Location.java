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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import be.fedasil.matchit.backend.dao.AbstractProperty;
import be.fedasil.matchit.backend.model.events.MessageGeneratorListener;

/**
 * See <b>Fedasil Match-IT Conceptual data model Place.docx v0.05</b>.
 * 
 * @author :Deo
 * */
@Entity
@EntityListeners(MessageGeneratorListener.class)
@Table(name = "\"LOCATION\"")
public class Location extends AbstractProperty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"LOCATION_ID\"")
	private long locationId;

	@Version
	@Column(name = "\"VERSION\"")
	private long version;

	@Column(name = "\"FLOOR\"")
	private String floor;

	@Column(name = "\"BLOCK\"")
	private String block;

	@Column(name = "\"ACTIVE\"")
	private Boolean active;

	@Column(name = "\"ENVIRONMENT\"")
	private String environement;

	// RelationShips
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "\"RECEPTION_CENTER_ID\"")
	private ReceptionCenter receptionCenter;

	// FK address
	@OneToMany(mappedBy = "location", targetEntity = Room.class, fetch = FetchType.LAZY)
	private List<Room> rooms;

	@OneToOne
	@JoinColumn(name = "\"ADDRESS_ID\"")
	private Address addressLocation;

	// Constructors
	public Location() {
	}

	public Location(ReceptionCenter receptionCenterLocation, String block,
			String floor, Boolean active) {
		super();

		this.block = block;
		this.floor = floor;
		this.receptionCenter = receptionCenterLocation;
		this.active = active;
	}

	public Location(ReceptionCenter receptionCenterLocation,
			Address addressLocation) {
		super();
		this.receptionCenter = receptionCenterLocation;
		this.addressLocation = addressLocation;
	}

	public long getLocationId() {
		return locationId;
	}

	public long getVersion() {
		return version;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public ReceptionCenter getReceptionCenter() {
		return receptionCenter;
	}

	public void setReceptionCenter(ReceptionCenter receptionCenter) {
		this.receptionCenter = receptionCenter;
	}

	public Address getAddressLocation() {
		return addressLocation;
	}

	public void setAddressLocation(Address addressLocation) {
		this.addressLocation = addressLocation;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getEnvironement() {
		return environement;
	}

	public void setEnvironement(String environement) {
		this.environement = environement;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

}
