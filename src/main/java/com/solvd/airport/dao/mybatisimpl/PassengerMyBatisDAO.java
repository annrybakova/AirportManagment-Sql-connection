package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IPassengerDAO;
import com.solvd.airport.models.Passenger;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassengerMyBatisDAO implements IPassengerDAO {

    private static final Logger logger = LogManager.getLogger(PassengerMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public PassengerMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Passenger getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Passenger passenger = session.selectOne("com.solvd.airport.mybatis.mappers.PassengerMapper.getById", id);
            logger.info("Fetched passenger: {}", passenger);
            return passenger;
        }
    }

    @Override
    public void add(Passenger passenger) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.PassengerMapper.insert", passenger);
            logger.info("Inserted passenger: {}", passenger);
        }
    }

    @Override
    public void update(Passenger passenger) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.PassengerMapper.update", passenger);
            logger.info("Updated passenger: {}", passenger);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.PassengerMapper.delete", id);
            logger.info("Deleted passenger with id={}", id);
        }
    }
}
