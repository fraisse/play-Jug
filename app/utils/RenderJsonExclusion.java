package utils;

import play.mvc.results.RenderJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

public class RenderJsonExclusion extends RenderJson {

	public RenderJsonExclusion(Object o, ExclusionStrategy exclusionStrategy) {
		super(new GsonBuilder().setExclusionStrategies(exclusionStrategy)
				.serializeNulls().create().toJson(o)); 
	}

	public RenderJsonExclusion(Object o) {
		this(o, new JsonExclusionStrategy());
	}

}
