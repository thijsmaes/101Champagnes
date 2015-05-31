package be.fedasil.matchit.backend.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.fedasil.matchit.backend.dao.ConfigurationDAO;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.backend.model.ConfigurationItem;

/**
 * Class to manage system configuration entries.
 * 
 * @author dtoch
 */
public class MatchitConfiguration {

	private static final MatchitLogger LOGGER = 
		MatchitLoggerFactory.getLogger(MatchitConfiguration.class.getName());
	
	private ConfigurationDAO configurationDAO;
	
	private static final MatchitConfiguration INSTANCE = new MatchitConfiguration();

	private Map<String, String> cachedConfigurationItems = new HashMap<String, String>();
	
	public static MatchitConfiguration getInstance() {
		return INSTANCE;
	}

	MatchitConfiguration(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}
	
	private MatchitConfiguration() {
		configurationDAO = new ConfigurationDAO();
	}
	
	private Map<String, String> getConfigurationFromDatabase() {
		List<ConfigurationItem> configurationItemsFromDatabase = 
			configurationDAO.findConfigurations();
		Map<String, String> configurationItems = new TreeMap<String, String>();
		for (ConfigurationItem item : configurationItemsFromDatabase) {
			configurationItems.put(item.getKey(), item.getValue());					
		}
		return configurationItems;
	}
	
	/**
	 * @return TRUE if configuration is modified, FALSE otherwise.
	 */
	public boolean configurationIsModified() {		
		Map<String, String> newCachedConfigurationItems = getConfigurationFromDatabase();
		return (!newCachedConfigurationItems.equals(cachedConfigurationItems));
	}	

	/**
	 * Refresh the configuration in the cache if modified. 
	 */
	public void refreshConfiguration() {
		synchronized (this) {
			if (configurationIsModified()) {
				cachedConfigurationItems = getConfigurationFromDatabase();
			}
		}
	}
	
	/**
	 * @return Get the configuration from the cache.
	 */
	public Map<String, String> getConfiguration() {
		return cachedConfigurationItems;
	}
	
	/**
	 * @param key The configuration key for which to look up the value.
	 * @return The configuration value for the given key.
	 */
	public String getConfigurationValue(String key) {
		return cachedConfigurationItems.get(key);
	}
}
