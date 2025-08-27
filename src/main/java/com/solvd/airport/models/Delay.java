package com.solvd.airport.models;

public class Delay {
    private int delayId;
    private int flightId;
    private int delayMinutes;
    private String reason;

    public Delay() {
    }

    public Delay(int delayId, int flightId, int delayMinutes, String reason) {
        this.delayId = delayId;
        this.flightId = flightId;
        this.delayMinutes = delayMinutes;
        this.reason = reason;
    }

    public int getDelayId() {
        return delayId;
    }

    public void setDelayId(int delayId) {
        this.delayId = delayId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Delay{" +
                "delayId=" + delayId +
                ", flightId=" + flightId +
                ", delayMinutes=" + delayMinutes +
                ", reason=" + reason +
                '}';
    }
}
