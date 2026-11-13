package unil.rush.external;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import unil.rush.domain.Weather;

import static jakarta.ws.rs.client.ClientBuilder.newClient;

public class WeatherClient
{
    public String fetchinforaw()
    {
        Client client = newClient();
        String URL = "https://api.open-meteo.com/v1/forecast?latitude=46.52&longitude=6.58&current=temperature_2m,weather_code&timezone=Europe/Zurich";
        WebTarget target = client.target(URL);
        Invocation.Builder build = target.request(MediaType.APPLICATION_JSON);
        String end = build.get(String.class);
        return end;
    }
    public Weather fetchWeather()
    {
        Client client = newClient();
        String URL = "https://api.open-meteo.com/v1/forecast?latitude=46.52&longitude=6.58&current=temperature_2m,weather_code&timezone=Europe/Zurich";
        WebTarget target = client.target(URL);
        Invocation.Builder build = target.request(MediaType.APPLICATION_JSON);
        OpenMeteoResponse end = build.get(OpenMeteoResponse.class);
        Current end1 = end.getCurrent();
        int code = end1.getWeather_code();
        return Weather.fromCode(code);
    }

}
