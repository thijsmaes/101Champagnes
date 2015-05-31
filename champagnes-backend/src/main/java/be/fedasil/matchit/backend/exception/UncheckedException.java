package be.fedasil.matchit.backend.exception;

/**
 * Unchecked exception to be used for runtime exception.
 * 
 * @author dtoch
 */
public abstract class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = -5648778114155951861L;

	/**
	 * @param message Error message.
	 */
	public UncheckedException(String message) {	
		super(message);
	}
	
	/**
	 * @param message Error message.
	 * @param cause Cause.
	 */
	public UncheckedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param cause Cause.
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
	}
	
}
