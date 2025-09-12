package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IStaffDAO;
import com.solvd.airport.models.Staff;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaffMyBatisDAO implements IStaffDAO {

    private static final Logger logger = LogManager.getLogger(StaffMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public StaffMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Staff getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Staff staff = session.selectOne("com.solvd.airport.mybatis.mappers.StaffMapper.getById", id);
            logger.info("Fetched staff: {}", staff);
            return staff;
        }
    }

    @Override
    public void add(Staff staff) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.StaffMapper.insert", staff);
            logger.info("Inserted staff: {}", staff);
        }
    }

    @Override
    public void update(Staff staff) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.StaffMapper.update", staff);
            logger.info("Updated staff: {}", staff);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.StaffMapper.delete", id);
            logger.info("Deleted staff with id={}", id);
        }
    }
}


