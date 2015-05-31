package be.fedasil.matchit.frontend.component.showplace;

import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.frontend.component.ComponentBuilder;

import com.vaadin.ui.Component;

public class ShowPlaceBuilder implements ComponentBuilder {

	private Place place;
	
	public ShowPlaceBuilder setPlace(Place place)
	{
		this.place=place;
		return this;
	}

	public Component build()
	{
		ShowPlaceView view= new ShowPlaceView(place);
		view.setWidth("100%");
		view.setPlace(place);
		return view;
	}
}
