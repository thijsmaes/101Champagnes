package be.fedasil.matchit.frontend.component.properties;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.model.properties.Property;
import com.vaadin.ui.Panel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmaes on 29/05/2015.
 */
public class PropertiesPresenter implements PropertiesView.BtnPropertiesClickListener {
    private PropertiesView view;
    private PropertiesHolder propertiesHolder;
    PropertiesHolder.Category props;

    public PropertiesPresenter(PropertiesView view, PropertiesHolder propertiesHolder) {
        this.view = view;
        this.propertiesHolder=propertiesHolder;
        view.addListener(this);
    }

    @Override
    public void onButtonClicked(Panel panel) {
        if (panel.isVisible()) {
            panel.setVisible(false);
        } else {
            panel.setVisible(true);
        }
    }

    List<String> getCategoryNames(){
        List<String> categories = new ArrayList<>();
        for(String categoryName : propertiesHolder.keySet()){
            categories.add(categoryName);
        }
        return categories;
    }

    public List<String> getProperties(String categoryName) {
        List<String> properties = new ArrayList<>();
        props = propertiesHolder.get(categoryName);
        for(String property: props.keySet()){
            properties.add(property);
        }
        return properties;
    }

    public List<String> getAttributes(String property) {

        return (List) props.get(property);
    }
}
