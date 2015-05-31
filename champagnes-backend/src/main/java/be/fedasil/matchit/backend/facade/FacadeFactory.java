package be.fedasil.matchit.backend.facade;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Use this factory to lookup instances of the *Facade session beans from the frontend 
 * @author wdewit
 *
 */
public class FacadeFactory {
	// the values if these static constants must correspond to the EJB name as it occurs in the JNDI tree. 
	// This corresponds to the name of the implementation class
	public static final String PLACE_FACADE="PlaceFacadeBean";
	public static final String RECEPTIONCENTER_FACADE="ReceptionCenterFacadeBean";
	public static final String ROOM_FACADE="RoomFacadeBean";
	public static final String CODELABEL_FACADE="CodeLabelFacadeBean";
	
	private Context initialContext;
	/**
	 * caching of EJB instances
	 */
	private Map<String, Object> cache;
	private static FacadeFactory ourInstance = new FacadeFactory();

	public static FacadeFactory getInstance() {
		return ourInstance;
	}

	private FacadeFactory() {
		try {
			this.initialContext = new InitialContext();
			this.cache = Collections
					.synchronizedMap(new HashMap<String, Object>());
		} catch (NamingException ne) {
			System.err.printf(
					"Error in CTX looking up %s because of %s while %s",
					ne.getRemainingName(), ne.getCause(), ne.getExplanation());
		}
	}

	public void setBean(String beanReference, Object bean){
				cache.put(beanReference, bean);
	}

	public Object lookupEjb(String ejbName) {
		if (this.cache.containsKey(ejbName)) {
			return this.cache.get(ejbName);
		} else {
			try {
				Object ejbRef = initialContext.lookup("java:app/matchit-frontend-0.0.1-SNAPSHOT/"
						+ ejbName);
				this.cache.put(ejbName, ejbRef);
				return ejbRef;
			} catch (NamingException ne) {
				throw new RuntimeException(ne);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Object lookupJndi(String jndi) throws Exception
	{
		return initialContext.lookup(jndi);
	}
	
	public static PlaceFacade lookupPlaceFacade() {
		return (PlaceFacade) getInstance().lookupEjb(PLACE_FACADE);
	}

	public static ReceptionCenterFacade lookupReceptionCenterFacade() {
		return (ReceptionCenterFacade) getInstance().lookupEjb(RECEPTIONCENTER_FACADE);
	}

	public static RoomFacade lookupRoomFacade() {
		return (RoomFacade) getInstance().lookupEjb(ROOM_FACADE);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T lookupFacade(Class<T> beanname)
	{
		return (T) getInstance().lookupEjb(beanname.getName()+"Bean");
	}
}
