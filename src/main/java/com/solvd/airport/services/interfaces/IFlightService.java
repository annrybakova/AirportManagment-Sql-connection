package com.solvd.airport.services.interfaces;

import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Flight;

public interface IFlightService extends IService<Flight> {
    void scheduleFlight(Flight flight);
    void addAirline(Airline airline);
}

