package be.fedasil.matchit.frontend.pages.template;

import be.fedasil.matchit.frontend.FedasilUI;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.frontend.component.ComponentBuilder;
import be.fedasil.matchit.frontend.component.lookupplace.LookupPlaceBuilder;
import be.fedasil.matchit.frontend.component.lookupplace.LookupPlaceBuilder.LookupPlaceListener;
import be.fedasil.matchit.frontend.component.showplace.ShowPlaceBuilder;
import be.fedasil.matchit.frontend.menu.MainMenuBuilder;
import be.fedasil.matchit.frontend.menu.MainMenuItem;
import be.fedasil.matchit.frontend.menu.SubMenuItem;
import be.fedasil.matchit.frontend.menu.SubMenuView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractSingleComponentContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * A template controls the overall page layout. It has following layout:
 * <p/>
 * <pre>
 *  +-------------------------+
 *  |         Header          |
 *  +-------------------------+
 *  | Main Menu               |
 *  +----------+--------------+
 *  | Quick-   |              |
 *  | Search   |              |
 *  +----------+              |
 *  | SubMenu  |    Body      |
 *  |          |              |
 *  +----------+              |
 *  | Quick-   |              |
 *  |  Link    |              |
 *  +----------+--------------|
 *  |         Footer          |
 *  +-------------------------+
 * </pre>
 *
 * @author wdewit
 */
public class NormalPageViewImpl extends VerticalLayout implements PageView, View, LookupPlaceListener {
    private static final MatchitLogger logger = MatchitLoggerFactory.getLogger(NormalPageViewImpl.class);
    private static final long serialVersionUID = 2214712538561739489L;

    /**
     * Container for the body part of the view
     */
    private VerticalLayout mainLayout;
    private SubMenuView subMenuPart;
    private PagePresenter pagePresenter;
    private VerticalLayout bodyPart;

    public NormalPageViewImpl() {
		logger.debug("New NormalPageViewImpl");
        pagePresenter = FedasilUI.get().getCurrentPagePresenter();
        if (pagePresenter == null)
            FedasilUI.get().setCurrentPagePresenter(pagePresenter = new NormalPagePresenter(this));
    }

    @Override
    public void enter(ViewChangeEvent event) {
		logger.debug("NormalPageViewImpl.enter:" + event);

        removeAllComponents();
        setSizeFull();
        Component languageBar = new LanguageBar();
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("970px");
        mainLayout.setHeight("100%");
        mainLayout.setSpacing(true);
        addComponent(languageBar);
        setComponentAlignment(languageBar, Alignment.TOP_CENTER);
        addComponent(mainLayout);
        setExpandRatio(languageBar, 1F);
        setExpandRatio(mainLayout, 15F);

        Component header = new Header();
        header.setSizeFull();
        mainLayout.addComponent(header);

        HorizontalLayout menu = (HorizontalLayout) new MainMenuBuilder().build();
        mainLayout.addComponent(menu);

        HorizontalLayout centralSection = new HorizontalLayout();
        VerticalLayout leftPart = new VerticalLayout();
        leftPart.addComponent(new LookupPlaceBuilder().setLookupListener(this).build());
        leftPart.addComponent(subMenuPart = new SubMenuView(pagePresenter));

        centralSection.addComponent(leftPart);
        centralSection.addComponent(bodyPart = new VerticalLayout());
        centralSection.setExpandRatio(leftPart, 3F);
        centralSection.setExpandRatio(bodyPart, 10F);
        centralSection.setSpacing(true);
        centralSection.setSizeFull();
        mainLayout.addComponent(centralSection);

        HorizontalLayout footer = Footer.getFooter();
        footer.setSizeFull();
        mainLayout.addComponent(footer);

        setComponentAlignment(mainLayout, Alignment.TOP_CENTER);
        mainLayout.setExpandRatio(header, 2F);
        mainLayout.setExpandRatio(menu, 1F);
        mainLayout.setExpandRatio(centralSection, 16F);
        mainLayout.setExpandRatio(footer, 1F);

        mainLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(centralSection, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(footer, Alignment.BOTTOM_CENTER);
    }

    @Override
    public void selectedPlace(Place place) {
        setBody(new ShowPlaceBuilder().setPlace(place));
    }

    @Override
    public void setCurrentMenuItem(MainMenuItem menuItem) {
        subMenuPart.setSubMenuItems(menuItem.getSubMenuItems());
    }

    @Override
    public void setCurrentSubMenuItem(SubMenuItem subMenuItem) {
        // TODO visualize the submenu selection
        setBody(subMenuItem.getComponentBuilder());
    }

	/**
	 * Replaces the body part with the Component returned by the {@link ComponentBuilder#build() builder.build()} method.
	 * @param builder required ComponentBuilder instance
	 */
   @Override
    public void setBody(ComponentBuilder builder) {
		bodyPart.removeAllComponents();
        bodyPart.addComponent(builder.build());
    }
}
