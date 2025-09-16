package com.solvd.airport.services.interfaces;

import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;

public interface IPassengerService extends IService<Passenger> {
    void addLuggage(Luggage luggage);
    
}
