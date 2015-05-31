package be.fedasil.matchit.frontend.component.showplace.tabsheetplace;

import be.fedasil.matchit.backend.facade.CodeLabelFacade;
import be.fedasil.matchit.backend.model.CodeLabel;
import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.frontend.util.LocaleText;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by tmaes on 28/04/2015.
 */
public class PlaceBuilder {
private Place place;
private PropertiesHolder propertiesHolder;

    public VerticalLayout createPlace(Place place) {
        this.place = place;
        propertiesHolder = place.getPropertiesHolder();

        VerticalLayout placeLayout = new VerticalLayout();
        /*Label address = new Label(LocaleText.getCaption("view.showcontact.address") + " " + place.getRoom().getAddressPlace().getStreet() + " " +
                place.getRoom().getAddressPlace().getNumber() + ", " + place.getRoom().getAddressPlace().getPostalBox() + ", " +
                place.getRoom().getAddressPlace().getCity());*/

        Panel intro = createPl(place);
        Panel description = createDescription(place);

        intro.setWidth("100%");
        description.setWidth("100%");

//        placeLayout.addComponent(address);
        placeLayout.addComponent(intro);
        placeLayout.addComponent(description);

        placeLayout.setSizeFull();
        placeLayout.setSpacing(true);
        return placeLayout;
    }

    private Panel createDescription(Place place) {
    	
        Panel description = new Panel(LocaleText.getCaption("Description"));
        VerticalLayout lines = new VerticalLayout();
        HorizontalLayout targetGroup = new HorizontalLayout();
        targetGroup.setSizeFull();
        HorizontalLayout medicalLabel = new HorizontalLayout();
        medicalLabel.setSizeFull();
        HorizontalLayout extraInfo = new HorizontalLayout();
        extraInfo.setSizeFull();

        Label group = new Label(LocaleText.getCaption("view.showplace.targetgroup"));
        Label groupAns = new Label(createTargetGroupLabel());
        targetGroup.addComponent(group);
        targetGroup.addComponent(groupAns);
        targetGroup.setExpandRatio(group, 2F);
        targetGroup.setExpandRatio(groupAns, 5F);

        Label medical = new Label(LocaleText.getCaption("view.showplace.medicallabel"));

       Label medicalAns = new Label(createMedicalLabel());



        medicalLabel.addComponent(medical);
        medicalLabel.addComponent(medicalAns);
        medicalLabel.setExpandRatio(medical, 2F);
        medicalLabel.setExpandRatio(medicalAns, 5F);

        Label xtraInfo = new Label(LocaleText.getCaption("view.showplace.extrainformation"));
        Label xtraInfoAns = new Label(createExtraInfoLabel());
        extraInfo.addComponent(xtraInfo);
        extraInfo.addComponent(xtraInfoAns);
        extraInfo.setExpandRatio(xtraInfo, 2F);
        extraInfo.setExpandRatio(xtraInfoAns, 5F);

        lines.addComponent(targetGroup);
        lines.addComponent(medicalLabel);
        lines.addComponent(extraInfo);

        description.setContent(lines);
//        description.setWidth("80%");

        return description;
    }

    private String createExtraInfoLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        String label="";
        for(int i=0; i<propertiesHolder.get("category.general").get("extraInfo").size(); i++) {
            stringBuilder.append(propertiesHolder.get("category.general").get("extraInfo").get(i).toString());
            if (i < propertiesHolder.get("category.general").get("extraInfo").size() - 1) {
                stringBuilder.append(", ");
            }
            label = stringBuilder.toString();
        }
        return label;
    }

    private String createTargetGroupLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        String label="";
        for(int i=0; i<propertiesHolder.get("category.general").get("targetGroup").size(); i++) {
            stringBuilder.append(propertiesHolder.get("category.general").get("targetGroup").get(i).toString());
            if (i < propertiesHolder.get("category.general").get("targetGroup").size() - 1) {
                stringBuilder.append(", ");
            }
            label = stringBuilder.toString();
        }
        return label;
    }

    private String createMedicalLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        String label="";
        for(int i=0; i<propertiesHolder.get("category.general").get("medicalLabel").size(); i++) {
            stringBuilder.append(/*new CodeLabelFacade().getCodeLabels() */propertiesHolder.get("category.general").get("medicalLabel").get(i).toString());
            if (i < propertiesHolder.get("category.general").get("medicalLabel").size() - 1) {
                stringBuilder.append(", ");
            }
            label = stringBuilder.toString();
        }
        return label;
    }

    private Panel createPl(Place place) {
        Panel intro = new Panel();
        VerticalLayout lines = new VerticalLayout();

        HorizontalLayout id = new HorizontalLayout();
        id.setSizeFull();
        HorizontalLayout availability = new HorizontalLayout();
        availability.setSizeFull();
        HorizontalLayout con = new HorizontalLayout();
        con.setSizeFull();
        HorizontalLayout type = new HorizontalLayout();
        type.setSizeFull();
        HorizontalLayout bedType = new HorizontalLayout();
        bedType.setSizeFull();

        Label identification = new Label(LocaleText.getCaption("view.showplace.identification"));
        Label idNumber = new Label(String.valueOf(place.getPlaceId()));
        id.addComponent(identification);
        id.addComponent(idNumber);
        id.setExpandRatio(identification, 2F);
        id.setExpandRatio(idNumber, 5F);

        Label ynOccupied = new Label(LocaleText.getCaption("view.showplace.ocupationstatus"));
        Label ynOccupiedAns = new Label(place.getOccupancyStatus());
        availability.addComponent(ynOccupied);
        availability.addComponent(ynOccupiedAns);
        availability.setExpandRatio(ynOccupied, 2F);
        availability.setExpandRatio(ynOccupiedAns, 5F);

        Label conventionedAns = new Label(place.getConventionedPlace());
        Label conventioned = new Label(place.getConventionedPlace());
        con.addComponent(conventioned);
        con.addComponent(conventionedAns);
        con.setExpandRatio(conventioned, 2F);
        con.setExpandRatio(conventionedAns, 5F);

        Label placeType = new Label(LocaleText.getCaption("view.showplace.typeofroom"));
        Label placeTypeAns = new Label(place.getTypePlace());
        type.addComponent(placeType);
        type.addComponent(placeTypeAns);
        type.setExpandRatio(placeType, 2F);
        type.setExpandRatio(placeTypeAns, 5F);

        Label typeOfBed = new Label(LocaleText.getCaption("view.showplace.typeofbed"));
        Label typeOfBedAns = new Label(place.getTypeBed());
        bedType.addComponent(typeOfBed);
        bedType.addComponent(typeOfBedAns);
        bedType.setExpandRatio(typeOfBed, 2F);
        bedType.setExpandRatio(typeOfBedAns, 5F);

        lines.addComponent(id);
        lines.addComponent(availability);
        lines.addComponent(con);
        lines.addComponent(type);
        lines.addComponent(bedType);

        intro.setContent(lines);
        return intro;
    }
}
