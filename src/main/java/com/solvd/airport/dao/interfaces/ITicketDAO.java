package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Ticket;

public interface ITicketDAO extends IGenericDAO<Ticket> {
    List<Ticket> getByPassengerId(int passengerId);

}
