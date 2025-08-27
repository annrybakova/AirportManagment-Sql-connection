package com.solvd.airport.models;

public class Route {
    private int id;
    private int departureAirportId;
    private int arrivalAirportId;
    private int distanceKm;

    public Route() {
    }

    public Route(int id, int departureAirportId, int arrivalAirportId, int distanceKm) {
        this.id = id;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.distanceKm = distanceKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(int arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + id +
                ", departureAirportId=" + departureAirportId +
                ", arrivalAirportId=" + arrivalAirportId +
                ", distanceKm=" + distanceKm +
                '}';
    }
}