package be.fedasil.matchit.frontend.component.showplace.tabsheetplace;

import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.backend.model.Room;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.frontend.util.LocaleText;
import com.vaadin.ui.*;

/**
 * Created by tmaes on 28/04/2015.
 */
public class RoomBuilder {

     Room room;
    PropertiesHolder placeProperties;

    public VerticalLayout createRoom(Room room) {
        this.room = room;
        placeProperties = room.getPropertiesHolder();


        VerticalLayout placeLayout = new VerticalLayout();
//        Label address = new Label(LocaleText.getCaption("view.showcontact.address") + " " + room.getAddressPlace().getStreet() + " " +
//                this.room.getAddressPlace().getNumber() + ", " + this.room.getAddressPlace().getPostalBox() + ", " +
//                this.room.getAddressPlace().getCity());

        Panel roomDescription = createRoomDescription(room);
        Table roomDetails = createRoomDetails(room);
        roomDescription.setWidth("100%");
        roomDetails.setWidth("100%");

        placeLayout.addComponent(roomDescription);
        placeLayout.addComponent(roomDetails);

        placeLayout.setSizeFull();
        placeLayout.setSpacing(true);
        return placeLayout;
    }

    private Table createRoomDetails(Room room) {
        Table roomDetails = new Table();


        roomDetails.addContainerProperty(LocaleText.getCaption("view.showroom.typeofbed"), String.class, null);
        roomDetails.addContainerProperty(LocaleText.getCaption("view.showroom.medicallabel"), String.class, null);
        roomDetails.addContainerProperty(LocaleText.getCaption("view.showroom.labelofaccessibility"), String.class, null);
        roomDetails.addContainerProperty(LocaleText.getCaption("view.showroom.typeofroom"), String.class, null);
        roomDetails.addContainerProperty(LocaleText.getCaption("view.showroom.occupationstatus"), String.class, null);

        int i=0;
        for(Place p:room.getPlaces()){
            roomDetails.addItem(new Object[]{p.getTypeBed(),
                  " ", // placeProperties.get("category.general").get("medicalLabel").get(i).toString(),
                  " ", // placeProperties.get("category.general").get("accessibilityLabel").get(i).toString(),
                    p.getTypePlace(), p.getOccupancyStatus()}, null);
            i++;
        }

        roomDetails.setPageLength(roomDetails.size());
        return roomDetails;
    }

    private Panel createRoomDescription(Room room) {
        Panel roomDescription = new Panel();
        VerticalLayout lines = new VerticalLayout();

        roomDescription.setCaption(LocaleText.getCaption("view.showroom.description"));

        HorizontalLayout numberOfBeds = new HorizontalLayout();
        numberOfBeds.setSizeFull();
        HorizontalLayout maxNumberOfBeds = new HorizontalLayout();
        maxNumberOfBeds.setSizeFull();
        HorizontalLayout availableBeds = new HorizontalLayout();
        availableBeds.setSizeFull();

        Label noBeds = new Label(LocaleText.getCaption("view.showroom.numberofbeds"));
        Label noBedsAns = new Label(Integer.toString(room.getNumberOfBeds()));
        numberOfBeds.addComponent(noBeds);
        numberOfBeds.addComponent(noBedsAns);
        numberOfBeds.setExpandRatio(noBeds, 2F);
        numberOfBeds.setExpandRatio(noBedsAns, 5F);

        Label maxNoBeds = new Label(LocaleText.getCaption("view.showroom.maxnumberofbeds"));
        Label maxNoBedsAns = new Label(Integer.toString(room.getMaxNumberOfBeds()));
        maxNumberOfBeds.addComponent(maxNoBeds);
        maxNumberOfBeds.addComponent(maxNoBedsAns);
        maxNumberOfBeds.setExpandRatio(maxNoBeds, 2F);
        maxNumberOfBeds.setExpandRatio(maxNoBedsAns, 5F);

        Label avNoBeds = new Label(LocaleText.getCaption("view.showroom.availablenumberofbeds"));
        Label avNoBedsAns = new Label(Integer.toString(room.getNumberOfAvailableBeds()));
        availableBeds.addComponent(avNoBeds);
        availableBeds.addComponent(avNoBedsAns);
        availableBeds.setExpandRatio(avNoBeds, 2F);
        availableBeds.setExpandRatio(avNoBedsAns, 5F);

        lines.addComponent(numberOfBeds);
        lines.addComponent(maxNumberOfBeds);
        lines.addComponent(availableBeds);

        roomDescription.setContent(lines);
        return roomDescription;
    }
}