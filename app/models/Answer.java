package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.mvc.Http.Cookie;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import utils.JsonExclude;

@Entity
public class Answer extends Model {
    
    @JsonExclude
    @ManyToOne
    public Poll poll;
    
    @Required
    public String answer;
    
    public int votes;
    
    @Override
    public String toString() {
        return answer;
    }
    
    public static Poll vote(Long answerId, Request request, Response response) {
        Cookie cookie = request.cookies.get("polls");
        Answer answer = Answer.findById(answerId);
        Poll poll = answer.poll;
        
        String pollsIds = "";
        if (cookie != null) {
            pollsIds = cookie.value;
            
            String [] ids = pollsIds.substring(1).split(",");
            List<Long> polls = new ArrayList<Long>();
            for (String id: ids) {
                polls.add(Long.valueOf(id));
            }
            
            if (polls.contains(poll.id)) {
                return poll;
            }
        }
        response.setCookie("polls", "," + poll.id + pollsIds, "365d");
        answer.votes++;
        answer.save();
        return poll;
    }

}
