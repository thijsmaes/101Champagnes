package be.fedasil.matchit.backend.process.indexer.exception;

/**
 * 
 * @author gvginder
 *
 */
public class RecoverableIndexerException extends IndexerException {
	/**
	 * @param message
	 *            Error message.
	 */
	public RecoverableIndexerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            Error message.
	 * @param cause
	 *            Cause.
	 */
	public RecoverableIndexerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 *            Cause.
	 */
	public RecoverableIndexerException(Throwable cause) {
		super(cause);
	}
}
