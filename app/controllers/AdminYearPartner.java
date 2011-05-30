/**
 * 
 */
package controllers;

import models.YearPartner;
import play.mvc.With;

/**
 * @author chamerling
 *
 */
@CRUD.For(YearPartner.class)
@With(Secure.class)
public class AdminYearPartner extends CRUD {

}
