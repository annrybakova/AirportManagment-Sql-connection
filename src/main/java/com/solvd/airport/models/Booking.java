package com.solvd.airport.models;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Booking {
    @XmlAttribute(name = "id")
    private int bookingId;

    @XmlElement(name = "passengerId")
    private int passengerId;

    @XmlElement(name = "flightId")
    private int flightId;

    @XmlElement(name = "bookingDate")
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
                ", bookingDate='" + bookingDate + '\'' +
                '}';
    }
}
