package com.solvd.airport.tests;

import com.solvd.airport.models.Ticket;
import com.solvd.airport.services.impl.TicketService;
import com.solvd.airport.services.interfaces.ITicketService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketServiceTest {
    private ITicketService ticketService;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        ticketService = new TicketService();
        Assert.assertNotNull(ticketService, "TicketService should be initialized");
    }

    @DataProvider(name = "ticketPrices")
    public Object[][] ticketPrices() {
        return new Object[][]{
                {199.99}, {299.50}, {450.00}
        };
    }

    @Test(dataProvider = "ticketPrices", groups = "regression")
    public void testAddTicket(double price) {
        Ticket ticket = new Ticket();
        ticket.setPassengerId(1);
        ticket.setFlightId(1);
        ticket.setSeatNumber("A1");
        ticket.setPrice(price);

        try {
            ticketService.add(ticket);
            Assert.assertTrue(ticket.getTicketId() > 0, "Ticket ID should be generated");
        } catch (Exception e) {
            Assert.fail("Exception occurred while adding ticket: " + e.getMessage(), e);
        }
    }

    @Test(groups = "smoke", dependsOnGroups = "regression")
    public void testGetTicketById() {
        Ticket ticket = ticketService.getById(1);
        Assert.assertNotNull(ticket, "Ticket should exist in the database");
    }
}
