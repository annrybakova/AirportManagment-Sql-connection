package com.solvd.airport.services.interfaces;

import com.solvd.airport.models.Flight;

public interface IFlightService extends IService<Flight> {
    void scheduleFlight(Flight flight);
}

