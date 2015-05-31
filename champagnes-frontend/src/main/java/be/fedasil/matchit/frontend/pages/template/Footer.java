package be.fedasil.matchit.frontend.pages.template;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Created by tmaes on 17/04/2015.
 */


public class Footer {


    public static HorizontalLayout getFooter(){
        HorizontalLayout hlTemplateFooter = new HorizontalLayout();
        hlTemplateFooter.setSizeFull();
        Label headerLabel = new Label("Copyright Fedasil");
        headerLabel.setSizeUndefined();
        hlTemplateFooter.addComponent(headerLabel);
        hlTemplateFooter.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
        return hlTemplateFooter;
    }
}
