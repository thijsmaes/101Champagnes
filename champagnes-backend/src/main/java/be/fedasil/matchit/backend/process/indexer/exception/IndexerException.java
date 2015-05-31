package be.fedasil.matchit.backend.process.indexer.exception;

import be.fedasil.matchit.backend.exception.CheckedException;

/**
 * 
 * @author gvginder
 *
 */
public abstract class IndexerException extends CheckedException {
	/**
	 * @param message
	 *            Error message.
	 */
	public IndexerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            Error message.
	 * @param cause
	 *            Cause.
	 */
	public IndexerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 *            Cause.
	 */
	public IndexerException(Throwable cause) {
		super(cause);
	}
}
