package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IAirportDAO;
import com.solvd.airport.models.Airport;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirportMyBatisDAO implements IAirportDAO {

    private static final Logger logger = LogManager.getLogger(AirportMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public AirportMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Airport getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Airport airport = session.selectOne("com.solvd.airport.mybatis.mappers.AirportMapper.getById", id);
            logger.info("Fetched airport: {}", airport);
            return airport;
        }
    }

    @Override
    public void add(Airport airport) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.AirportMapper.insert", airport);
            logger.info("Inserted airport: {}", airport);
        }
    }

    @Override
    public void update(Airport airport) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.AirportMapper.update", airport);
            logger.info("Updated airport: {}", airport);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.AirportMapper.delete", id);
            logger.info("Deleted airport with id={}", id);
        }
    }
}

