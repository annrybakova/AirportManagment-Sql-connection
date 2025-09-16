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

    private Flight(Builder b) {
        this.id = b.id;
        this.airlineId = b.airlineId;
        this.departureAirportId = b.departureAirportId;
        this.arrivalAirportId = b.arrivalAirportId;
        this.departureTime = b.departureTime;
        this.arrivalTime = b.arrivalTime;
    }

    public static class Builder {
        private int id;
        private int airlineId;
        private int departureAirportId;
        private int arrivalAirportId;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;

        public Builder withAirlineId(int id){ this.airlineId = id; return this; }
        public Builder fromAirport(int id){ this.departureAirportId = id; return this; }
        public Builder toAirport(int id){ this.arrivalAirportId = id; return this; }
        public Builder departAt(LocalDateTime dt){ this.departureTime = dt; return this; }
        public Builder arriveAt(LocalDateTime dt){ this.arrivalTime = dt; return this; }
        public Flight build(){ return new Flight(this); }
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
