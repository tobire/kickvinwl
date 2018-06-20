package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/{a:leaderboard|Leaderboard}")
public abstract class LeaderboardResource {

    @GET
    @Path("/season")
    @Produces(MediaType.APPLICATION_JSON)
    public abstract Response getSeasonLeaderboard();

    @GET
    @Path("/gameday")
    @Produces(MediaType.APPLICATION_JSON)
    public abstract Response getGamedayLeaderboard();

    @GET
    @Path("/alltime")
    @Produces(MediaType.APPLICATION_JSON)
    public abstract Response getAlltimeLeaderboard();
}