package be.fedasil.matchit.frontend.component.lookupplace;

import be.fedasil.matchit.backend.facade.FacadeFactory;
import be.fedasil.matchit.backend.facade.PlaceFacade;
import be.fedasil.matchit.backend.model.Place;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by tmaes on 8/05/2015.
 */
public class LookupPlacePresenterUTest {

    Place p;
    LookupPlacePresenter classToTest;

    @Mock
    LookupPlaceBuilder.LookupPlaceListener lookupPlaceListener;

    @Mock
    PlaceFacade placeFacade;

    @Mock
    LookupPlaceView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        p = new Place();
        p.setPlaceCode("whatever");
        Mockito.when(placeFacade.findPlaceByCode("whatever")).thenReturn(p);
        FacadeFactory.getInstance().setBean(FacadeFactory.PLACE_FACADE, placeFacade);
    }

    /**
     * comeout of findPlaceByCode(existing placeCode)
     */
    @Test
    public void testFindPlaceByCodeSuccessful(){

        classToTest = new LookupPlacePresenter(lookupPlaceListener, view);
        classToTest.lookup("whatever");
        Mockito.verify(lookupPlaceListener).selectedPlace(p);
        Mockito.verify(view, Mockito.never()).setErrorMessage(Mockito.anyString());
    }

    /**
     * comeout of findPlaceByCode(unexisting placeCode)
         */
    @Test
    public void testFindPlaceByCodeUnSuccessful(){

        classToTest = new LookupPlacePresenter(lookupPlaceListener, view);
        classToTest.lookup("something");
        Mockito.verify(view).setErrorMessage(Mockito.anyString());
        Mockito.verify(lookupPlaceListener, Mockito.never()).selectedPlace(Mockito.any(Place.class));
    }
}
