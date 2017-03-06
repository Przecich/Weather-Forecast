package weather.entity;

public class WeatherForecast {

    public Weather[] getList() {
        return list;
    }

    public void setList(Weather[] list) {
        this.list = list;
    }

    private Weather [] list;
}

