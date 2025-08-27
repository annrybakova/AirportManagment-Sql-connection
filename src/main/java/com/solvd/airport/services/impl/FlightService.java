package com.solvd.airport.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.AirlineDAO;
import com.solvd.airport.dao.impl.FlightDAO;
import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Flight;
import com.solvd.airport.services.interfaces.IFlightService;

public class FlightService implements IFlightService {
    private static final Logger logger = LogManager.getLogger(FlightService.class);

    private final FlightDAO flightDAO;
    private final AirlineDAO airlineDAO;

    public FlightService() {
        this.flightDAO = new FlightDAO();
        this.airlineDAO = new AirlineDAO();
        logger.info("FlightService initialized with FlightDAO and AirlineDAO");
    }

    @Override
    public void scheduleFlight(Flight flight) {
        flightDAO.add(flight);
        logger.info("Scheduled flight id={} for airlineId={}", flight.getFlightId(), flight.getAirlineId());
    }

    @Override
    public void add(Flight flight) {
        flightDAO.add(flight);
        logger.info("Added flight: {}", flight.getFlightId());
    }

    @Override
    public Flight getById(int id) {
        Flight flight = flightDAO.getById(id);
        if (flight != null) {
            logger.info("Fetched flight with id={}", id);
        } else {
            logger.warn("No flight found with id={}", id);
        }
        return flight;
    }

    // @Override
    // public List<Flight> getAll() {
    //     List<Flight> flights = flightDAO.getAll();
    //     logger.info("Fetched {} flights", flights.size());
    //     return flights;
    // }

    @Override
    public void update(Flight flight) {
        flightDAO.update(flight);
        logger.info("Updated flight id={}", flight.getFlightId());
    }

    @Override
    public void delete(int id) {
        flightDAO.delete(id);
        logger.info("Deleted flight with id={}", id);
    }

    public void addAirline(Airline airline) {
        airlineDAO.add(airline);
        logger.info("Added airline: {}", airline.getName());
    }

    public Airline getAirlineById(int id) {
        Airline airline = airlineDAO.getById(id);
        if (airline != null) {
            logger.info("Fetched airline with id={}", id);
        } else {
            logger.warn("No airline found with id={}", id);
        }
        return airline;
    }
}
