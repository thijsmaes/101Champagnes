package be.fedasil.matchit.frontend.component.showplace.tabsheetplace;

import be.fedasil.matchit.backend.model.Contact;
import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.frontend.util.LocaleText;
import com.vaadin.ui.*;
import org.vaadin.toolbarwindow.ToolbarWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tmaes on 28/04/2015.
 */
public class StructureBuilder {
    private ReceptionCenter receptionCenter;

    public VerticalLayout createStructure(ReceptionCenter receptionCenter) {

        this.receptionCenter = receptionCenter;

        VerticalLayout structureLayout = new VerticalLayout();
        Label address = new Label(receptionCenter.getCodeLocation());

        Panel generalInfo = createReceptionCenter(receptionCenter);
        Panel contacts = createContacts(receptionCenter);

        generalInfo.setWidth("100%");
        contacts.setWidth("100%");

        structureLayout.addComponent(address);
        structureLayout.addComponent(generalInfo);
        structureLayout.addComponent(contacts);

        structureLayout.setSizeFull();
        structureLayout.setSpacing(true);
        return structureLayout;
    }

    private Panel createContacts(ReceptionCenter receptionCenter) {
        Panel contacts = new Panel(LocaleText.getCaption("view.showplace.contacts"));
        List<Contact> cs = receptionCenter.getContacts();

        VerticalLayout contactLayout = new VerticalLayout();

        int i = 0;

        for (Contact c : cs){
            HorizontalLayout hl1 = new HorizontalLayout();
            hl1.setSizeFull();
            Label titleContact = new Label(c.getService());
            Label nameContact = new Label(c.getName());
            hl1.addComponents(titleContact, nameContact);
            hl1.setExpandRatio(titleContact, 2F);
            hl1.setExpandRatio(nameContact, 5F);

            HorizontalLayout hl2 = new HorizontalLayout();
            hl2.setSizeFull();
            Label email = new Label(LocaleText.getCaption("view.showcontact.email"));
            Label emailAns = new Label(c.getEmailAddress());
            hl2.addComponents(email, emailAns);
            hl2.setExpandRatio(email, 2F);
            hl2.setExpandRatio(emailAns, 5F);

            HorizontalLayout hl3 = new HorizontalLayout();
            hl3.setSizeFull();
            Label telephone = new Label(LocaleText.getCaption("view.showcontact.telephone"));
            Label telephoneAns = new Label(c.getPhoneNumber());
            hl3.addComponents(telephone, telephoneAns);
            hl3.setExpandRatio(telephone, 2F);
            hl3.setExpandRatio(telephoneAns, 5F);

            contactLayout.addComponents(hl1, hl2, hl3);
            if(i<(cs.size()-1)){
                contactLayout.addComponent(new Label(" "));
            }
            i++;
        }
        contacts.setContent(contactLayout);
        return contacts;
    }

    private Panel createReceptionCenter(ReceptionCenter receptionCenter) {
        Panel receptionCentre = new Panel(LocaleText.getCaption("view.showcontact.receptioncenter"));
        VerticalLayout lines = new VerticalLayout();

        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        HorizontalLayout hl3 = new HorizontalLayout();
        HorizontalLayout hl4 = new HorizontalLayout();
        HorizontalLayout hl5 = new HorizontalLayout();
        HorizontalLayout hl6 = new HorizontalLayout();
        hl1.setSizeFull();
        hl2.setSizeFull();
        hl3.setSizeFull();
        hl4.setSizeFull();
        hl5.setSizeFull();
        hl6.setSizeFull();

        Label responsibility = new Label(LocaleText.getCaption("view.showcontact.responsable"));
        Label responsibilityAns = new Label(receptionCenter.getOrganization());
        hl1.addComponents(responsibility, responsibilityAns);
        hl1.setExpandRatio(responsibility, 2F);
        hl1.setExpandRatio(responsibilityAns, 5F);

        Label structureType = new Label(LocaleText.getCaption("view.showcontact.structuretype"));
        Label structureTypeAns = new Label(receptionCenter.getStructureType());
        hl2.addComponents(structureType, structureTypeAns);
        hl2.setExpandRatio(structureType, 2F);
        hl2.setExpandRatio(structureTypeAns, 5F);

        Label maximumNrOfPlaces = new Label(LocaleText.getCaption("view.showcontact.maxnrofplaces"));
        //another locale available stating the same (max number of beds)
        Label maximumNrOfPlacesAns = new Label(Integer.toString(receptionCenter.getMaxNumberOfPlaces()));
        hl3.addComponents(maximumNrOfPlaces, maximumNrOfPlacesAns);
        hl3.setExpandRatio(maximumNrOfPlaces, 2F);
        hl3.setExpandRatio(maximumNrOfPlacesAns, 5F);

        Label address = new Label(LocaleText.getCaption("view.showcontact.address"));
        Label addressAns = new Label(receptionCenter.getContactAddressId().toString());
        hl4.addComponents(address, addressAns);
        hl4.setExpandRatio(address, 2F);
        hl4.setExpandRatio(addressAns, 5F);

        Label email = new Label(LocaleText.getCaption("view.showcontact.email"));
        Label emailAns = new Label(receptionCenter.getEmailAddress());
        hl5.addComponents(email, emailAns);
        hl5.setExpandRatio(email, 2F);
        hl5.setExpandRatio(emailAns, 5F);

        Label telephone = new Label(LocaleText.getCaption("view.showcontact.telephone"));
        Label telephoneAns = new Label(receptionCenter.getPhoneNumber());
        hl6.addComponents(telephone, telephoneAns);
        hl6.setExpandRatio(telephone, 2F);
        hl6.setExpandRatio(telephoneAns, 5F);

        lines.addComponents(hl1, hl2, hl3, hl4, hl5, hl6);
        receptionCentre.setContent(lines);

        return receptionCentre;
    }
}
