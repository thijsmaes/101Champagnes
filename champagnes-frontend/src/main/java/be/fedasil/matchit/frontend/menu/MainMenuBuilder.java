package be.fedasil.matchit.frontend.menu;

import java.util.ArrayList;
import java.util.List;

import be.fedasil.matchit.frontend.component.ComponentBuilder;
import be.fedasil.matchit.frontend.util.LocaleText;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class MainMenuBuilder implements ComponentBuilder {

    @Override
    public Component build() {
        MainMenuView view = new MainMenuView();
        view.setMenuItems(buildMenuItems());
        return view;
    }

    private List<MainMenuItem> buildMenuItems() {
        List<MainMenuItem> items = new ArrayList<MainMenuItem>();
        MainMenuItem home = new MainMenuItem("mainmenu.home");
        home.setComponentBuilder(new LabelComponentBuilder("home.welcome"));
        MainMenuItem placeManagement = new MainMenuItem("mainmenu.placemanagement");
        placeManagement.setComponentBuilder(new LabelComponentBuilder("places.welcome"));
        SubMenuItem createPlace = new SubMenuItem();
        createPlace.setCaptionCode("submenu.placemanagement.createPlace");
        createPlace.setComponentBuilder(new LabelComponentBuilder("todo"));
        placeManagement.getSubMenuItems().add(createPlace);
        items.add(home);
        items.add(placeManagement);
        return items;
    }

    private class LabelComponentBuilder implements ComponentBuilder {

        private String messageCode;

        public LabelComponentBuilder(String messageCode) {
            super();
            this.messageCode = messageCode;
        }

        @Override
        public Component build() {
            return new Label(LocaleText.getMessage(messageCode));
        }

    }
}
