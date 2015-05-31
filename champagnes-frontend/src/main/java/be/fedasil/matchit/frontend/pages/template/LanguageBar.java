package be.fedasil.matchit.frontend.pages.template;

import be.fedasil.matchit.frontend.util.UIHelper;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by tmaes on 7/05/2015.
 */
public class LanguageBar extends HorizontalLayout {
	private static ThemeResource blgLogoRes=new ThemeResource("img/blgm_beLogo.gif");

    public LanguageBar(){
    	Image imgBE=new Image(null,blgLogoRes);
        HorizontalLayout languageBar=new HorizontalLayout();
        languageBar.setStyleName("m-languagebar");
//        languageBar.setSpacing(true);
        languageBar.addComponent(UIHelper.languageSwitcher("nl"));
        languageBar.addComponent(UIHelper.languageSwitcher("fr"));
        languageBar.addComponent(UIHelper.languageSwitcher("en"));
        languageBar.setWidthUndefined();
        HorizontalLayout languageLine=new HorizontalLayout();
        languageLine.addStyleName("m-languagePanel");
        languageLine.addComponent(languageBar);
        languageLine.setWidth("100%");
        addComponents(languageLine,imgBE);
        setComponentAlignment(languageLine, Alignment.TOP_LEFT);
        setComponentAlignment(imgBE, Alignment.BOTTOM_RIGHT);
        setExpandRatio(languageLine, 100);
        setExpandRatio(imgBE, 1);
        setWidth("970px");
    }
}
