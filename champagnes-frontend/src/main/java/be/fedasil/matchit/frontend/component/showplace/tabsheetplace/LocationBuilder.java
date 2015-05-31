package be.fedasil.matchit.frontend.component.showplace.tabsheetplace;

import be.fedasil.matchit.backend.model.Address;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.frontend.component.properties.PropertiesBuilder;
import be.fedasil.matchit.frontend.component.properties.PropertiesViewImpl;
import be.fedasil.matchit.frontend.util.LocaleText;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * Created by tmaes on 28/04/2015.
 */
public class LocationBuilder {

    Panel addressDetails;
    Panel propertyPanel;
    ReceptionCenter receptionCenter;
    private PropertiesHolder locationProperties;

    public VerticalLayout createAddress(Location location) {
        VerticalLayout addressLayout = new VerticalLayout();
        Label addressLabel = new Label(location.getReceptionCenter().getCodeLocation());


        receptionCenter = location.getReceptionCenter();
        locationProperties = location.getPropertiesHolder();

        addressDetails = createAddressDetails(location);
        addressDetails.setWidth("100%");
        addressDetails.setVisible(true);

//        addressLayout.addComponent(new PropertiesBuilder().setPropertiesHolder(locationProperties).build());
//        propertyPanel = new PropertiesViewImpl().createGlobalPropertiesPanel(locationProperties);
        propertyPanel = (Panel) new PropertiesBuilder().setPropertiesHolder(locationProperties).build();

        addressLayout.addComponent(addressLabel);
        addressLayout.addComponent(addressDetails);

        addressLayout.addComponent(propertyPanel);

        addressLayout.setSpacing(true);

        return addressLayout;
    }

    private Panel createAddressDetails(Location location) {
        Address address = location.getAddressLocation();
        Panel detailsFrame = new Panel();
        HorizontalLayout hl6 = new HorizontalLayout();
        VerticalLayout detailsColumn1 = new VerticalLayout();
        VerticalLayout detailsColumn2 = new VerticalLayout();
        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        HorizontalLayout hl3 = new HorizontalLayout();
        HorizontalLayout hl4 = new HorizontalLayout();
        HorizontalLayout hl5 = new HorizontalLayout();

        detailsFrame.setContent(hl6);
        hl6.addComponents(detailsColumn1, detailsColumn2);
        hl6.setExpandRatio(detailsColumn1, 4F);
        hl6.setExpandRatio(detailsColumn2, 5F);
        detailsColumn1.addComponents(hl1, hl2);
        detailsColumn2.addComponents(hl3, hl4, hl5);
        detailsColumn1.setMargin(new MarginInfo(false, true, false, false));
        detailsColumn2.setMargin(new MarginInfo(false, false, false, false));

        //First column of first panel
        hl1.setSizeFull();
        hl2.setSizeFull();
        hl3.setSizeFull();
        hl4.setSizeFull();
        hl5.setSizeFull();
        hl6.setSizeFull();

        Label addressLabel = new Label(LocaleText.getCaption("view.showcontact.address"));
        Label addressAns = new Label(address.getStreet() + " " + address.getNumber() + "<br>" +
                address.getZipCode() + " " + address.getCity(), ContentMode.HTML);
        hl1.addComponents(addressLabel, addressAns);
        hl1.setExpandRatio(addressLabel, 3F);
        hl1.setExpandRatio(addressAns, 5F);

        Label floor = new Label(LocaleText.getCaption("view.showcontact.floor"));
        Label floorAns = new Label(location.getFloor());
        hl2.addComponents(floor, floorAns);
        hl2.setExpandRatio(floor, 3F);
        hl2.setExpandRatio(floorAns, 5F);

        //Second column of first panel
        hl3.setSizeFull();
        hl4.setSizeFull();
        hl5.setSizeFull();

        Label box = new Label(LocaleText.getCaption("view.showcontact.box"));
        Label boxAns = new Label(address.getPostalBox());
        hl3.addComponents(box, boxAns);
        hl3.setExpandRatio(box, 3F);
        hl3.setExpandRatio(boxAns, 5F);

        Label block = new Label(LocaleText.getCaption("view.showcontact.block"));
        Label blockAns = new Label(location.getBlock());
        hl4.addComponents(block, blockAns);
        hl4.setExpandRatio(block, 3F);
        hl4.setExpandRatio(blockAns, 5F);

      /*  Label environment = new Label(LocaleText.getCaption("view.showcontact.environment"));
        Label environmentAns = new Label(location.getAddressLocation().get);
        hl5.addComponents(environment, environmentAns);
        hl5.setExpandRatio(environment, 3F);
        hl5.setExpandRatio(environmentAns, 5F);*/

//        hl6.setSpacing(true);

        return detailsFrame;
    }
}
