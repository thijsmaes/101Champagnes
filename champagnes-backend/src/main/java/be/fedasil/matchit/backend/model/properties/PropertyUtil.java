package be.fedasil.matchit.backend.model.properties;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * @author gvginder
 */
public class PropertyUtil {
	private static ObjectMapper MAPPER;
	private static ObjectReader READER;
	private static ObjectWriter WRITER;

	private PropertyUtil() {
	}

	private static ObjectMapper getObjectMapper() {
		if (MAPPER == null) {
			SimpleModule module = new SimpleModule();
			module.addSerializer(PropertiesHolder.Entry.class, new Serializer());
			module.addDeserializer(PropertiesHolder.Entry.class, new Deserializer());

			MAPPER = new ObjectMapper();
			MAPPER.registerModule(module);
		}
		return MAPPER;
	}

	private static ObjectReader getObjectReader() {
		if (READER == null) {
			READER = getObjectMapper().reader(PropertiesHolder.class);
		}
		return READER;
	}

	private static ObjectWriter getObjectWriter() {
		if (WRITER == null) {
			WRITER = getObjectMapper().writer();
		}
		return WRITER;
	}

	public static PropertiesHolder jsonToPropertiesHolder(String json) {
		try {
			return getObjectReader().readValue(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}

	public static String propertiesHolderToJson(PropertiesHolder properties) {
		try {
			return getObjectWriter().writeValueAsString(properties);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static class Serializer extends JsonSerializer<PropertiesHolder.Entry> {
		@Override
		public void serialize(PropertiesHolder.Entry entry, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeStartArray();
			for (Object value : entry) {
				writeTypedValue(value, gen, provider);
			}
			gen.writeEndArray();
		}

		protected void writeTypedValue(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			final String type;
			final Object converted;
			if (value == null) {
				throw new NullValueException();
			} else if (value instanceof Boolean) {
				type = "boolean";
				converted = value;
			} else if (value instanceof Integer) {
				type = "integer";
				converted = value;
			} else if (value instanceof Long) {
				type = "long";
				converted = value;
			} else if (value instanceof Double) {
				type = "double";
				converted = value;
			} else if (value instanceof Float) {
				type = "float";
				converted = value;
			} else if (value instanceof String) {
				type = "string";
				converted = value;
			} else if (value instanceof Date) {
				Date date = ((Date) value);
				type = "date";
				converted = date.getTime();
			} else {
				throw new UnsupportedValueException(value);
			}

			gen.writeStartObject();
			gen.writeObjectField("value", converted);
			gen.writeStringField("type", type);
			gen.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<PropertiesHolder.Entry> {
		@Override
		public PropertiesHolder.Entry deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			PropertiesHolder.Entry entry = new PropertiesHolder.Entry();
			JsonNode rootNode = parser.readValueAsTree();
			readValues(entry, rootNode, context);
			return entry;
		}

		protected void readValues(PropertiesHolder.Entry entry, JsonNode valuesNode, DeserializationContext context) throws JsonProcessingException {
			if (!valuesNode.isArray()) {
				throw new UnexpectedNodeException(valuesNode, JsonNodeType.ARRAY);
			}

			Iterator<JsonNode> valuesIterator = valuesNode.iterator();
			while (valuesIterator.hasNext()) {
				JsonNode typedValueNode = valuesIterator.next();
				readTypedValue(entry, typedValueNode, context);
			}
		}

		protected void readTypedValue(PropertiesHolder.Entry entry, JsonNode typedValueNode, DeserializationContext context) throws JsonProcessingException {
			if (!typedValueNode.isObject()) {
				throw new UnexpectedNodeException(typedValueNode, JsonNodeType.OBJECT);
			}

			JsonNode valueNode = typedValueNode.get("value");
			JsonNode typeNode = typedValueNode.get("type");
			if (valueNode == null || typeNode == null) {
				throw new NullNodeException();
			} else if (!typeNode.isTextual()) {
				throw new UnexpectedNodeException(typedValueNode, JsonNodeType.STRING);
			}

			Object value;
			String type = typeNode.textValue();
			if ("boolean".equals(type)) {
				value = valueNode.booleanValue();
			} else if ("integer".equals(type)) {
				value = valueNode.intValue();
			} else if ("long".equals(type)) {
				value = valueNode.longValue();
			} else if ("double".equals(type)) {
				value = valueNode.doubleValue();
			} else if ("float".equals(type)) {
				value = valueNode.floatValue();
			} else if ("string".equals(type)) {
				value = valueNode.textValue();
			} else if ("date".equals(type)) {
				long timestamp = valueNode.longValue();
				value = new Date(timestamp);
			} else {
				throw new UnsupportedTypeException(type);
			}
			entry.add(value);
		}
	}

	public static class NullValueException extends JsonProcessingException {
		protected NullValueException() {
			super("Null value given as property value");
		}
	}

	public static class UnsupportedValueException extends JsonProcessingException {
		protected UnsupportedValueException(Object object) {
			super("Unsupported value of class '" + object.getClass() + "'");
		}
	}

	public static class NullNodeException extends JsonProcessingException {
		protected NullNodeException() {
			super("Null node given as value");
		}
	}

	public static class UnsupportedTypeException extends JsonProcessingException {
		protected UnsupportedTypeException(String type) {
			super("Unsupported type '" + type + "'");
		}
	}

	public static class UnexpectedNodeException extends JsonProcessingException {
		protected UnexpectedNodeException(JsonNode given, JsonNodeType expected) {
			this(given.getNodeType(), expected);
		}

		protected UnexpectedNodeException(JsonNodeType given, JsonNodeType expected) {
			super("Expected '" + expected + "' node, given '" + given + "'");
		}
	}
}
