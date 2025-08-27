package com.solvd.airport.models;

import java.time.LocalDateTime;

public class Flight {
    private int id;
    private int airlineId;
    private int departureAirportId;
    private int arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(int id, int airlineId, int departureAirportId, int arrivalAirportId, LocalDateTime departureTime,
            LocalDateTime arrivalTime) {
        this.id = id;
        this.airlineId = airlineId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getFlightId() {
        return id;
    }

    public void setFlightId(int id) {
        this.id = id;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + id +
                ", airlineId=" + airlineId +
                ", departureAirportId=" + departureAirportId +
                ", arrivalAirportId=" + arrivalAirportId +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
