package com.solvd.airport.dao.interfaces;

import java.util.List;

public interface IGenericDAO<T> {
    void add(T entity);
    T getById(int id);
    // List<T> getAll();
    void update(T entity);
    void delete(int id);
}
