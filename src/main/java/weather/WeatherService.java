package weather;

import weather.entity.*;
import weather.entity.System;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class WeatherService {

    public static int CURRENT_WEATHER = 0;
    WeatherConnectionUtils connectionUtils = new WeatherConnectionUtils();

    public Weather[] getCompleteWeatherForecast(String name) throws IOException {

        Weather currentWeather = getCurrentWeather(name);
        if (currentWeather.cod.equals("502")) throw new IOException("wrongCityName");
        Coordinate cord = new Coordinate(currentWeather.getCoord().getLat(), currentWeather.getCoord().getLon());
        connectionUtils.setZoneId(cord, currentWeather.getDt());

        WeatherForecast weather = getWeatherForecast(name);
        List<Object> list = Arrays.stream(weather.getList()).
                filter(WeatherPredicates.isNotCurrentDay(currentWeather)).
                filter(WeatherPredicates.isNoon()).
                collect(Collectors.toList());


        list.add(CURRENT_WEATHER, currentWeather);
        Weather[] weathers = new Weather[4];
        weathers = list.toArray(weathers);
        for (Weather singleWeather : weathers)
            singleWeather.addWeatherFormatting();

        return weathers;
    }

    private Weather getCurrentWeather(String cityName) throws IOException {

        String formattedCityName = cityName.replaceAll("\\s", "");
        java.lang.System.out.println(formattedCityName);
        String line = connectionUtils.getJSONResponse(WeatherConnectionUtils.weatherURL + formattedCityName + WeatherConnectionUtils.weatherKey);
        Weather weather = (Weather) connectionUtils.parseJson(line, Weather.class);
        Weather[] weathers;

      /* if (!weather.getCod().equals("502")) {
            Coordinate cord = new Coordinate(weather.getCoord().getLat(), weather.getCoord().getLon());
            connectionUtils.setZoneId(cord, weather.getDt());
            weathers = getCompleteWeatherForecast(formattedCityName, weather);
       } else {
            weathers = new Weather[]{weather};
        }*/
        return weather;
    }


    private WeatherForecast getWeatherForecast(String name) throws IOException {
        String line = connectionUtils.getJSONResponse(connectionUtils.weatherForecastUrl +
                name + connectionUtils.weatherForecastKey);
        return ((WeatherForecast) connectionUtils.parseJson(line, WeatherForecast.class));
    }


}
