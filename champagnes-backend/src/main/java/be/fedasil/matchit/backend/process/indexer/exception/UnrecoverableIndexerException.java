package be.fedasil.matchit.backend.process.indexer.exception;

/**
 * 
 * @author gvginder
 *
 */
public class UnrecoverableIndexerException extends IndexerException {
	/**
	 * @param message
	 *            Error message.
	 */
	public UnrecoverableIndexerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            Error message.
	 * @param cause
	 *            Cause.
	 */
	public UnrecoverableIndexerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 *            Cause.
	 */
	public UnrecoverableIndexerException(Throwable cause) {
		super(cause);
	}
}
