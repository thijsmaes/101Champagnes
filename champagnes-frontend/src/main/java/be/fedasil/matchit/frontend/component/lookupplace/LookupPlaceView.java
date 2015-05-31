package be.fedasil.matchit.frontend.component.lookupplace;

public interface LookupPlaceView {

	interface LookupViewListener {
		void lookup(String placeId);
	}
	
	void addListener(LookupViewListener listener);
	
	void setErrorMessage(String errorMessage);
}
