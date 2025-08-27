package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.IAirportDAO;
import com.solvd.airport.models.Airport;

public class AirportDAO implements IAirportDAO {
    private static final String T = "airports";
    private static final String ID = "airport_id";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";

    private static final String INSERT_SQL = "INSERT INTO " + T + " (" + NAME + "," + CITY + "," + COUNTRY
            + ") VALUES (?,?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT " + ID + "," + NAME + "," + CITY + "," + COUNTRY + " FROM "
            + T + " WHERE " + ID + "=?";
    // private static final String SELECT_ALL_SQL = "SELECT " + ID + "," + NAME +
    // "," + CITY + "," + COUNTRY + " FROM "
    // + T;
    private static final String UPDATE_SQL = "UPDATE " + T + " SET " + NAME + "=?," + CITY + "=?," + COUNTRY
            + "=? WHERE " + ID + "=?";
    private static final String DELETE_SQL = "DELETE FROM " + T + " WHERE " + ID + "=?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Airport a) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, a.getName());
                ps.setString(2, a.getCity());
                ps.setString(3, a.getCountry());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        a.setAirportId(rs.getInt(1));
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
    public Airport getById(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_ID_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Airport a = new Airport();
                        a.setAirportId(rs.getInt(ID));
                        a.setName(rs.getString(NAME));
                        a.setCity(rs.getString(CITY));
                        a.setCountry(rs.getString(COUNTRY));
                        return a;
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
    // public List<Airport> getAll() {
    // List<Airport> list = new ArrayList<>();
    // try (Connection c = pool.getConnection();
    // PreparedStatement ps = c.prepareStatement(SELECT_ALL_SQL);
    // ResultSet rs = ps.executeQuery()) {
    // while (rs.next()) {
    // Airport a = new Airport();
    // a.setAirportId(rs.getInt(ID));
    // a.setName(rs.getString(NAME));
    // a.setCity(rs.getString(CITY));
    // a.setCountry(rs.getString(COUNTRY));
    // list.add(a);
    // }
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // return list;
    // }

    @Override
    public void update(Airport a) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(UPDATE_SQL)) {
                ps.setString(1, a.getName());
                ps.setString(2, a.getCity());
                ps.setString(3, a.getCountry());
                ps.setInt(4, a.getAirportId());
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