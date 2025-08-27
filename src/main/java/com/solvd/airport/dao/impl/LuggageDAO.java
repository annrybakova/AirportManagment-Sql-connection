package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.ILuggageDAO;
import com.solvd.airport.models.Luggage;

public class LuggageDAO implements ILuggageDAO {
    private static final String T = "luggage";
    private static final String ID = "luggage_id";
    private static final String PASSENGER_ID = "passenger_id";
    private static final String WEIGHT = "weight";

    private static final String INSERT_SQL = "INSERT INTO " + T + " (" + PASSENGER_ID + "," + WEIGHT + ") VALUES (?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM " + T + " WHERE " + ID + "=?";
    // private static final String SELECT_ALL_SQL = "SELECT * FROM " + T;
    private static final String UPDATE_SQL = "UPDATE " + T + " SET " + PASSENGER_ID + "=?," + WEIGHT + "=? WHERE " + ID
            + "=?";
    private static final String DELETE_SQL = "DELETE FROM " + T + " WHERE " + ID + "=?";
    private static final String SELECT_BY_PASSENGER_SQL = "SELECT * FROM " + T + " WHERE " + PASSENGER_ID + "=?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Luggage l) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, l.getPassengerId());
                ps.setDouble(2, l.getWeight());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        l.setLuggageId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public Luggage getById(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_ID_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Luggage l = new Luggage();
                        l.setLuggageId(rs.getInt(ID));
                        l.setPassengerId(rs.getInt(PASSENGER_ID));
                        l.setWeight(rs.getDouble(WEIGHT));
                        return l;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    // @Override
    // public List<Luggage> getAll() {
    // List<Luggage> list = new ArrayList<>();
    // try (Connection c = pool.getConnection();
    // PreparedStatement ps = c.prepareStatement(SELECT_ALL_SQL);
    // ResultSet rs = ps.executeQuery()) {
    // while (rs.next()) {
    // Luggage l = new Luggage();
    // l.setLuggageId(rs.getInt(ID));
    // l.setPassengerId(rs.getInt(PASSENGER_ID));
    // l.setWeight(rs.getDouble(WEIGHT));
    // list.add(l);
    // }
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // return list;
    // }

    @Override
    public List<Luggage> getByPassengerId(int passengerId) {
        List<Luggage> list = new ArrayList<>();
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_PASSENGER_SQL)) {
                ps.setInt(1, passengerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Luggage l = new Luggage();
                        l.setLuggageId(rs.getInt(ID));
                        l.setPassengerId(rs.getInt(PASSENGER_ID));
                        l.setWeight(rs.getDouble(WEIGHT));
                        list.add(l);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public void update(Luggage l) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(UPDATE_SQL)) {
                ps.setInt(1, l.getPassengerId());
                ps.setDouble(2, l.getWeight());
                ps.setInt(3, l.getLuggageId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }

    @Override
    public void delete(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(DELETE_SQL)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }
}
