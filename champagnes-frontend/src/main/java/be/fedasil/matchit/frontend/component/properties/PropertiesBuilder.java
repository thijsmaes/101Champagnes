package be.fedasil.matchit.frontend.component.properties;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.frontend.component.ComponentBuilder;
import com.vaadin.ui.Component;

/**
 * Created by tmaes on 29/05/2015.
 */
public class PropertiesBuilder implements ComponentBuilder{

    private PropertiesHolder propertiesHolder;
    private PropertiesPresenter propertiesPresenter;

    public PropertiesBuilder setPropertiesHolder(PropertiesHolder propertiesHolder){
        this.propertiesHolder=propertiesHolder;
        return this;
    }

    @Override
    public Component build() {
        PropertiesViewImpl view = new PropertiesViewImpl();
        propertiesPresenter = new PropertiesPresenter(view, propertiesHolder);

        return view.createGlobalPropertiesPanel(propertiesPresenter);
    }
}


//new PropertiesBuilder().setPropertiesHolder(propertiesHolder).build();