package weather.entity;

public class Coordinate {
    public Coordinate(double lat,double lon){
        this.lon=lon;
        this.lat=lat;
    }
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    private double lat;

}
