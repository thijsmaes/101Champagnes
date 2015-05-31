package be.fedasil.matchit.frontend.component.showplace;

//import be.fedasil.matchit.backend.model.AddressPlace;
import be.fedasil.matchit.backend.model.*;

import be.fedasil.matchit.frontend.component.showplace.tabsheetplace.LocationBuilder;
import be.fedasil.matchit.frontend.component.showplace.tabsheetplace.PlaceBuilder;
import be.fedasil.matchit.frontend.component.showplace.tabsheetplace.RoomBuilder;
import be.fedasil.matchit.frontend.component.showplace.tabsheetplace.StructureBuilder;
import com.vaadin.ui.*;

public class ShowPlaceView extends CustomComponent {

	private static final long serialVersionUID = 67209593343906007L;


	Address address;
	Place place;
	Room room;
	Location location;
	ReceptionCenter receptionCenter;

	ShowPlaceView(Place place) {
		super();
		setPlace(place);
		room = place.getRoom();
		LazyTabSheet placeViewer;
		placeViewer = placeViewer();

		setCompositionRoot(placeViewer);
	}

	public void setPlace(Place place)
	{
		this.place=place;
	}

	private LazyTabSheet placeViewer() {

		room = place.getRoom();
		location = room.getLocation();
		address = room.getLocation().getAddressLocation();
		receptionCenter = place.getRoom().getLocation().getReceptionCenter();

		LazyTabSheet placeDetails = new LazyTabSheet();
		VerticalLayout placeLayout = new PlaceBuilder().createPlace(place);
		VerticalLayout roomLayout = new RoomBuilder().createRoom(room);
		VerticalLayout addressLayout = new LocationBuilder().createAddress(location);
		VerticalLayout structureLayout = new StructureBuilder().createStructure(receptionCenter);

		placeDetails.addTab(placeLayout, "Place");
		placeDetails.addTab(roomLayout, "Room");
		placeDetails.addTab(addressLayout, "Address");
		placeDetails.addTab(structureLayout, "Structure");

		placeDetails.setSizeFull();
		return placeDetails;
	}
	
}
