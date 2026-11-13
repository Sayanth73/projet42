package unil.rush.rest;

import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import unil.rush.domain.DefeatCause;
import unil.rush.domain.Faculty;
import unil.rush.domain.Run;
import unil.rush.domain.RunStatus;
import unil.rush.state.ApplicationState;

import java.awt.event.FocusEvent;
import java.util.List;
import java.util.UUID;

import static unil.rush.domain.RunStatus.ABANDONED;
import static unil.rush.domain.RunStatus.IN_PROGRESS;

@Path("/runs")
public class RunResource {
    @Inject
    private ApplicationState state;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> getAllRuns() {
        return (state.getAllRuns());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Run getRun(@PathParam("id") UUID id) {
        if (state.getRun(id) == null)
            throw new NotFoundException("Run not found");
        return (state.getRun(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Run createRun(Run run) {

        if (state.addRun(run) == null)
            throw new NotFoundException("Player not found");
        return (run);
    }

    @Path("/{id}")
    @DELETE

    public void removeRun(@PathParam("id") UUID id) {
        if (!state.removeRun(id))
            throw new NotFoundException("Run not found");
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRun(@PathParam("id") UUID id, Run changes) {
        if (!state.updateRun(id, changes))
            throw new NotFoundException("RUN not found");

    }

    @Path("/{id}/warnings")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Run addWarning(@PathParam("id") UUID id) {
        try {
            boolean test = state.addWarning(id);
            if (test == false)
                throw new NotFoundException("Run not found");
            else
                return (getRun(id));
        } catch (IllegalStateException e) {
            throw new WebApplicationException("the run is finished", 409);
        }
    }

    @Path("/{id}/abandon")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Run abandonRun(@PathParam("id") UUID id) {
        try {
            boolean test = state.abandonRun(id);
            if (test == false)
                throw new NotFoundException("run not found");
            else
                return (state.getRun(id));
        }
        catch (IllegalStateException e)
        {
            throw new WebApplicationException("the run is finished", 409 );
        }
    }

    @Path("/{id}/lose")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Run loseRun(@PathParam("id")UUID id, @QueryParam("cause")DefeatCause cause)
    {
        try{
            boolean test = state.loseRun(id,cause);
            if(test == false)
                throw new NotFoundException("run not found");
            else
                return(state.getRun(id));
        }
        catch(IllegalStateException e) {
            throw new WebApplicationException("the run is finished", 409 );
        }
        catch(IllegalArgumentException f ){
            throw new WebApplicationException(f.getMessage(), 400 );
        }
    }

}



