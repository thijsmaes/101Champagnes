package be.fedasil.matchit.frontend.pages.template;

import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.frontend.menu.MainMenuItem;
import be.fedasil.matchit.frontend.menu.SubMenuItem;

public class NormalPagePresenter implements PagePresenter {
	private static final MatchitLogger logger = MatchitLoggerFactory
			.getLogger(NormalPagePresenter.class);
	
	private PageView view;
	
	public NormalPagePresenter(PageView view) {
		this.view=view;
	}

	public void onMenuSelected(MainMenuItem menuItem){
		view.setCurrentMenuItem(menuItem);
	}
	
	public void onSubMenuSelected(SubMenuItem subMenuItem)
	{
		// TODO check access rights for the current user
		view.setCurrentSubMenuItem(subMenuItem);
	}
}
