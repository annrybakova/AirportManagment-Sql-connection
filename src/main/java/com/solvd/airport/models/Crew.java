package com.solvd.airport.models;

public class Crew {
    private int crewId;
    private String name;

    public Crew() {
    }

    public Crew(int crewId, String name) {
        this.crewId = crewId;
        this.name = name;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewId=" + crewId +
                ", name=" + name +
                '}';
    }
}
