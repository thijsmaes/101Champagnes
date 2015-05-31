package be.fedasil.matchit.frontend.component.lookupplace;

import be.fedasil.matchit.backend.facade.FacadeFactory;
import be.fedasil.matchit.backend.facade.PlaceFacade;
import be.fedasil.matchit.backend.model.Place;

public class LookupPlacePresenter implements LookupPlaceView.LookupViewListener {
	private LookupPlaceBuilder.LookupPlaceListener placeListener;
	private LookupPlaceView view;
	
	private PlaceFacade placeFacade;

	public LookupPlacePresenter(LookupPlaceBuilder.LookupPlaceListener placeListener,LookupPlaceView view)
	{
		placeFacade=FacadeFactory.lookupPlaceFacade();
		this.placeListener=placeListener;
		this.view=view;
		view.addListener(this);
	}
	
	public void lookup(String placeCode){
		Place result=placeFacade.findPlaceByCode(placeCode);
		if(result!=null)
		{
			placeListener.selectedPlace(result);
		} else {
			view.setErrorMessage("No Place found.");
		}
	}
}
