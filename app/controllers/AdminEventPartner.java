/**
 * 
 */
package controllers;

import models.Event;
import models.EventPartner;
import play.mvc.With;

/**
 * @author chamerling
 *
 */
@CRUD.For(EventPartner.class)
@With(Secure.class)
public class AdminEventPartner extends CRUD {

}
