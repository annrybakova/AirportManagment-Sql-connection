package com.solvd.airport.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.TicketDAO;
import com.solvd.airport.models.Ticket;
import com.solvd.airport.services.interfaces.ITicketService;

public class TicketService implements ITicketService {
    private static final Logger logger = LogManager.getLogger(TicketService.class);
    private TicketDAO ticketDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAO();
        logger.info("TicketService initialized with TicketDAO");
    }

    @Override
    public void add(Ticket ticket) {
        ticketDAO.add(ticket);
        logger.info("Added ticket with id {} for passenger {}", ticket.getTicketId(), ticket.getPassengerId());
    }

    @Override
    public Ticket getById(int id) {
        Ticket ticket = ticketDAO.getById(id);
        if (ticket != null) {
            logger.info("Fetched ticket by id {}: {}", id, ticket);
        } else {
            logger.warn("No ticket found with id {}", id);
        }
        return ticket;
    }

    // @Override
    // public List<Ticket> getAll() {
    //     List<Ticket> tickets = ticketDAO.getAll();
    //     logger.info("Fetched {} tickets", tickets.size());
    //     return tickets;
    // }

    @Override
    public void update(Ticket ticket) {
        ticketDAO.update(ticket);
        logger.info("Updated ticket with id {}", ticket.getTicketId());
    }

    @Override
    public void delete(int id) {
        ticketDAO.delete(id);
        logger.info("Deleted ticket with id {}", id);
    }
}
