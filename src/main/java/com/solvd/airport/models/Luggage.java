package com.solvd.airport.models;

public class Luggage {
    private int id;
    private int passengerId;
    private double weight;

    public Luggage() {
    }

    public Luggage(int id, int passengerId, double weight) {
        this.id = id;
        this.passengerId = passengerId;
        this.weight = weight;
    }

    public int getLuggageId() {
        return id;
    }

    public void setLuggageId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "luggageId=" + id +
                ", passengerId=" + id +
                ", weight=" + weight +
                '}';
    }
}
