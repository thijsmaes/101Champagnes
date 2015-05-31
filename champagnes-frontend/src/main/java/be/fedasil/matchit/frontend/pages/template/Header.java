package be.fedasil.matchit.frontend.pages.template;

import be.fedasil.matchit.frontend.util.LocaleText;
import be.fedasil.matchit.frontend.util.UIHelper;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by tmaes on 17/04/2015.
 */

public class Header extends CustomComponent {

	private static final long serialVersionUID = 3891398924518439130L;

	public Header()
    {
        HorizontalLayout firstLine = new HorizontalLayout();
        firstLine.setWidth("100%");
        Image fedasilLogo = new Image(null, new ThemeResource("img/logo_fr.png"));
//        Image fedasilLogo = UIHelper.getTranslatedImage(null, "img/FedAsil.png");
        fedasilLogo.setDescription("FedAsil");
        ThemeResource matchitLogoResource = new ThemeResource("img/logo_matchit.png");
        Image matchitLogo = new Image(null, matchitLogoResource);

        Label headerLabel = new Label(LocaleText.getCaption("header.welcome"));
        firstLine.addComponent(matchitLogo);
        firstLine.addComponent(headerLabel);
        firstLine.addComponent(fedasilLogo);
        firstLine.setComponentAlignment(matchitLogo, Alignment.MIDDLE_LEFT);
        firstLine.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
        firstLine.setComponentAlignment(fedasilLogo, Alignment.MIDDLE_RIGHT);
        firstLine.setSpacing(true);
        headerLabel.setWidthUndefined();

        setCompositionRoot(firstLine);
    }
}
