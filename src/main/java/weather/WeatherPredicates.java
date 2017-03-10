package weather;

import weather.entity.Weather;

import java.util.function.Predicate;

public class WeatherPredicates {
    public static Predicate<Weather> isNoon(){
        return  x->x.getFormatedTime( "HH:mm").equals("12:00") ||
                x.getFormatedTime( "HH:mm").equals("11:00") ||
                x.getFormatedTime("HH:mm").equals("10:00");
    }
    public static Predicate<Weather> isNotCurrentDay(Weather currentWeather){
        return x -> !x.getFormatedTime("EEEE").equals(currentWeather.getFormatedTime( "EEEE"));
    }
}
