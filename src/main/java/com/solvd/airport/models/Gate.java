package com.solvd.airport.models;

public class Gate {
    private int gateId;
    private int airportId;
    private String gateNumber;

    public Gate() {
    }

    public Gate(int gateId, int airportId, String gateNumber) {
        this.gateId = gateId;
        this.airportId = airportId;
        this.gateNumber = gateNumber;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "gateId=" + gateId +
                ", airportId=" + airportId +
                ", gateNumber=" + gateNumber +
                '}';
    }
}
