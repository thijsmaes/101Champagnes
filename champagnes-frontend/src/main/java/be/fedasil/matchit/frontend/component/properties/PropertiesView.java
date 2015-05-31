package be.fedasil.matchit.frontend.component.properties;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;

import java.util.List;

/**
 * Created by tmaes on 28/05/2015.
 */
public interface PropertiesView {

    interface BtnPropertiesClickListener {
        void onButtonClicked(Panel panel);
    }

    Button createButton(String propertyName, Panel panel);

    Panel createComponent(String propertyName);

    Panel createGlobalPropertiesPanel(PropertiesPresenter propertiesPresenter);

    void addListener(BtnPropertiesClickListener btnPropertiesClickListener);
}
