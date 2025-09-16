package com.solvd.airport.services.interfaces;

import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Role;
import com.solvd.airport.models.Staff;

public interface IStaffService extends IService<Staff> {
    void addRole(Role role);
    
}
