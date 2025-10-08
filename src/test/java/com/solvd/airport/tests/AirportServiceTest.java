package com.solvd.airport.tests;

import com.solvd.airport.models.Airport;
import com.solvd.airport.services.impl.AirportService;
import com.solvd.airport.services.interfaces.IAirportService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.*;

public class AirportServiceTest {
    private IAirportService airportService;

    @BeforeClass
    public void setup() {
        airportService = new AirportService();
        System.out.println("AirportServiceTest started");
    }

    @Test(priority = 1)
    public void testAddAirport() {
        Airport airport = new Airport();
        airport.setName("Test Airport");
        airport.setCity("Test City");
        airport.setCountry("Testland");

        airportService.add(airport);

        Assert.assertTrue(airport.getAirportId() > 0, "Airport ID should be generated");
    }

    @Test(priority = 2)
    public void testGetAirportById() {
        Airport airport = airportService.getById(1);
        Assert.assertNotNull(airport, "Airport with ID 1 should exist");
    }

    @AfterClass
    public void teardown() {
        System.out.println("AirportServiceTest finished");
    }
}
