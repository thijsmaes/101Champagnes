package be.fedasil.matchit.backend.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory to create instance of logger to be used by all layers.
 * 
 * @author dtoch
 */
public class MatchitLoggerFactory {

	/**
	 * @param category Category under which to log.
	 * @return Logger instance.
	 */
	public static MatchitLogger getLogger(String category) {
		Logger log = LoggerFactory.getLogger(category);
		return new MatchitLogger(log);
	}
	
	/**
	 * @param category Category under which to log.
	 * @return Logger instance.
	 */
	public static MatchitLogger getLogger(Class category) {
		Logger log = LoggerFactory.getLogger(category);
		return new MatchitLogger(log);
	}

}
