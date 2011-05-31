/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

/**
 * @author chamerling
 *
 */
@Entity
public class News extends Model {
	
	public String title;

	/**
	 * The Date the news is posted
	 */
	public Date date;
	
	@MaxSize(10000)
    @Column(length=10000)
	public String content;
	
	/**
	 * Display/Activate or not Disqus.com comments for this News
	 */
	public boolean comments;
	
	/**
	 * get all news ordered by date
	 * 
	 * @return
	 */
    public static List<News> allByDate() {
    	return News.find("order by date desc").fetch();
    }
    
    public static List<News> last(int size) {
    	return News.find("order by date desc").fetch(size);
    }
    
    /**
     * Get all the news which are older than the given one
     * 
     * @return
     */
    public static List<News> past(News n) {
    	return News.find("date < ? order by date desc", n.date).fetch();
    }
    
    public String getShortContent(int length) {
    	String result = null;
    	if (content == null) {
    		return "";
    	}
    	
    	if (content.length() >= length) {
    		result = content.substring(0, length);
    	} else {
    		result = content;
    	}
    	return result;
    }

}
