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
	
    public static List<News> allByDate() {
    	return News.find("order by date desc").fetch();
    }

}
