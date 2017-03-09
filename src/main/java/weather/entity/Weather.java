package weather.entity;

public class Weather {


    private SimpleWeather main;
    private Wind wind;
    private Clouds clouds;
    private WeatherDesc[] weather;
    private long dt;

    public String date;
    public String dateWeek;

    public System getSys() {
        return sys;
    }

    public void setSys(System sys) {
        this.sys = sys;
    }

    private System sys;

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    private Coordinate coord;
    public String name;


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

    public String cod;

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

    public String dayOfWeek;

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
