package com.solvd.airport.services.interfaces;

import java.util.List;

public interface IService<T> {
    void add(T entity);
    T getById(int id);
    // List<T> getAll();
    void update(T entity);
    void delete(int id);
}
