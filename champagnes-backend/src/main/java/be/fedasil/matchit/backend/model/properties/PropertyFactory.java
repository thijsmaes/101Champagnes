package be.fedasil.matchit.backend.model.properties;

/**
 * Factory to instantiate Property instances.
 */
public class PropertyFactory {

	private static PropertyFactory instance;

	/**
	 * Private constructor. Always use {@link #getInstance()} to get an
	 * instance.
	 */
	private PropertyFactory() {
	}

	public static PropertyFactory getInstance() {
		if (instance == null) {
			instance = new PropertyFactory();
		}
		return instance;
	}

	/**
	 * Based on the provided type, the correct Property instance is instantiated
	 * and returned. public Property createProperty(PropertyType type) { //
	 * Based on the provided type instantiate the correct Property instance.
	 * return null; }
	 * 
	 * /** Create a Property instance using the provided serialized
	 * representation of the data.
	 * 
	 * @param json
	 *            required JSON representation of the data. the first field in
	 *            the JSON map must be <code>propertyType</code> with a value
	 *            corresponding to an existing {@link PropertyType}.
	 * @return a new Property instance filled with the information extracted
	 *         from the provided json.
	 */
	public Property parse(String json) {
		return null;
	}

	/**
	 * Creates a new Property instance of the requested type.
	 * 
	 * @param propType
	 *            required PropertyType
	 * @return new Property instance wherefor {@link Property property.getPropertyType()}
	 *         = propType.
	 */
	public Property createProperty(PropertyType propType) {
		// TODO based on the requested type provide the correct Property
		// implementation
		return null;
	}
}