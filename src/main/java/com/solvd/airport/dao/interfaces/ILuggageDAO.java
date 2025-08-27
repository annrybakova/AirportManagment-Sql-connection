package com.solvd.airport.dao.interfaces;

import java.util.List;

import com.solvd.airport.models.Luggage;

public interface ILuggageDAO extends IGenericDAO<Luggage> {
    List<Luggage> getByPassengerId(int passengerId);
}
