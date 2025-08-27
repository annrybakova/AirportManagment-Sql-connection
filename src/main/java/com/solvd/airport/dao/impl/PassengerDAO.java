package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.IPassengerDAO;
import com.solvd.airport.models.Passenger;

public class PassengerDAO implements IPassengerDAO {
    private static final String T = "passengers";
    private static final String ID = "passenger_id";
    private static final String NAME = "name";

    private static final String INSERT_SQL = "INSERT INTO " + T + " (" + NAME + ") VALUES (?)";
    private static final String SELECT_BY_ID_SQL = "SELECT " + ID + "," + NAME + " FROM " + T + " WHERE " + ID + "=?";
    // private static final String SELECT_ALL_SQL = "SELECT " + ID + "," + NAME + "
    // FROM " + T;
    private static final String UPDATE_SQL = "UPDATE " + T + " SET " + NAME + "=? WHERE " + ID + "=?";
    private static final String DELETE_SQL = "DELETE FROM " + T + " WHERE " + ID + "=?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Passenger p) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        p.setPassengerId(rs.getInt(1));
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
    public Passenger getById(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_ID_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Passenger p = new Passenger();
                        p.setPassengerId(rs.getInt(ID));
                        p.setName(rs.getString(NAME));
                        return p;
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
    // public List<Passenger> getAll() {
    // List<Passenger> list = new ArrayList<>();
    // try (Connection c = pool.getConnection();
    // PreparedStatement ps = c.prepareStatement(SELECT_ALL_SQL);
    // ResultSet rs = ps.executeQuery()) {
    // while (rs.next()) {
    // Passenger p = new Passenger();
    // p.setPassengerId(rs.getInt(ID));
    // p.setName(rs.getString(NAME));
    // list.add(p);
    // }
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // return list;
    // }

    @Override
    public void update(Passenger p) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(UPDATE_SQL)) {
                ps.setString(1, p.getName());
                ps.setInt(2, p.getPassengerId());
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
