package weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import weather.entity.Coordinate;
import weather.entity.Zone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class WeatherConnectionUtils {
    public static final String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String weatherKey = "&APPID=18f5a5a69169c44e3cc45e6297f8014b";
    public static final String timeZoneUrl = "https://maps.googleapis.com/maps/api/timezone/json?location=";
    public static final String timeZoneKey = "&key=AIzaSyBySlZRVQhi4w3d0_uCf77sWNg-nJnQErY";
    public static final String weatherForecastUrl="http://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String weatherForecastKey="&APPID=18f5a5a69169c44e3cc45e6297f8014b";
    public static String timeZone = "UTC";





    public String setZoneId(Coordinate coordinate, long timestamp) throws IOException{

        String json = getJSONResponse(WeatherConnectionUtils.timeZoneUrl
                + coordinate.getLat() + "," + coordinate.getLon() + "&timestamp=" +
                timestamp + WeatherConnectionUtils.timeZoneKey);
        timeZone=((Zone) parseJson(json, Zone.class)).getTimeZoneId();
        return timeZone;

    }

    public String getJSONResponse(String req) throws IOException{
        String line;

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(req);
        HttpResponse response = httpClient.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

        line = rd.lines().collect(Collectors.joining());

        return line;
    }

    public Object parseJson(String json, Class cl) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        try {
            Object object = cl.newInstance();
            object = gson.fromJson(json, cl);
            return object;
        } catch (Exception e) {
            return new Object();
        }

    }
}
