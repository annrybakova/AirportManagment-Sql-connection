package com.solvd.airport.tests;

import com.solvd.airport.models.Passenger;
import com.solvd.airport.services.impl.PassengerService;
import com.solvd.airport.services.interfaces.IPassengerService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.*;

public class PassengerServiceTest {
    private IPassengerService passengerService;

    @BeforeMethod
    public void setup() {
        passengerService = new PassengerService();
    }

    @Test
    public void testAddPassenger() {
        Passenger passenger = new Passenger();
        passenger.setName("Eggplant Joe");
        passengerService.add(passenger);
        Assert.assertTrue(passenger.getPassengerId() > 0);
    }

    @Test(dependsOnMethods = "testAddPassenger")
    public void testGetPassengerById() {
        Passenger passenger = passengerService.getById(1);
        Assert.assertNotNull(passenger);
    }
}
