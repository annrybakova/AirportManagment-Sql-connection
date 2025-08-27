package com.solvd.airport.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.AirportDAO;
import com.solvd.airport.models.Airport;
import com.solvd.airport.services.interfaces.IAirportService;

public class AirportService implements IAirportService {
    private static final Logger logger = LogManager.getLogger(AirportService.class);
    private AirportDAO airportDAO;

    public AirportService() {
        this.airportDAO = new AirportDAO();
        logger.info("AirportService initialized with AirportDAO");
    }

    @Override
    public void add(Airport airport) {
        logger.info("Adding airport: {}", airport);
        airportDAO.add(airport);
        logger.info("Airport added successfully with ID={}", airport.getAirportId());
    }

    @Override
    public Airport getById(int id) {
        logger.info("Fetching airport with ID={}", id);
        Airport airport = airportDAO.getById(id);
        if (airport != null) {
            logger.info("Fetched airport: {}", airport);
        } else {
            logger.warn("No airport found with ID={}", id);
        }
        return airport;
    }

    // @Override
    // public List<Airport> getAll() {
    // logger.info("Fetching all airports");
    // List<Airport> airports = airportDAO.getAll();
    // logger.info("Fetched {} airports", airports.size());
    // return airports;
    // }

    @Override
    public void update(Airport airport) {
        logger.info("Updating airport: {}", airport);
        airportDAO.update(airport);
        logger.info("Airport updated successfully with ID={}", airport.getAirportId());
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting airport with ID={}", id);
        airportDAO.delete(id);
        logger.info("Airport deleted with ID={}", id);
    }
}
