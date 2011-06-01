package utils;

import play.mvc.results.RenderJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

/**
 * NOTE : For now in Play 1.2 and 1.2.1, setting the date format has no effect.
 * It should be OK for play 1.2.2 according to this <a href=
 * "http://play.lighthouseapp.com/projects/57987/tickets/780-gson-16-ignores-setdateformat-please-update-to-171"
 * >ticket</a>
 * 
 * @author chamerling
 * 
 */
public class RenderJsonExclusion extends RenderJson {
	
	private static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public RenderJsonExclusion(Object o, ExclusionStrategy exclusionStrategy) {
		super(new GsonBuilder().setExclusionStrategies(exclusionStrategy)
				.serializeNulls().setDateFormat(DATE_FORMAT).setPrettyPrinting().create().toJson(o)); 
	}

	public RenderJsonExclusion(Object o) {
		this(o, new JsonExclusionStrategy());
	}

}
