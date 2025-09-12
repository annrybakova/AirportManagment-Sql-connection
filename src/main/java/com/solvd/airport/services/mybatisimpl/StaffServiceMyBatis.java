package com.solvd.airport.services.mybatisimpl;

import com.solvd.airport.dao.mybatisimpl.RoleMyBatisDAO;
import com.solvd.airport.dao.mybatisimpl.StaffMyBatisDAO;
import com.solvd.airport.models.Role;
import com.solvd.airport.models.Staff;
import com.solvd.airport.services.interfaces.IStaffService;
import com.solvd.airport.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaffServiceMyBatis implements IStaffService {
    private static final Logger logger = LogManager.getLogger(StaffServiceMyBatis.class);

    private final StaffMyBatisDAO staffDAO;
    private final RoleMyBatisDAO roleDAO;

    public StaffServiceMyBatis() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.staffDAO = new StaffMyBatisDAO(sqlSessionFactory);
        this.roleDAO = new RoleMyBatisDAO(sqlSessionFactory);
    }

    @Override
    public void add(Staff staff) {
        staffDAO.add(staff);
    }

    @Override
    public Staff getById(int id) {
        return staffDAO.getById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDAO.update(staff);
    }

    @Override
    public void delete(int id) {
        staffDAO.delete(id);
    }

    public void addRole(Role role) {
        roleDAO.add(role);
        logger.info("Added role: {}", role.getRoleName());
    }
}
