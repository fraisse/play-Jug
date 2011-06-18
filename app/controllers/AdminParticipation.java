package controllers;

import models.Participation;
import play.mvc.With;

@CRUD.For(Participation.class)
@With(Secure.class)
public class AdminParticipation extends CRUD {

}
