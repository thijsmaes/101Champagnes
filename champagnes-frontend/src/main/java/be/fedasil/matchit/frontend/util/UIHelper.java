package be.fedasil.matchit.frontend.util;

import java.util.Locale;

import com.vaadin.event.FieldEvents;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UIHelper {

    public interface SubmitListener {
        void onSubmit();
        Link link = new Link();
    }

    /**
     * Creates a component to put at the top of the page to switch to a specific
     * language. The onClick of the component is defined to let the UI switch to
     * the selected language.
     *
     * @param language 2-char language code: nl, fr
     * @return Component ready to put on the view.
     */
    @SuppressWarnings("serial")
    public static Component languageSwitcher(final String language) {
       /* final Button lngButton = new Button(language);
       lngButton.addStyleName("m-langbutton");
        if (language.equals(UI.getCurrent().getLocale().getLanguage())){
            lngButton.addStyleName("m-selected");
        }
            lngButton.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    UI.getCurrent().setLocale(new Locale(language));
                    UI.getCurrent().getNavigator().navigateTo("home");
                }
            });
        return lngButton;
        */

        final Button lngButton = new Button(language);
        lngButton.setStyleName("m-langbutton");
        if (language.equals(UI.getCurrent().getLocale().getLanguage())){
            lngButton.addStyleName("m-selected");
        }
        lngButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().setLocale(new Locale(language));
                UI.getCurrent().getNavigator().navigateTo("home");
            }
        });
        return lngButton;
    }

    public String whatEver(){


//        lngButton.setWidthUndefined();
//        lngButton.setSizeUndefined();

        //padding-right: 0.8em;
        return " ";
    }

    /**
     * Looks for the image defined in resourcePath for the requested language.
     * Example:
     * <p/>
     * <pre>
     * resourcePath = "images/logo.png"
     * language = "nl"
     * </pre>
     * <p/>
     * it will search for "images/logo_nl.png". if not found : "images/logo.png"
     *
     * @param caption
     * @param resourcePath
     * @param language
     * @return
     */
    public static Image getTranslatedImage(String caption, String resourcePath) {
        String[] resourceParts = resourcePath.split("\\.");
        Resource img = new ThemeResource(resourceParts[0] + "_" + getCurrentLanguage() + "." + resourceParts[1]);
        return new Image(caption, img);
    }

    public static String getCurrentLanguage() {
        return UI.getCurrent().getLocale().getLanguage();
    }
}
