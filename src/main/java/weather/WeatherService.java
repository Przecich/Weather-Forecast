package weather;

import com.google.gson.internal.ObjectConstructor;
import weather.entity.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherService {
    Weather weather;
    private String timeZone;
    public static int CURRENT_WEATHER = 0;

    public Weather[] getCurrentWeather(String cityName) throws ClientProtocolException, IOException {


        String line = getJSONResponse(JsonUtil.weatherURL + cityName + JsonUtil.weatherKey);

        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        Weather weather = gson.fromJson(line, Weather.class);
        Weather[] weathers;

        if (!weather.getCod().equals("502")) {
            Coordinate cord = new Coordinate(weather.getCoord().getLat(), weather.getCoord().getLon());
            timeZone = getZoneId(cord, weather.getDt());
            weathers = getCompleteWeatherForecast(cityName, weather);
        } else {
            weathers = new Weather[]{weather};
        }
        return weathers;
    }

    private void addWeatherFormatting(Weather weather) {

        String weatherCondition = weather.getWeather()[0].getDescription();
        String upperCaseWeatherCondition = weatherCondition.substring(0, 1).toUpperCase() + weatherCondition.substring(1);

        weather.getWeather()[0].setDescription(upperCaseWeatherCondition);
        weather.getMain().setCelsiusTemp((int) (weather.getMain().getTemp() - 273.15));
        weather.getWeather()[0].setIcon("res/icons/" + weather.getWeather()[0].getIcon() + ".png");

        weather.getSys().setSunRise(getForecastTime(weather.getSys().getSunrise(), "HH:mm"));
        weather.getSys().setSunSet(getForecastTime(weather.getSys().getSunset(), "HH:mm"));

        weather.date = getForecastTime(weather.getDt(), "EEEE HH:mm");
        weather.dateWeek = getForecastTime(weather.getDt(), "dd.MM");
        weather.dayOfWeek = getForecastTime(weather.getDt(), "EEEE");


    }

    private Weather[] getCompleteWeatherForecast(String name, Weather currentWeather) {


        String line = getJSONResponse("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&APPID=18f5a5a69169c44e3cc45e6297f8014b");
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        WeatherForecast weather = gson.fromJson(line, WeatherForecast.class);

        List<Object> list = Arrays.stream(weather.getList()).
                filter(x -> !getForecastTime(x.getDt(), "EEEE").equals(getForecastTime(currentWeather.getDt(), "EEEE"))).
                filter(x -> getForecastTime(x.getDt(), "HH:mm").equals("12:00") ||
                        getForecastTime(x.getDt(), "HH:mm").equals("11:00") ||
                        getForecastTime(x.getDt(), "HH:mm").equals("10:00")).
                collect(Collectors.toList());


        list.add(CURRENT_WEATHER, currentWeather);

        Weather[] weathers = new Weather[4];
        weathers = list.toArray(weathers);
        for (Weather object : weathers)
            addWeatherFormatting(object);


        return weathers;
    }

    private Weather[] getWeatherForecast(String name) {
        String line = getJSONResponse("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&APPID=18f5a5a69169c44e3cc45e6297f8014b");
        return ((WeatherForecast)parseJson(line,WeatherDesc.class)).getList();
    }

    private String getForecastTime(Long timeStamp, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return Instant.ofEpochSecond(timeStamp).atZone(ZoneId.of(timeZone)).format(formatter);
    }

    private String getZoneId(Coordinate coordinate, long timestamp) {

        String json = getJSONResponse(JsonUtil.timeZoneUrl
                + coordinate.getLat() + "," + coordinate.getLon() + "&timestamp=" +
                timestamp + JsonUtil.timeZoneKey);
        return ((Zone) parseJson(json, Zone.class)).getTimeZoneId();

    }

    private String getJSONResponse(String req) {
        String line;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(req);
            HttpResponse response = httpClient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

            line = rd.lines().collect(Collectors.joining());
        } catch (IOException e) {
            line = "";
        }
        return line;
    }

    private Object parseJson(String json, Class cl) {
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
