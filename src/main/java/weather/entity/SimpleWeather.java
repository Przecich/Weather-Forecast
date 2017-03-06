package weather.entity;

public class SimpleWeather {
    private double temp;
    private int celsiusTemp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getCelsiusTemp() {
        return celsiusTemp;
    }

    public void setCelsiusTemp(int celsiusTemp) {
        this.celsiusTemp = celsiusTemp;
    }


}
