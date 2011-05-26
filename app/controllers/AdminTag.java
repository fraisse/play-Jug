/**
 * 
 */
package controllers;

import models.Tag;
import play.mvc.With;

/**
 * @author chamerling
 *
 */
@CRUD.For(Tag.class)
@With(Secure.class)
public class AdminTag extends CRUD {

}
