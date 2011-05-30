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
public class YearPartner extends Model {

	/**
	 * Unique partner name
	 */
	public String name;

	/**
	 * The partner URL (website or whatever)
	 */
	public String url;

	/**
	 * Partner description. Can be wikitext formatted.
	 */
	@MaxSize(10000)
	@Column(length = 10000)
	public String description;

	/**
	 * The local logo URL or remote one.
	 */
	public String logoURL;

	/**
	 * The date when the partnership starts
	 */
	public Date startDate;

	/**
	 * The date when the partnership stops. Can be updated for renewal.
	 */
	public Date stopDate;

	/**
	 * Get the current year partners
	 * 
	 * @return
	 */
	public static List<YearPartner> getCurrent() {
		Date d = new Date();
		return YearPartner.find("startDate < ? and stopDate > ? order by stopDate desc", d, d).fetch();

	}

	public static List<YearPartner> getOldies() {
		return YearPartner.find("stopDate < ? order by stopDate desc", new Date()).fetch();
	}

}
