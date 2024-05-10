package Prog4.Station.model;

import java.util.List;

public class Station {
    private int stationId;


    private String location;

    public Station(int stationId,  String location) {
        this.stationId = stationId;
        this.location=location;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", location='" + location + '\'' +
                '}';
    }
}
