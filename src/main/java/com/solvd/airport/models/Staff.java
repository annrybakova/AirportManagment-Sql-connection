package com.solvd.airport.models;

public class Staff {
    private int staffId;
    private String name;
    private int roleId;
    private int airportId;

    public Staff() {
    }

    public Staff(int staffId, String name, int roleId, int airportId) {
        this.staffId = staffId;
        this.name = name;
        this.roleId = roleId;
        this.airportId = airportId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name=" + name +
                ", roleId=" + roleId +
                ", airportId=" + airportId +
                '}';
    }
}