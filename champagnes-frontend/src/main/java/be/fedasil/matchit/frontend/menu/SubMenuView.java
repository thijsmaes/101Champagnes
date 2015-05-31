package be.fedasil.matchit.frontend.menu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.frontend.pages.template.PagePresenter;
import be.fedasil.matchit.frontend.util.LocaleText;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * Component to show the available submenu items in a vertical layout.
 * 
 * @author wdewit
 *
 */
public class SubMenuView extends VerticalLayout {
	private static final MatchitLogger logger = MatchitLoggerFactory
			.getLogger(SubMenuView.class);

	private static final long serialVersionUID = -5695274984943224055L;
	private PagePresenter pagePresenter;
	private Map<SubMenuItem,Button> menuItems=new LinkedHashMap<SubMenuItem, Button>();
	
	/**
	 * constructor giving the PagePresenter which will be notified when the user
	 * chooses a submenu item.
	 * 
	 * @param presenter
	 *            when the user chooses a submenu item the 
	 *            {@link PagePresenter#onSubMenuSelected(SubMenuItem)
	 *            presenter.onSubMenuSelected(SubMenuItem)} is called.
	 */
	public SubMenuView(PagePresenter presenter) {
		setStyleName("m-submenu");
		this.pagePresenter = presenter;
	}

	/**
	 * Pass the list of SubMenuItems to show. The UI will be updated inline. 
	 * @param items
	 */
	public void setSubMenuItems(List<SubMenuItem> items) {
		removeAllComponents();
		if (items != null) {
			for (SubMenuItem item : items) {
				Button btn = new Button(LocaleText.getCaption(item
						.getCaptionCode()));
				btn.addClickListener(new MenuClickListener(item));
				btn.setStyleName("m-submenu-item");
				addComponent(btn);
				menuItems.put(item,btn);
			}
		}
	}

	private class MenuClickListener implements ClickListener {
		private static final long serialVersionUID = 5500645899678570222L;
		private SubMenuItem item;

		public MenuClickListener(SubMenuItem item) {
			this.item = item;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			logger.debug("Submenu " + item.getCaptionCode() + " selected.");
			pagePresenter.onSubMenuSelected(item);
		}
	}

	public void setCurrent(SubMenuItem subMenuItem) {
		for(Button btn:menuItems.values())
			btn.removeStyleName("m-selected");
		menuItems.get(subMenuItem).addStyleName("m-selected");
	}
}
