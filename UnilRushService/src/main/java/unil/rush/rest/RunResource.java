package unil.rush.rest;

import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import unil.rush.domain.Run;
import unil.rush.state.ApplicationState;

import java.util.List;
import java.util.UUID;

@Path("/runs")
public class RunResource {
    @Inject
    private ApplicationState state;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> getAllRuns()
    {
        return(state.getAllRuns());
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Run getRun(@PathParam("id") UUID id)
    {
        if(state.getRun(id) == null)
            throw new NotFoundException("Run not found");
        return(state.getRun(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Run createRun(Run run)
    {

        if(state.addRun(run) == null)
            throw new NotFoundException("Player not found");
        return (run);
    }
    @Path("/{id}")
    @DELETE

    public void removeRun(@PathParam("id") UUID id)
    {
        if(!state.removeRun(id))
            throw new NotFoundException("Run not found");
    }
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRun(@PathParam("id")UUID id, Run changes)
    {
        if(!state.updateRun(id, changes))
            throw new NotFoundException("RUN not found");

    }
}


