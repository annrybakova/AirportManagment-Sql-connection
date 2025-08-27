package com.solvd.airport.services.impl;

import com.solvd.airport.dao.impl.PassengerDAO;
import com.solvd.airport.dao.impl.TicketDAO;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.LuggageDAO;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;
import com.solvd.airport.models.Ticket;
import com.solvd.airport.services.interfaces.IPassengerService;

public class PassengerService implements IPassengerService {
    private static final Logger logger = LogManager.getLogger(PassengerService.class);

    private PassengerDAO passengerDAO;
    private TicketDAO ticketDAO;
    private LuggageDAO luggageDAO;

    public PassengerService() {
        this.passengerDAO = new PassengerDAO();
        this.ticketDAO = new TicketDAO();
        this.luggageDAO = new LuggageDAO();
        logger.info("PassengerService initialized with PassengerDAO, TicketDAO and LuggageDAO");
    }

    // Passenger CRUD
    @Override
    public void add(Passenger passenger) {
        passengerDAO.add(passenger);
        logger.info("Added passenger: {}", passenger.getName());
    }

    @Override
    public Passenger getById(int id) {
        Passenger passenger = passengerDAO.getById(id);
        logger.info("Fetched passenger by id {}: {}", id, passenger != null ? passenger.getName() : "NOT FOUND");
        return passenger;
    }

    // @Override
    // public List<Passenger> getAll() {
    // List<Passenger> passengers = passengerDAO.getAll();
    // logger.info("Fetched all passengers, count={}", passengers.size());
    // return passengers;
    // }

    @Override
    public void update(Passenger passenger) {
        passengerDAO.update(passenger);
        logger.info("Updated passenger: {}", passenger.getName());
    }

    @Override
    public void delete(int id) {
        passengerDAO.delete(id);
        logger.info("Deleted passenger with id={}", id);
    }

    // Ticket Logic
    public void addTicket(Ticket ticket) {
        ticketDAO.add(ticket);
        logger.info("Added ticket with id={} for passengerId={}", ticket.getTicketId(), ticket.getPassengerId());
    }

    public Ticket getTicketById(int id) {
        Ticket ticket = ticketDAO.getById(id);
        logger.info("Fetched ticket by id {}: {}", id, ticket != null ? "FOUND" : "NOT FOUND");
        return ticket;
    }

    public List<Ticket> getTicketsForPassenger(int passengerId) {
        List<Ticket> tickets = ticketDAO.getByPassengerId(passengerId);
        logger.info("Fetched {} tickets for passengerId={}", tickets.size(), passengerId);
        return tickets;
    }

    // Luggage Logic
    public void addLuggage(Luggage luggage) {
        luggageDAO.add(luggage);
        logger.info("Added luggage id={} for passengerId={}", luggage.getLuggageId(), luggage.getPassengerId());
    }

    public List<Luggage> getLuggageForPassenger(int passengerId) {
        List<Luggage> luggageList = luggageDAO.getByPassengerId(passengerId);
        logger.info("Fetched {} luggage items for passengerId={}", luggageList.size(), passengerId);
        return luggageList;
    }

    public void deleteLuggage(int id) {
        luggageDAO.delete(id);
        logger.info("Deleted luggage with id={}", id);
    }
}
