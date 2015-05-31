package be.fedasil.matchit.backend.configuration.timerejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.fedasil.matchit.backend.configuration.MatchitConfiguration;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;

@Startup
@Singleton
public class TimerEjb {

	private static final MatchitLogger LOGGER = 
		MatchitLoggerFactory.getLogger(TimerEjb.class.getName());
	
	@Schedule(minute = "*/5")
	public void checkConfigurationRefresh() {
		MatchitConfiguration instance = MatchitConfiguration.getInstance();
		if (instance.configurationIsModified()) {
			LOGGER.warn("Configuration modified, refreshing config in cache");
			instance.refreshConfiguration();
		} else {
			LOGGER.debug("Configuration not changed, doing nothing");
		}
	}

}
