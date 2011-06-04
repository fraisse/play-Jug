package utils;

import java.sql.Timestamp;
import java.util.Date;

import play.mvc.results.RenderJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

public class RenderJsonExclusion extends RenderJson {

	public RenderJsonExclusion(Object o, ExclusionStrategy exclusionStrategy) {
		super(new GsonBuilder().setExclusionStrategies(exclusionStrategy)
				.serializeNulls().
				setDateFormat("yyyy/MM/dd").
				registerTypeAdapter(Timestamp.class, new DateTypeAdapter()).
				registerTypeAdapter(Date.class, new DateTypeAdapter()).
				registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter()).
				create().toJson(o)); 
	}

	public RenderJsonExclusion(Object o) {
		this(o, new JsonExclusionStrategy());
	}

}
