package controllers;

import models.Answer;
import models.Poll;
import play.mvc.Controller;
import utils.RenderJsonExclusion;

/**
 *
 * @author dboissin
 */
public class PollController extends Controller {
    
    public static void vote(Long answerId) {
        notFoundIfNull(answerId);
        Poll poll = Answer.vote(answerId, request, response);
        renderJSONExclusion(poll);
    }
    
    private static void renderJSONExclusion(Object o) {
        throw new RenderJsonExclusion(o);
    }
}
