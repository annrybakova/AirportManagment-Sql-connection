package com.solvd.airport.models;

public class Passenger {
    private int id;
    private String name;
    private String email;
    private String passportNumber;

    public Passenger() {
    }

    public Passenger(int id, String name, String email, String passportNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public int getPassengerId() {
        return id;
    }

    public void setPassengerId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + id +
                ", name=" + name +
                ", email=" + email +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
