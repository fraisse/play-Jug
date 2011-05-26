/**
 * 
 */
package controllers;

import java.util.List;

import models.Event;
import models.Speaker;
import play.mvc.Controller;
import utils.RenderJsonExclusion;

/**
 * A dedicated controller for all the API related stuff
 * 
 * @author chamerling
 *
 */
public class API extends Controller {

    public static void listEvents() {
    	List<Event> events = Event.allByDate();
    	if (request.format.equals("json")) {
    		renderJSONExclusion(events);
    	}
    	render(events);
    }
    
    public static void listMembers() {
    	List<Speaker> members = Speaker.getMembers();
    	if (request.format.equals("json")) {
    		renderJSON(members);
    	}
    	render(members);
    }
    
    public static void nextEvent() {
    	Event event = Event.next();
    	if (request.format.equals("json")) {
    		renderJSONExclusion(event);
    	}
    	render(event);
    }
    
    private static void renderJSONExclusion(Object o) {
    	throw new RenderJsonExclusion(o);
    }
}
