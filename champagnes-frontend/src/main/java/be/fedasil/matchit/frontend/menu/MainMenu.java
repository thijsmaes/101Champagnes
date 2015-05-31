package be.fedasil.matchit.frontend.menu;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainMenu extends CustomComponent {

	private static final long serialVersionUID = 2739346173546549933L;
    private Navigator navigator;
    private Map<String, Button> viewButtons = new HashMap<String, Button>();
    private HorizontalLayout navigationBar;
	public MainMenu(Navigator navigator) {
		this.navigator=navigator;
		navigationBar = new HorizontalLayout();
		navigationBar.setHeightUndefined();
		navigationBar.setSpacing(true);
//		navigationBar.setWidthUndefined();
		navigationBar.setSizeFull();
        navigationBar.setPrimaryStyleName(ValoTheme.MENU_ROOT);
		setCompositionRoot(navigationBar);

	}
    public void addView(View view, final String name, String caption,
            Resource icon) {
        createViewButton(name, caption, icon);
    }
    public void addView(Class<? extends View> viewClass, final String name,
            String caption, Resource icon) {
        createViewButton(name, caption, icon);
    }

    private void createViewButton(final String name, String caption,
            Resource icon) {
        @SuppressWarnings("serial")
		Button button = new Button(caption, new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo(name);
            }
        });
//        button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
//        button.addStyleName("m-menubar-item");
        button.addStyleName("m-menubutton");
        if(icon!=null)
        	button.setIcon(icon);
        navigationBar.addComponent(button);
        viewButtons.put(name, button);
    }

    /**
     * Highlights a view navigation button as the currently active view in the
     * menu. This method does not perform the actual navigation.
     *
     * @param viewName
     *            the name of the view to show as active
     */
    public void setActiveView(String viewName) {
        for (Button button : viewButtons.values()) {
            button.removeStyleName("selected");
        }
        Button selected = viewButtons.get(viewName);
        if (selected != null) {
            selected.addStyleName("selected");
        }
    }
}
