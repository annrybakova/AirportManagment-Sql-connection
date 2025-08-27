package com.solvd.airport.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.RoleDAO;
import com.solvd.airport.dao.impl.StaffDAO;
import com.solvd.airport.models.Role;
import com.solvd.airport.models.Staff;
import com.solvd.airport.services.interfaces.IStaffService;

public class StaffService implements IStaffService {
    private static final Logger logger = LogManager.getLogger(StaffService.class);
    private StaffDAO staffDAO;
    private RoleDAO roleDAO;

    public StaffService() {
        this.staffDAO = new StaffDAO();
        this.roleDAO = new RoleDAO();
        logger.info("StaffService initialized with StaffDAO and RoleDAO");
    }

    @Override
    public void add(Staff staff) {
        staffDAO.add(staff);
        logger.info("Added staff: {}", staff.getName());
    }

    @Override
    public Staff getById(int id) {
        Staff staff = staffDAO.getById(id);
        if (staff != null) {
            logger.info("Fetched staff by id {}: {}", id, staff.getName());
        } else {
            logger.warn("No staff found with id {}", id);
        }
        return staff;
    }

    // @Override
    // public List<Staff> getAll() {
    //     List<Staff> staffList = staffDAO.getAll();
    //     logger.info("Fetched {} staff records", staffList.size());
    //     return staffList;
    // }

    @Override
    public void update(Staff staff) {
        staffDAO.update(staff);
        logger.info("Updated staff: {}", staff.getName());
    }

    @Override
    public void delete(int id) {
        staffDAO.delete(id);
        logger.info("Deleted staff with id {}", id);
    }

    public void addRole(Role role) {
        roleDAO.add(role);
        logger.info("Added role: {}", role.getRoleName());
    }

    public Role getRoleById(int id) {
        Role role = roleDAO.getById(id);
        if (role != null) {
            logger.info("Fetched role by id {}: {}", id, role.getRoleName());
        } else {
            logger.warn("No role found with id {}", id);
        }
        return role;
    }
}
