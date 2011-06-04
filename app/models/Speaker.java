package models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;

import controllers.Application;

import play.Play;
import play.data.validation.MaxSize;
import play.db.jpa.Model;
import play.libs.Crypto;
import play.libs.Images;
import play.mvc.Router;
import utils.MD5;

@Entity
public class Speaker extends Model {

    public String fullName;

    public String activity;

    public String company;
    
    public String url;

    public String personalUrl;
    
    public String email;

    @Lob
    @MaxSize(5000)
    public String description;

    public Boolean jugmember;

    public String memberFct;

    public String photoUrl;

    public static List<Speaker> getMembers() {
        return find("jugmember", Boolean.TRUE).fetch();
    }

    public static List<Event> getSpeakerEvents(Long speakerId) {
        return Talk.find(
                "select talk.event from Talk talk where talk.speaker.id=?",
                speakerId).fetch();
    }
    
    public String getGravatar() {
		String defaultLogo = "";
		try {
			// FIXME : Is there another way to get the logo.png URL?
			defaultLogo = URLEncoder.encode(Router.getFullUrl("Application.index") + "public/images/logo.png", "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

    	if (email != null) {
    		return "http://www.gravatar.com/avatar/" + MD5.md5Hex(email.toLowerCase()) + "?s=110&d=" + defaultLogo;
    	} else {
    		return "http://www.gravatar.com/avatar/00000000000000000000000000000000?s=110&d=" + defaultLogo;
    	}
    }

    public String getPhotoUrl() {
    	if (photoUrl != null && photoUrl.trim().length() > 0) {
    		return photoUrl;
    	}
    	return getGravatar();
    }
    
    public void setPhotoUrl(String newPhotoUrl) {
    	photoUrl = newPhotoUrl;
    }
    
    @Override
    public String toString() {
        return fullName;
    }
}
