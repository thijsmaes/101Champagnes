package be.fedasil.matchit.frontend.component.properties;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.model.properties.Property;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by tmaes on 28/05/2015.
 */
public class PropertiesViewImpl /*extends Panel*/ implements PropertiesView {

    private BtnPropertiesClickListener btnPropertiesClickListener;
    private PropertiesPresenter propertiesPresenter;

    public PropertiesViewImpl() {}

    @Override
    public void addListener(BtnPropertiesClickListener btnPropertiesClickListener) {
        this.btnPropertiesClickListener = btnPropertiesClickListener;
    }

    @Override
    public Button createButton(String propertyName, final Panel component) {
        Button button = new Button(propertyName);
        button.setSizeFull();
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnPropertiesClickListener.onButtonClicked(component);
            }
        });
        return button;
    }

    @Override
    public Panel createComponent(String categoryName) {
        Panel propertyPanel = new Panel();
        VerticalLayout vl = new VerticalLayout();
        List<String> properties = propertiesPresenter.getProperties(categoryName);
        for (String property : properties) {
            List<String> attributes = propertiesPresenter.getAttributes(property);
            HorizontalLayout hl = new HorizontalLayout();
            hl.setSpacing(true);
            hl.addComponent(new Label(property + ": "));
            String prefix = "";
            for (String attribute : attributes) {
                hl.addComponent(new Label(prefix));
                prefix = ", ";
                hl.addComponent(new Label(attribute));
            }
            vl.addComponent(hl);
        }
        propertyPanel.setContent(vl);
        return propertyPanel;
    }

    @Override
    public Panel createGlobalPropertiesPanel(PropertiesPresenter propertiesPresenter
    ) {
        this.propertiesPresenter = propertiesPresenter;
        Panel properties = new Panel();
        VerticalLayout propertiesLayout = new VerticalLayout();
        propertiesLayout.setSpacing(true);
        Panel panel;
        List<String> categoryNames = propertiesPresenter.getCategoryNames();
        for (String categoryName : categoryNames) {
            panel = new Panel();
            VerticalLayout vl = new VerticalLayout();
            Panel propertyPanel = createComponent(categoryName);
            propertyPanel.setVisible(false);
            Button button = createButton(categoryName, propertyPanel);
            vl.addComponents(button, propertyPanel);
            panel.setContent(vl);
            propertiesLayout.addComponent(panel);
        }
        properties.setContent(propertiesLayout);
        return properties;
    }
}
