package be.fedasil.matchit.frontend.component.beanlookup;

public interface BeanLookupView {

	interface BeanLookupViewListener {
		void lookup(String placeId);
	}
	
	void addListener(BeanLookupViewListener listener);
	
	void setMessage(String message);
}
