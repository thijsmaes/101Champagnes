package be.fedasil.matchit.frontend.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.fedasil.matchit.frontend.FedasilUI;
import be.fedasil.matchit.frontend.util.LocaleText;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainMenuView extends HorizontalLayout {

    private static final long serialVersionUID = 2739346173546549933L;
    private Map<MainMenuItem, Button> viewButtons = new HashMap<MainMenuItem, Button>();
//    private HorizontalLayout navigationBar;

    public MainMenuView() {
        setStyleName("m-menubar");
    }

    public MainMenuView setMenuItems(List<MainMenuItem> items) {
        for (MainMenuItem item : items)
            createViewButton(item);
        return this;
    }

    private void createViewButton(final MainMenuItem menuItem) {
        @SuppressWarnings("serial")
        Button button = new Button(LocaleText.getCaption(menuItem.getCaptionCode()), new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                FedasilUI.get().getCurrentPagePresenter().onMenuSelected(menuItem);
            }
        });

//        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addStyleName("m-menubutton");

        button.setSizeUndefined();
        addComponent(button);
        viewButtons.put(menuItem, button);
    }

    /**
     * Highlights a view navigation button as the currently active view in the
     * menu. This method does not perform the actual navigation.
     *
     * @param menuItem the name of the view to show as active
     */
    public void setActiveView(MainMenuItem menuItem) {
        for (Button button : viewButtons.values()) {
            button.removeStyleName("m-selected");
        }
        Button selected = viewButtons.get(menuItem);
        if (selected != null) {
            selected.addStyleName("m-selected");
        }
    }
}
