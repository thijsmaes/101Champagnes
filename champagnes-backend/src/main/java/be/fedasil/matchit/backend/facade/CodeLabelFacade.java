package be.fedasil.matchit.backend.facade;

import java.util.Map;

/**
 * Code labels represent all codes which have a translation per supported
 * language in the database.
 * 
 * @author wdewit
 *
 */
public interface CodeLabelFacade {

	/**
	 * Returns the list of code keys of type codeType with their human readable
	 * label in the requested language.
	 * 
	 * @param codeType
	 *            name of the code list type
	 * @param language
	 *            requested language (nl, fr, ...)
	 * @return map which contains an entry for each defined code in the
	 *         requested code type and the label in the requested language as
	 *         value.
	 */
	Map<String, String> getCodeLabels(String codeType, String language);
}
