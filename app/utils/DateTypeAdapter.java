/**
 * 
 */
package utils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
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
 * This class aims to provide a specific Serializer for Date instance.<br/>
 * It is a fix for PlayFramework 1.2.1 and above. This class has to be registered with the GsonBuilder:<br/>
 * <code><br/>
 * registerTypeAdapter(Timestamp.class, new DateTypeAdapter())<br/>
 * registerTypeAdapter(Date.class, new DateTypeAdapter())<br/>
 * registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter())<br/>
 * </code>
 * 
 * @author eric
 */
public class DateTypeAdapter implements	JsonSerializer {

	@Override
	public JsonElement serialize(Object obj, Type type, JsonSerializationContext ctx) {
		// Allocate an instance each time as SimpleDateFormat is not thread safe
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JsonElement ele = new JsonPrimitive(sdf.format((Date)obj));
		return ele;
	}
}
