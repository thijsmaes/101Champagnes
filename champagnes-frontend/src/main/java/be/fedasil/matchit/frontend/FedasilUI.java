package be.fedasil.matchit.frontend;

import javax.servlet.annotation.WebServlet;

import be.fedasil.matchit.frontend.pages.template.NormalPagePresenter;
import be.fedasil.matchit.frontend.pages.template.NormalPageViewImpl;
import be.fedasil.matchit.frontend.pages.template.PagePresenter;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 *
 * The @Viewport annotation configures the viewport meta tags appropriately on
 * mobile devices. Instead of device based scaling (default), using responsive
 * layouts.
 */
/*
 * @Viewport("user-scalable=no,initial-scale=1.0")
 */
@Theme("mytheme")
@Widgetset("be.fedasil.matchit.MyAppWidgetset")
public class FedasilUI extends UI {

	private static final long serialVersionUID = -7899453212774486600L;

	private PagePresenter currentPagePresenter;
	

	/*
	 * @Override protected void init(VaadinRequest vaadinRequest) {
	 * Responsive.makeResponsive(this); setLocale(vaadinRequest.getLocale());
	 * getPage().setTitle("My"); if (!accessControl.isUserSignedIn()) {
	 * setContent(new LoginScreen(accessControl, new LoginListener() {
	 * 
	 * @Override public void loginSuccessful() { showMainView(); } })); } else {
	 * showMainView(); } }
	 */

	@Override
	protected void init(VaadinRequest request) {

		//final Panel panel = new Panel();
		CssLayout panel = new CssLayout();
//		panel.addStyleName("valo-content");
		panel.setSizeFull();

		Navigator navigator = new Navigator(getUI(), panel);
		setNavigator(navigator);
		NormalPageViewImpl homeView=new NormalPageViewImpl();
		navigator.addView("home", homeView);
		setContent(panel);
		setCurrentPagePresenter(new NormalPagePresenter(homeView));
		navigator.navigateTo("home");
	}
	public PagePresenter getCurrentPagePresenter() {
		return currentPagePresenter;
	}

	public void setCurrentPagePresenter(PagePresenter currentPagePresenter) {
		this.currentPagePresenter = currentPagePresenter;
	}

	/*
	 * 
	 * protected void showMainView() { addStyleName(ValoTheme.UI_WITH_MENU);
	 * setContent(new MainScreen(FedasilUI.this));
	 * getNavigator().navigateTo(getNavigator().getState()); }
	 */

	public static FedasilUI get() {
		return (FedasilUI) UI.getCurrent();
	}

	@WebServlet(urlPatterns = "/*", name = "FedasilUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = FedasilUI.class, productionMode = false)
	public static class FedasilUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -9085700359453727641L;
	}
}
