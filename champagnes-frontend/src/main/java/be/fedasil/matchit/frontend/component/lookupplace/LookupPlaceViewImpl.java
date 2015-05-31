package be.fedasil.matchit.frontend.component.lookupplace;

import be.fedasil.matchit.frontend.util.EnterKeyHandler;
//import be.fedasil.matchit.frontend.utils.EnterKeyHandlerExt;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class LookupPlaceViewImpl extends VerticalLayout implements LookupPlaceView {

    private static final long serialVersionUID = 1665028802309450706L;
    private LookupViewListener listener;
    private TextField txtPlaceId;
    private TextField txtPersonId;
    Panel lookupPlace;
    Panel lookupPerson;

    @Override
    public void addListener(LookupViewListener listener) {
        this.listener = listener;
    }

    /**
     * package restricted constructor. Should only be instantiated by the builder
     */
    @SuppressWarnings("serial")
    LookupPlaceViewImpl() {
        setSpacing(true);
        addComponent(lookupPerson());
        addComponent(lookupPlace());

        new EnterKeyHandler() {
            @Override
            public void onEnterKeyPressed() {
                listener.lookup(txtPlaceId.getValue());
            }
        }.installOn(txtPlaceId);
    }

    private Panel lookupPerson() {
        lookupPerson = new Panel();
        lookupPerson.setSizeUndefined();
        HorizontalLayout hlLookupPerson = new HorizontalLayout();
        txtPersonId = new TextField();
        txtPersonId.setImmediate(true);
        txtPersonId.addStyleName("borderless");
        new EnterKeyHandler() {
            @Override
            public void onEnterKeyPressed() {
                listener.lookup(txtPersonId.getValue());
            }
        }.installOn(txtPersonId);

        final Button btnLookup = new Button();
        btnLookup.setIcon(new ThemeResource("icons/personico.png"));
        btnLookup.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnLookup.addStyleName("borderless");
        btnLookup.addClickListener(new ClickListener() {

            /**
             * On click of the lookup button, pass the entered id to the listener.
             */
            @Override
            public void buttonClick(ClickEvent event) {
                listener.lookup(txtPersonId.getValue());
            }
        });

        hlLookupPerson.addComponents(txtPersonId, btnLookup);
        lookupPerson.setContent(hlLookupPerson);

        return lookupPerson;
    }

    private Panel lookupPlace() {
        lookupPlace = new Panel();
        lookupPlace.setSizeUndefined();
        HorizontalLayout hlLookupPlace = new HorizontalLayout();
        txtPlaceId = new TextField();
        txtPlaceId.setImmediate(true);
        txtPlaceId.addStyleName("borderless");
        new EnterKeyHandler() {
            @Override
            public void onEnterKeyPressed() {
                listener.lookup(txtPlaceId.getValue());
            }
        }.installOn(txtPlaceId);

        final Button btnLookup = new Button();
        btnLookup.setIcon(new ThemeResource("icons/houseico.png"));
        btnLookup.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnLookup.addStyleName("borderless");

        btnLookup.addClickListener(new ClickListener() {

            /**
             * On click of the lookup button, pass the entered id to the listener.
             */
            @Override
            public void buttonClick(ClickEvent event) {
                listener.lookup(txtPlaceId.getValue());
            }
        });
        hlLookupPlace.addComponents(txtPlaceId, btnLookup);
        lookupPlace.setContent(hlLookupPlace);

        return lookupPlace;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Notification.show("Error", errorMessage, Notification.Type.WARNING_MESSAGE);
    }
}
