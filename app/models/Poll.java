package models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.binding.As;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.mvc.Http.Cookie;

@Entity
public class Poll extends Model {
    
    @Required
    @MaxSize(1000)
    @Column(length = 1000)
    public String question;
    
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    public Set<Answer> answers;
    
    @As("yyyy-MM-dd")
    public Date expiryDate;
    
    public boolean visible;
    
    @Override
    public String toString() {
        return question;
    }
    
    public static Set<Long> closedPolls(Cookie userPollsCookie) {
        Set<Long> closedPolls = new HashSet<Long>();
        if (userPollsCookie != null) {
            String voted = userPollsCookie.value.substring(1);
            for (String id: voted.split(",")) {
                closedPolls.add(Long.valueOf(id));
            }
        }
        closedPolls.addAll(visiblesExpiredIds());
        return closedPolls;
    }
    
    public static List<Long> visiblesExpiredIds() {
        return Poll.find("select poll.id from Poll poll " +
               "where poll.visible = ? and poll.expiryDate < ?",
                true, new Date()).fetch();
    }

    public static List<Poll> getVisibles() {
        return Poll.find("visible = ?", true).fetch();
    }
}
