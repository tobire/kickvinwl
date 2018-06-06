package resources;

import entities.Match;
import entities.MatchTip;
import entities.Team;
import entities.User;
import persistence.MatchPersistenceService;
import persistence.MatchTipPersistenceService;
import persistence.UserPersistenceService;
import resources.datamodel.Tip;
import resources.datamodel.MatchTipTransform;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class TipResourceImpl extends TipResource {

    @Override
    public Response setTip(String token, int gameday, String season, ArrayList<Tip> tipList) {
        response = Response.accepted().build();

        try {
            UserPersistenceService.getInstance().getBySessionKey(token);
            // TODO: Persistieren der Matches
            response = Response.accepted().build();
        }
        catch (SecurityException | NoResultException exception) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }

        return response;
    }



    @Override
    public Response getTipByToken(String gameday, String token) {
        Response.ResponseBuilder response = Response.accepted();



        //Token -> User
        User user = UserPersistenceService.getInstance().getBySessionKey(token);

        //MatchTipTransform füllen
        MatchTipTransform matchTip = new MatchTipTransform("2017/18", gameday, user.getTips()); //TODO

        return response.entity(matchTip).build();
    }


}