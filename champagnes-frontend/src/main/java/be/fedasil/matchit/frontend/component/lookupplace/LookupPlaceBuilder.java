package be.fedasil.matchit.frontend.component.lookupplace;

import be.fedasil.matchit.backend.model.Place;

import com.vaadin.ui.Component;

/**
 * Builder for the LookupPlace
 * This is the starting point for this package
 * @author wdewit
 *
 */
public class LookupPlaceBuilder {
	private LookupPlaceListener listener;
	
	public interface LookupPlaceListener{
		void selectedPlace(Place place);
	}
	
	public LookupPlaceBuilder setLookupListener(LookupPlaceListener listener)
	{
		this.listener=listener;
		return this;
	}

	public Component build ()
	{
		LookupPlaceViewImpl view = new LookupPlaceViewImpl();
		new LookupPlacePresenter(listener,view);
		return view;
	}
}
