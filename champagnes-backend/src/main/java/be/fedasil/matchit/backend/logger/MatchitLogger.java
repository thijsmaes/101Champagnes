package be.fedasil.matchit.backend.logger;

import org.slf4j.Logger;

/**
 * Logger to be used by all layers. Instantiation using {@link MatchitLoggerFactory}.
 * 
 * @author dtoch
 */
public final class MatchitLogger {

	private final org.slf4j.Logger logger;

	/**
	 * Constructor.
	 * 
	 * @param logger
	 */
	MatchitLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * Log a message in level = DEBUG. 
	 * 
	 * <b>Always wrap this debug(...) call in a isDebugEnabled() condition.</b>
	 * 
	 * @param msg Message.
	 */
	public void debug(String msg) {
		logger.debug(msg);
	}

	/**
	 * Log a message and cause in level = DEBUG.
	 * 
	 * <b>Always wrap this debug(...) call in a isDebugEnabled() condition.</b>
	 * 
	 * @param msg Message.
	 * @param cause Cause.
	 */
	public void debug(String msg, Throwable cause) {
		logger.debug(msg, cause);
	}

	/**
	 * Log a message in level = ERROR.
	 * 
	 * @param msg Message.
	 */
	public void error(String msg) {
		logger.error(msg);
	}

	/**
	 * Log a message and cause in level = ERROR.
	 * 
	 * @param msg Message.
	 * @param cause Cause.
	 */
	public void error(String msg, Throwable cause) {
		logger.error(msg, cause);
	}

	/**
	 * Log a message in level = INFO.
	 * 
	 * @param msg Message.
	 */
	public void info(String msg) {
		logger.info(msg);
	}

	/**
	 * Log a message in level = INFO.
	 *  
	 * @param msg Message.
	 * @param cause Cause.
	 */
	public void info(String msg, Throwable cause) {
		logger.info(msg, cause);
	}

	/**
	 * Log a message in level = WARN.
	 * 
	 * @param msg Message.
	 */
	public void warn(String msg) {
		logger.warn(msg);
	}

	/**
	 * @return TRUE if debug is enabled in underlying logging configuration,
	 *         FALSE otherwise.
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

}
