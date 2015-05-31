package be.fedasil.matchit.frontend.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import be.fedasil.matchit.backend.facade.CodeLabelFacade;
import be.fedasil.matchit.backend.facade.FacadeFactory;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;
import be.fedasil.matchit.frontend.FedasilUI;

import com.vaadin.ui.UI;

/**
 * This helper class manages translations. UI captions are defined in properties
 * files following the resourcebundle naming conventions. CodeLabels are taken
 * from the database
 * 
 * @author wdewit
 *
 */
public class LocaleText {
	private static final MatchitLogger logger = MatchitLoggerFactory
			.getLogger(LocaleText.class);
	private static List<String> FORMAT_DB = Arrays.asList("db");
	private static List<Locale> SUPPORTED_LOCALES = Arrays.asList(new Locale(
			"nl", "be"), new Locale("fr", "be"),Locale.ENGLISH,Locale.ROOT);
	private static MatchitDBControl DB_CONTROL = new MatchitDBControl();
	/**
	 * Name of the resource bundle containing the UI Captions.
	 */
	private static String CAPTIONS = "be.fedasil.matchit.frontend.util.captions";
	private static String MESSAGES = "be.fedasil.matchit.frontend.util.messages";

	/**
	 * Get the translated text for the specified caption key in the language of
	 * the current user.
	 * 
	 * @param key
	 *            required key of the wanted text
	 * @return
	 */
	public static String getCaption(String key) {
		return get(CAPTIONS,key, FedasilUI.get().getLocale());
	}

	/**
	 * Get the translated text for the specified key in the specified language.
	 * 
	 * @param key
	 *            required key of the wanted text
	 * @param locale
	 *            required Locale
	 * @return the translation caption. If no translation can be found for the
	 *         provided key the answer is "![key]".
	 */
	public static String getCaption(String key, Locale locale) {
		return get(CAPTIONS,key,locale);
	}

	public static String getMessage(String key)
	{
		return get(MESSAGES,key, FedasilUI.get().getLocale());
	}
	public static String getMessage(String key, Locale locale)
	{
		return get(MESSAGES,key,locale);
	}
	private static String get(String bundle, String key,Locale locale)
	{
		try {
			if(locale==null)
			{
				logger.warn("No locale defined");
				return ResourceBundle.getBundle(bundle).getString(key);
			}
			return ResourceBundle.getBundle(bundle, locale).getString(key);
		} catch (Exception ex) {
			logger.error("something went wrong for bundle"+bundle+":"+key+":"+(locale==null?"null":locale.getLanguage()),ex);
			return "!MISSING[" + key + "]";
		}		
		
	}
	public static String getCodeLabel(String codeType, String code) {
		return ResourceBundle.getBundle(codeType, UI.getCurrent().getLocale(),
				DB_CONTROL).getString(code);
	}

	/**
	 * ResourceBundle Control implementation which fetches the messages from the
	 * database.
	 */
	private static class MatchitDBControl extends ResourceBundle.Control {

		@Override
		public List<String> getFormats(String baseName) {
			if (baseName.startsWith("be.fedasil.matchit"))
				return ResourceBundle.Control.FORMAT_PROPERTIES;
			return FORMAT_DB;
		}

		@Override
		public List<Locale> getCandidateLocales(String baseName, Locale locale) {
			return SUPPORTED_LOCALES;
		}

		@Override
		public ResourceBundle newBundle(String baseName, Locale locale,
				String format, ClassLoader loader, boolean reload)
				throws IllegalAccessException, InstantiationException,
				IOException {
			// Load messages from database table CodeLabel using basename as
			// CodeType
			CodeLabelFacade clf = FacadeFactory
					.lookupFacade(CodeLabelFacade.class);
			return new DBResourceBundle(clf.getCodeLabels(baseName,
					locale.getLanguage()));
		}
	}

	private static class DBResourceBundle extends ListResourceBundle {
		private Object[][] content;

		public DBResourceBundle(Map<String, String> items) {
			content = new Object[items.size()][2];
			int index = 0;
			for (Map.Entry<String, String> entry : items.entrySet()) {
				content[index][0] = entry.getKey();
				content[index][1] = entry.getValue();
			}
		}

		@Override
		protected Object[][] getContents() {
			return content;
		}

	}
}
