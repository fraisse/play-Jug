/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.db.jpa.Model;
import utils.JsonExclude;

/**
 * @author chamerling
 *
 */
@Entity
public class EventPartner extends Model {
	
	/**
	 * Unique name
	 */
	public String name;
	
	/**
	 * Partner URL
	 */
	public String url;
	
	/**
	 * Partner description. Can be wikitext formated.
	 */
	@MaxSize(10000)
    @Column(length=10000)
	public String description;
	
	/**
	 * Local or remote logo URL
	 */
	public String logoURL;
	
	/**
	 * TODO : Do it with JPA...
	 * @param partnerId
	 * @return
	 */
	public static List<Event> getSponsoredEvents(Long partnerId) {
		 return Event.find(
	                "select event from Event event where event.partner.id=?",
	                partnerId).fetch();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
