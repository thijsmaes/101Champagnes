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
@Table(name = "\"ROOM\"")
public class Room extends AbstractProperty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ROOM_ID\"")
	private long roomId;

	@Version
	@Column(name = "\"VERSION\"")
	private long version;

	@NotNull
	@Column(name = "\"NUMBER_OF_BEDS\"")
	private int numberOfBeds;

	@NotNull
	@Column(name = "\"MAX_NUMBER_OF_BEDS\"")
	private int maxNumberOfBeds;

	@NotNull
	@Column(name = "\"NUMBER_OF_AVAILABLE_BEDS\"")
	private int numberOfAvailableBeds;

	// add in version 05
	@Column(name = "\"IDENTIFICATION\"")
	private String identification;

	@Column(name = "\"ACTIVE\"")
	private Boolean active;

	/*
	 * Relationship @ManyToOne Reference to foreign key ADRESS_PLACE_ID in table
	 * ADRESS_PLACE
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "\"LOCATION_ID\"")
	private Location location;

	@OneToMany(mappedBy = "room", targetEntity = Place.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Place> places;

	public Room() {
	}

	public Room(int numberOfBeds, int maxNumberOfBeds,
			int numberOfAvailableBeds, String identification, Boolean active,
			Location location) {
		super();
		this.numberOfBeds = numberOfBeds;
		this.maxNumberOfBeds = maxNumberOfBeds;
		this.numberOfAvailableBeds = numberOfAvailableBeds;
		this.identification = identification;
		this.active = active;
		this.location = location;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public long getVersion() {
		return version;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getMaxNumberOfBeds() {
		return maxNumberOfBeds;
	}

	public void setMaxNumberOfBeds(int maxNumberOfBeds) {
		this.maxNumberOfBeds = maxNumberOfBeds;
	}

	public int getNumberOfAvailableBeds() {
		return numberOfAvailableBeds;
	}

	public void setNumberOfAvailableBeds(int numberOfAvailableBeds) {
		this.numberOfAvailableBeds = numberOfAvailableBeds;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public Boolean getActive() {
		return active;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Room{" + "roomId=" + roomId + ", version=" + version
				+ ", numberOfBeds=" + numberOfBeds + ", maxNumberOfBeds="
				+ maxNumberOfBeds + ", numberOfAvailableBeds="
				+ numberOfAvailableBeds + ", places=" + places + '}';
	}
}
