/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Event;
import models.EventPartner;
import models.News;
import models.Speaker;
import models.YearPartner;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Router;
import play.mvc.Router.Route;
import utils.RenderJsonExclusion;

/**
 * A dedicated controller for all the API related stuff
 * 
 * @author chamerling
 * 
 */
public class API extends Controller {

	public static void definition() {
		List<String> URLs = new ArrayList<String>();
		for (Route route : Router.routes) {
			if (route.path.startsWith("/api/")) {
				URLs.add(Http.Request.current().getBase() + route.path);
			}
		}
		renderJSON(URLs);
	}

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

	public static void listNews() {
		List<News> news = News.allByDate();
		if (request.format.equals("json")) {
			renderJSON(news);
		}
		render(news);
	}

	public static void currentYearPartners() {
		List<YearPartner> partners = YearPartner.getCurrent();
		if (request.format.equals("json")) {
			renderJSON(partners);
		}
		// TODO :: XML
		// render(news);
	}

	public static void pastYearPartners() {
		List<YearPartner> partners = YearPartner.getOldies();
		if (request.format.equals("json")) {
			renderJSON(partners);
		}
		// TODO :: XML
		// render(news);
	}

	public static void eventPartners() {
		List<EventPartner> partners = EventPartner.findAll();
		if (request.format.equals("json")) {
			renderJSON(partners);
		}
		// TODO :: XML
		// render(news);
	}

	private static void renderJSONExclusion(Object o) {
		throw new RenderJsonExclusion(o);
	}
}
