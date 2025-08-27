package com.solvd.airport.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Airline {
    private int id;
    private String name;
    private String country;

    public Airline() {
    }

    public Airline(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getAirlineId() {
        return id;
    }

    public void setAirlineId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("airlineId", id)
                .append("name", name)
                .append("country", country)
                .toString();
    }
}
