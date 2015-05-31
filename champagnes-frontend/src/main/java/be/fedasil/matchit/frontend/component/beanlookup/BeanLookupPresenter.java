package be.fedasil.matchit.frontend.component.beanlookup;

import be.fedasil.matchit.backend.facade.FacadeFactory;

public class BeanLookupPresenter implements
		BeanLookupView.BeanLookupViewListener {
	private BeanLookupView view;

	public BeanLookupPresenter(BeanLookupView view) {
		// placeFacade=FacadeFactory.lookupPlaceFacade();
		this.view = view;
		view.addListener(this);
	}

	public void lookup(String jndi) {
		try {
			Object result = FacadeFactory.getInstance().lookupJndi(jndi);
			if (result != null) {
				view.setMessage("Lookup successfull "+result.getClass().getName());
			}
		} catch (Exception ex) {
			view.setMessage(ex.getMessage());
		}
	}
}
