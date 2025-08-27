package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.ITicketDAO;
import com.solvd.airport.models.Ticket;

public class TicketDAO implements ITicketDAO {
    private static final String T = "tickets";
    private static final String ID = "ticket_id";
    private static final String PASSENGER_ID = "passenger_id";
    private static final String FLIGHT_ID = "flight_id";
    private static final String SEAT = "seat_number";
    private static final String PRICE = "price";

    private static final String INSERT_SQL = "INSERT INTO " + T + " (" + PASSENGER_ID + "," + FLIGHT_ID + "," + SEAT
            + "," + PRICE
            + ") VALUES (?,?,?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM " + T + " WHERE " + ID + "=?";
    // private static final String SELECT_ALL_SQL = "SELECT * FROM " + T;
    private static final String UPDATE_SQL = "UPDATE " + T + " SET " + PASSENGER_ID + "=?," + FLIGHT_ID + "=?," + SEAT
            + "=?," + PRICE
            + "=? WHERE " + ID + "=?";
    private static final String DELETE_SQL = "DELETE FROM " + T + " WHERE " + ID + "=?";
    private static final String SELECT_BY_PASSENGER_SQL = "SELECT * FROM " + T + " WHERE " + PASSENGER_ID + "=?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Ticket t) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, t.getPassengerId());
                ps.setInt(2, t.getFlightId());
                ps.setString(3, t.getSeatNumber());
                ps.setDouble(4, t.getPrice());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        t.setTicketId(rs.getInt(1));
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
    public Ticket getById(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_ID_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Ticket t = new Ticket();
                        t.setTicketId(rs.getInt(ID));
                        t.setPassengerId(rs.getInt(PASSENGER_ID));
                        t.setFlightId(rs.getInt(FLIGHT_ID));
                        t.setSeatNumber(rs.getString(SEAT));
                        t.setPrice(rs.getDouble(PRICE));
                        return t;
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
    // public List<Ticket> getAll() {
    // List<Ticket> list = new ArrayList<>();
    // try (Connection c = pool.getConnection();
    // PreparedStatement ps = c.prepareStatement(SELECT_ALL_SQL);
    // ResultSet rs = ps.executeQuery()) {
    // while (rs.next()) {
    // Ticket t = new Ticket();
    // t.setTicketId(rs.getInt(ID));
    // t.setPassengerId(rs.getInt(PASSENGER_ID));
    // t.setFlightId(rs.getInt(FLIGHT_ID));
    // t.setSeatNumber(rs.getString(SEAT));
    // t.setPrice(rs.getDouble(PRICE));
    // list.add(t);
    // }
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // return list;
    // }

    @Override
    public void update(Ticket t) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(UPDATE_SQL)) {
                ps.setInt(1, t.getPassengerId());
                ps.setInt(2, t.getFlightId());
                ps.setString(3, t.getSeatNumber());
                ps.setDouble(4, t.getPrice());
                ps.setInt(5, t.getTicketId());
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

    @Override
    public List<Ticket> getByPassengerId(int passengerId) {
        List<Ticket> tickets = new ArrayList<>();
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_PASSENGER_SQL)) {
                ps.setInt(1, passengerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Ticket t = new Ticket();
                        t.setTicketId(rs.getInt(ID));
                        t.setPassengerId(rs.getInt(PASSENGER_ID));
                        t.setFlightId(rs.getInt(FLIGHT_ID));
                        t.setSeatNumber(rs.getString(SEAT));
                        t.setPrice(rs.getDouble(PRICE));
                        tickets.add(t);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(c);
        }
    }
}
