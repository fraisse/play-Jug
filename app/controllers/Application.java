package controllers;

import java.io.File;
import java.util.List;

import models.Event;
import models.EventPartner;
import models.News;
import models.Speaker;
import models.YearPartner;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Filters.class)
public class Application extends Controller {
	
	@Before
	public static void addDefaults() {
		renderArgs.put("yearpartners", YearPartner.getCurrent());
	}

    public static void index() {
        Event event = Event.next();
        if (event == null) {
            event = Event.last();
        }
        render(event);
    }

    public static void event(Long id) {
        Event event = Event.findById(id);
        String[] attachments = Event.attachments(id);
        render(event, attachments);
    }

    public static void about() {
        if (renderArgs.get("nextEventId") != null) {
            Event event = Event.findById(renderArgs.get("nextEventId"));
            render(event);
        } else {
            render();
        }
    }

    public static void view() {
        render();
    }

    public static void members() {
        List<Speaker> members = Speaker.getMembers();
        if (renderArgs.get("nextEventId") != null) {
            Event event = Event.findById(renderArgs.get("nextEventId"));
            render(members, event);
        } else {
            render(members);
        }
    }

    public static void member(Long id) {
        Speaker member = Speaker.findById(id);
        List<Event> memberEvent = Speaker.getSpeakerEvents(id);
        if (renderArgs.get("nextEventId") != null) {
            Event event = Event.findById(renderArgs.get("nextEventId"));
            render(member, memberEvent, event);
        } else {
            render(member, memberEvent);
        }
    }
    
    public static void downloadEventAttachment(Long eventId, String attachment) {
        File file = Event.getAttachment(eventId, attachment);
        if (file != null && file.exists()) {
            renderBinary(file);
        } else {
            notFound();
        }
    }
    
    /**
     * List all the talks tagged with the given tag name
     * 
     * @param tag
     */
    public static void listTagged(String tag) {
    	// TODO
    	index();
    }

    /* News Section */
    
    public static void news() {
    	List<News> news = News.allByDate();
        if (renderArgs.get("nextEventId") != null) {
            Event event = Event.findById(renderArgs.get("nextEventId"));
            render(news, event);
        } else {
            render(news);
        }
    }
    
	public static void newsDetail(Long id) {
		News news = News.findById(id);
		if (renderArgs.get("nextEventId") != null) {
			Event event = Event.findById(renderArgs.get("nextEventId"));
			render(news, event);
		} else {
			render(news);
		}
	}
    
    public static void newsFeed() {
    	request.format = "rss";
    	List<News> news = News.allByDate();
    	response.setContentTypeIfNotSet("application/rss+xml");
    	render(news);
    }
    
    public static void partners() {
    	List<YearPartner> partners = YearPartner.getCurrent();
    	List<EventPartner> eventPartners = EventPartner.all().fetch();
    	render(partners, eventPartners);
    }
    
    public static void partner(Long id) {
    	YearPartner partner = YearPartner.findById(id);
    	render(partner);
    }
    
    public static void oldPartners() {
    	List<YearPartner> partners = YearPartner.getOldies();
    	render(partners);
    }
    
    public static void eventPartner(Long id) {
    	EventPartner partner = EventPartner.findById(id);
    	render(partner);
    }
    
    
}
