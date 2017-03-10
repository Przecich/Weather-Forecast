package weather.entity;


import weather.WeatherConnectionUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Weather {
    private WeatherConnectionUtils jsonUtil = new WeatherConnectionUtils();
    private SimpleWeather main;
    private Wind wind;
    private Clouds clouds;
    private WeatherDesc[] weather;
    private long dt;

    public String date;
    public String dateWeek;
    private Coordinate coord;
    public String name;
    private System sys;
    public String dayOfWeek;
    public String cod;


    public void addWeatherFormatting() {


        String weatherCondition = getWeather()[0].getDescription();
        String upperCaseWeatherCondition = weatherCondition.substring(0, 1).toUpperCase() + weatherCondition.substring(1);

        getWeather()[0].setDescription(upperCaseWeatherCondition);
        getMain().setCelsiusTemp((int) (getMain().getTemp() - 273.15));
        getWeather()[0].setIcon("res/icons/" + getWeather()[0].getIcon() + ".png");

        sys.setSunRise(getFormatedTime("HH:mm", sys.sunrise));
        sys.setSunSet(getFormatedTime("HH:mm", sys.sunset));

        date = getFormatedTime("EEEE HH:mm");
        dateWeek = getFormatedTime("dd.MM");
        dayOfWeek = getFormatedTime("EEEE");


    }

    public String getFormatedTime(String pattern) {
        return getFormatedTime(pattern, dt);
    }

    public String getFormatedTime(String pattern, long unixTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of(jsonUtil.timeZone)).format(formatter);

    }


    public System getSys() {
        return sys;
    }

    public void setSys(System sys) {
        this.sys = sys;
    }


    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }


    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }


    public String getDateWeek() {
        return dateWeek;
    }

    public void setDateWeek(String dateWeek) {
        this.dateWeek = dateWeek;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public String getName() {
        return name;
    }


    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WeatherDesc[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherDesc[] weather) {
        this.weather = weather;
    }

    public SimpleWeather getMain() {
        return main;
    }

    public void setMain(SimpleWeather main) {
        this.main = main;
    }

}
