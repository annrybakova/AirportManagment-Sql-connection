package com.solvd.airport.models;

public class Booking {
    private int bookingId;
    private int passengerId;
    private int flightId;
    private String bookingDate;

    public Booking() {
    }

    public Booking(int bookingId, int passengerId, int flightId, String bookingDate) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", flightId=" + flightId +
                ", bookingDate=" + bookingDate +
                '}';
    }
}
