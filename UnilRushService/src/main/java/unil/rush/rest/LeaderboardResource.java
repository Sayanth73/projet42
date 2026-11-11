package unil.rush.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import unil.rush.domain.Faculty;
import unil.rush.domain.Run;
import unil.rush.state.ApplicationState;

import java.util.List;

@Path("/leaderboard")
public class LeaderboardResource {


    @Inject
    private ApplicationState state;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> getLeaderboard(@DefaultValue("10") @QueryParam("limit")int limit, @QueryParam("faculty") Faculty faculty)
    {
        if (faculty == null)
            return (state.getLeaderboard().top(limit));
        else
            return(state.getLeaderboard().topByFaculty(faculty,limit));
    }
}
