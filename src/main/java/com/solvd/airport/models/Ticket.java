package com.solvd.airport.models;

public class Ticket {
    private int id;
    private int flightId;
    private int passengerId;
    private double price;
    private String seatNumber;

    public Ticket() {
    }

    public Ticket(int id, int flightId, int passengerId, double price, String seatNumber) {
        this.id = id;
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public int getTicketId() {
        return id;
    }

    public void setTicketId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + id +
                ", flightId=" + flightId +
                ", passengerId=" + passengerId +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                '}';
    }

}
