package com.solvd.airport.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.interfaces.IStaffDAO;
import com.solvd.airport.models.Staff;

public class StaffDAO implements IStaffDAO {
    private static final String T = "staff";
    private static final String ID = "staff_id";
    private static final String ROLE_ID = "role_id";
    private static final String NAME = "name";

    private static final String INSERT_SQL = "INSERT INTO " + T + " (" + ROLE_ID + "," + NAME + ") VALUES (?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM " + T + " WHERE " + ID + "=?";
    // private static final String SELECT_ALL_SQL = "SELECT * FROM " + T;
    private static final String UPDATE_SQL = "UPDATE " + T + " SET " + ROLE_ID + "=?, " + NAME + "=? WHERE " + ID
            + "=?";
    private static final String DELETE_SQL = "DELETE FROM " + T + " WHERE " + ID + "=?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Staff s) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, s.getRoleId());
                ps.setString(2, s.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        s.setStaffId(rs.getInt(1));
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
    public Staff getById(int id) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(SELECT_BY_ID_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Staff s = new Staff();
                        s.setStaffId(rs.getInt(ID));
                        s.setRoleId(rs.getInt(ROLE_ID));
                        s.setName(rs.getString(NAME));
                        return s;
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
    // public List<Staff> getAll() {
    // List<Staff> list = new ArrayList<>();
    // try (Connection c = pool.getConnection();
    // PreparedStatement ps = c.prepareStatement(SELECT_ALL_SQL);
    // ResultSet rs = ps.executeQuery()) {
    // while (rs.next()) {
    // Staff s = new Staff();
    // s.setStaffId(rs.getInt(ID));
    // s.setRoleId(rs.getInt(ROLE_ID));
    // s.setName(rs.getString(NAME));
    // list.add(s);
    // }
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // return list;
    // }

    @Override
    public void update(Staff s) {
        Connection c = null;
        try {
            c = pool.getConnection();
            try (PreparedStatement ps = c.prepareStatement(UPDATE_SQL)) {
                ps.setInt(1, s.getRoleId());
                ps.setString(2, s.getName());
                ps.setInt(3, s.getStaffId());
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