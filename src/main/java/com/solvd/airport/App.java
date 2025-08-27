package com.solvd.airport;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Airport;
import com.solvd.airport.models.Flight;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;
import com.solvd.airport.models.Role;
import com.solvd.airport.models.Staff;
import com.solvd.airport.models.Booking;
import com.solvd.airport.models.Payment;
import com.solvd.airport.models.Ticket;
import com.solvd.airport.services.impl.AirportService;
import com.solvd.airport.services.impl.FlightService;
import com.solvd.airport.services.impl.PassengerService;
import com.solvd.airport.services.impl.StaffService;
import com.solvd.airport.services.impl.TicketService;
import com.solvd.airport.services.impl.xmlServices.XmlBookingService;
import com.solvd.airport.services.impl.xmlServices.XmlPaymentService;
import com.solvd.airport.services.impl.xmlServices.XmlBookingJaxbService;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        AirportService airportService = new AirportService();
        FlightService flightService = new FlightService();
        PassengerService passengerService = new PassengerService();
        StaffService staffService = new StaffService();
        TicketService ticketService = new TicketService();

        // create Airline
        Airline airline = new Airline();
        airline.setName("Garden Beds");
        flightService.addAirline(airline);

        // create Airports
        Airport airport_a = new Airport();
        airport_a.setName("Salat Airport");
        airport_a.setCity("Tomatoland");
        airport_a.setCountry("US of Vegetables");
        airportService.add(airport_a);

        Airport airport_b = new Airport();
        airport_b.setName("Smoothie Airport");
        airport_b.setCity("Berryland");
        airport_b.setCountry("UK of Fruits");
        airportService.add(airport_b);

        // create a Flight
        Flight flight = new Flight();
        flight.setAirlineId(airline.getAirlineId());
        flight.setDepartureAirportId(airport_a.getAirportId());
        flight.setArrivalAirportId(airport_b.getAirportId());
        flight.setDepartureTime(LocalDateTime.now().plusDays(1));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(7));
        flightService.scheduleFlight(flight);

        // create a Passenger + Luggage
        Passenger pax = new Passenger();
        pax.setName("Ms Pumpkin");
        passengerService.add(pax);

        Luggage bag = new Luggage();
        bag.setPassengerId(pax.getPassengerId());
        bag.setWeight(18.5);
        passengerService.addLuggage(bag);

        // issue a Ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerId(pax.getPassengerId());
        ticket.setFlightId(flight.getFlightId());
        ticket.setSeatNumber("12A");
        ticket.setPrice(499.99);
        ticketService.add(ticket);

        // hire Staff + Role
        Role role = new Role(1, "Gate Agent");
        role.setRoleName("Gate Agent");
        staffService.addRole(role);

        Staff staff = new Staff();
        staff.setName("John Peackle");
        staff.setRoleId(role.getRoleId());
        staffService.add(staff);

        // update seat
        ticket.setSeatNumber("14C");
        ticketService.add(ticket);

        // XML parsing
        XmlBookingService bookingService = new XmlBookingService();
        XmlPaymentService paymentService = new XmlPaymentService();

        List<Booking> bookings = bookingService.getBookingsFromXml("src/main/resources/xml/booking.xml");
        List<Payment> payments = paymentService.getPaymentsFromXml("src/main/resources/xml/payment.xml");

        logger.info("Parsed Bookings:");
        bookings.forEach(b -> logger.info(b.toString()));

        logger.info("Parsed Payments:");
        payments.forEach(p -> logger.info(p.toString()));

        XmlBookingJaxbService xmlBookingService = new XmlBookingJaxbService();

        String bookingXml = "src/main/resources/xml/bookingXmlXsd.xml";
        String bookingXsd = "src/main/resources/xsd/booking.xsd";

        // Validate
        var bookingsXmlXsd = xmlBookingService.readBookings(bookingXml, bookingXsd);
        bookingsXmlXsd.forEach(b -> logger.info("XML Booking loaded: {}", b));
    }
}