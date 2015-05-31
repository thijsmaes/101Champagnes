package be.fedasil.matchit.frontend.component.beanlookup;

import com.vaadin.ui.Component;

public class BeanLookupManager {

	public static Component build ()
	{
		BeanLookupViewImpl view=new BeanLookupViewImpl();
		new BeanLookupPresenter(view);
		return view;
	}
}
