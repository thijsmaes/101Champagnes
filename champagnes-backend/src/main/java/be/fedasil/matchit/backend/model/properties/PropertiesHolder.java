package be.fedasil.matchit.backend.model.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gvginder
 */
public class PropertiesHolder extends HashMap<String, PropertiesHolder.Category> {
	/**
	 * @param categoryName
	 * @param entryName
	 * @param values
	 */
	public PropertiesHolder addCategoryValues(String categoryName, String entryName, Object... values) {
		Category category = createCategory(categoryName);
		category.addEntry(entryName, values);
		return this;
	}

	/**
	 * @param other
	 */
	public PropertiesHolder addPropertiesHolder(PropertiesHolder other) {
		for (Map.Entry<String, Category> categoryMapEntry : other.entrySet()) {
			String categoryName = categoryMapEntry.getKey();
			Category categoryEntries = categoryMapEntry.getValue();

			for (Map.Entry<String, Entry> entryMapEntry : categoryEntries.entrySet()) {
				String entryName = entryMapEntry.getKey();
				Entry entry = entryMapEntry.getValue();

				for (Object value : entry) {
					addCategoryValues(categoryName, entryName, value);
				}
			}
		}
		return this;
	}

	/**
	 * Create a new category list or return the existing one for the given
	 * category.
	 *
	 * @param categoryName
	 * @return
	 */
	protected Category createCategory(String categoryName) {
		Category category;
		if (!containsKey(categoryName)) {
			category = new Category();
			put(categoryName, category);
		} else {
			category = get(categoryName);
		}
		return category;
	}

	/**
	 *
	 */
	public static class Category extends HashMap<String, Entry> {
		public void addEntry(String entryName, Object[] values) {
			if (containsKey(entryName)) {
				Entry entry = get(entryName);
				entry.addAll(Arrays.asList(values));
			} else {
				put(entryName, new Entry(values));
			}
		}
	}

	/**
	 *
	 */
	public static class Entry extends ArrayList<Object> {
		public Entry(Object... values) {
			addAll(Arrays.asList(values));
		}
	}
}
