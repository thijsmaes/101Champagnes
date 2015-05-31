package be.fedasil.matchit.backend.exception;

/**
 * Base checked exception from which all checked exception must inherit.
 * Checked exception must be used for exception that must be explicitly
 * defined in the interface definitions.
 * 
 * @author dtoch
 */
public abstract class CheckedException extends Exception {

	private static final long serialVersionUID = 3928901095315261087L;

	/**
	 * @param message Error message.
	 */
	public CheckedException(String message) {	
		super(message);
	}
	
	/**
	 * @param message Error message.
	 * @param cause Cause.
	 */
	public CheckedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause Cause.
	 */
	public CheckedException(Throwable cause) {
		super(cause);
	}
	
}
