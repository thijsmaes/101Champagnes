package be.fedasil.matchit.backend.service.solr.beans;

import org.apache.solr.client.solrj.beans.Field;

/**
 * This class is used to read and write Solr rooms from and to the Solr server.
 *
 * @author gvginder
 */
public class SolrRoomBean {
	@Field("id")
	private String id;

	@Field("content_type_s")
	private String contentType;

	@Field("reception_center_id_l")
	private long receptionCenterId;
	@Field("reception_center_version_l")
	private long receptionCenterVersion;
	@Field("location_id_l")
	private long locationId;
	@Field("location_version_l")
	private long locationVersion;
	@Field("room_version_l")
	private long roomVersion;

	@Field("structure_type_t")
	private String structureType;
	@Field("reception_name_t")
	private String receptionName;
	@Field("organization_t")
	private String organization;
	@Field("email_address_t")
	private String emailAddress;
	@Field("phone_number_t")
	private String phoneNumber;
	@Field("code_location_t")
	private String codeLocation;
	@Field("network_t")
	private String network;

	@Field("block_t")
	private String block;
	@Field("floor_t")
	private String floor;

	@Field("room_identification_s")
	private String roomIdentification;
	@Field("beds_i")
	private int numberOfBeds;
	@Field("max_beds_i")
	private int maxNumberOfBeds;
	@Field("available_beds_i")
	private int numberOfAvailableBeds;

	public SolrRoomBean() {
		contentType = "room";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getReceptionCenterId() {
		return receptionCenterId;
	}

	public void setReceptionCenterId(long receptionCenterId) {
		this.receptionCenterId = receptionCenterId;
	}

	public long getReceptionCenterVersion() {
		return receptionCenterVersion;
	}

	public void setReceptionCenterVersion(long receptionCenterVersion) {
		this.receptionCenterVersion = receptionCenterVersion;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getLocationVersion() {
		return locationVersion;
	}

	public void setLocationVersion(long locationVersion) {
		this.locationVersion = locationVersion;
	}

	public long getRoomVersion() {
		return roomVersion;
	}

	public void setRoomVersion(long roomVersion) {
		this.roomVersion = roomVersion;
	}

	public String getStructureType() {
		return structureType;
	}

	public void setStructureType(String structureType) {
		this.structureType = structureType;
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

	public String getCodeLocation() {
		return codeLocation;
	}

	public void setCodeLocation(String codeLocation) {
		this.codeLocation = codeLocation;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoomIdentification() {
		return roomIdentification;
	}

	public void setRoomIdentification(String roomIdentification) {
		this.roomIdentification = roomIdentification;
	}

	public long getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int beds) {
		this.numberOfBeds = beds;
	}

	public int getMaxNumberOfBeds() {
		return maxNumberOfBeds;
	}

	public void setMaxNumberOfBeds(int maxBeds) {
		this.maxNumberOfBeds = maxBeds;
	}

	public int getNumberOfAvailableBeds() {
		return numberOfAvailableBeds;
	}

	public void setNumberOfAvailableBeds(int numberOfAvailableBeds) {
		this.numberOfAvailableBeds = numberOfAvailableBeds;
	}
}