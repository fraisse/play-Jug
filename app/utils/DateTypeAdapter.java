/**
 * 
 */
package utils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author eric
 */
public class DateTypeAdapter implements	InstanceCreator, JsonSerializer, JsonDeserializer {

	@Override
	public Object createInstance(Type type) {
		return null;
	}

	@Override
	public JsonElement serialize(Object obj, Type type, JsonSerializationContext ctx) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JsonElement ele = new JsonPrimitive(sdf.format((Date)obj));
		return ele;
	}

	@Override
	public Object deserialize(JsonElement ele, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		return null;
	}
}
