package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IFlightDAO;
import com.solvd.airport.models.Flight;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightMyBatisDAO implements IFlightDAO {

    private static final Logger logger = LogManager.getLogger(FlightMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public FlightMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Flight getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Flight flight = session.selectOne("com.solvd.airport.mybatis.mappers.FlightMapper.getById", id);
            logger.info("Fetched flight: {}", flight);
            return flight;
        }
    }

    @Override
    public void add(Flight flight) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.FlightMapper.insert", flight);
            logger.info("Inserted flight: {}", flight);
        }
    }

    @Override
    public void update(Flight flight) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.FlightMapper.update", flight);
            logger.info("Updated flight: {}", flight);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.FlightMapper.delete", id);
            logger.info("Deleted flight with id={}", id);
        }
    }
}


