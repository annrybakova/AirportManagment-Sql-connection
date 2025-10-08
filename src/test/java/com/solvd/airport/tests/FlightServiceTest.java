package com.solvd.airport.tests;

import com.solvd.airport.factory.FactoryService;
import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Flight;
import com.solvd.airport.services.impl.FlightService;
import com.solvd.airport.services.interfaces.IFlightService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.*;

import java.time.LocalDateTime;

public class FlightServiceTest {
    private IFlightService flightService;
    private Airline airline;

    @BeforeClass
    public void setup() {
        flightService = new FlightService();
        airline = new Airline();
        airline.setName("TestAir");
        flightService.addAirline(airline);
    }

    @Test(priority = 1)
    public void testScheduleFlight() {
        Flight flight = new Flight.Builder()
                .withAirlineId(airline.getAirlineId())
                .fromAirport(1)
                .toAirport(2)
                .departAt(LocalDateTime.now().plusDays(1))
                .arriveAt(LocalDateTime.now().plusDays(1).plusHours(3))
                .build();

        flightService.scheduleFlight(flight);
        Assert.assertTrue(flight.getFlightId() > 0, "Flight ID should be generated");
    }

    @Test(priority = 2)
    public void testGetFlightById() {
        Flight flight = flightService.getById(1);
        Assert.assertNotNull(flight, "Flight should exist");
    }
}
