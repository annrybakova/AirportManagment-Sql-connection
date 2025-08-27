package com.solvd.airport.models;

public class CrewRole {
    private int crewId;
    private int roleId;

    public CrewRole() {
    }

    public CrewRole(int crewId, int roleId) {
        this.crewId = crewId;
        this.roleId = roleId;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "CrewRole{" +
                "crewId=" + crewId +
                ", roleId=" + roleId +
                '}';
    }
}
