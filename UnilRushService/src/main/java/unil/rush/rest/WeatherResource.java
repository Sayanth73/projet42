package unil.rush.rest;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import unil.rush.external.WeatherClient;


@Path("/weather")
public class WeatherResource {
    @GET
    @Path("/raw")
    @Produces(MediaType.APPLICATION_JSON)
    public String raw() {
        WeatherClient client = new WeatherClient();
        return client.fetchinforaw();
    }
}

