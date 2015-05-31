package be.fedasil.matchit.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import be.fedasil.matchit.backend.dao.AbstractProperty;

/**
 * See <b>Fedasil Match-IT Conceptual data model Place.docx</b>.
 * 
 * @author dtoch
 */
@Entity
@Table(name = "\"PLACE\"")
public class Place extends AbstractProperty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"PLACE_ID\"")
	private long placeId;

	@NotNull
	@Column(name = "\"PLACE_CODE\"", unique = true)
	private String placeCode;

	@NotNull
	@Column(name = "\"OCCUPANCY_STATUS\"")
	private String occupancyStatus;

	@NotNull
	@Column(name = "\"TYPE_BED\"")
	private String typeBed;

	@NotNull
	@Column(name = "\"TYPE_PLACE\"")
	private String typePlace;

	@NotNull
	@Column(name = "\"CONVENTIONED_PLACE\"")
	private String conventionedPlace;

	@Column(name = "\"ACTIVE\"")
	private Boolean active;

	@Version
	@Column(name = "\"VERSION\"")
	private long version;

	/*
	 * Relationship @ManyToOne Reference to foreign key ROOM_ID in ROOM table
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "\"ROOM_ID\"")
	private Room room;

	// Constructors

	public Place() {
	}

	public Place(String placeCode, String occupancyStatus, String typeBed,
			String typePlace, String conventionedPlace, Room room) {
		super();
		this.placeCode = placeCode;
		this.occupancyStatus = occupancyStatus;
		this.typeBed = typeBed;
		this.typePlace = typePlace;
		this.conventionedPlace = conventionedPlace;
		this.room = room;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public String getOccupancyStatus() {
		return occupancyStatus;
	}

	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}

	public String getTypeBed() {
		return typeBed;
	}

	public void setTypeBed(String typeBed) {
		this.typeBed = typeBed;
	}

	public String getTypePlace() {
		return typePlace;
	}

	public void setTypePlace(String typePlace) {
		this.typePlace = typePlace;
	}

	public String getConventionedPlace() {
		return conventionedPlace;
	}

	public void setConventionedPlace(String conventionedPlace) {
		this.conventionedPlace = conventionedPlace;
	}

	public long getVersion() {
		return version;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Place{" + "placeId=" + placeId + ", placeCode='" + placeCode
				+ '\'' + ", occupancyStatus='" + occupancyStatus + '\''
				+ ", typeBed='" + typeBed + '\'' + ", typePlace='" + typePlace
				+ '\'' + ", conventionedPlace='" + conventionedPlace + '\''
				+ ", version=" + version + ", room=" + room + '}';
	}

}
