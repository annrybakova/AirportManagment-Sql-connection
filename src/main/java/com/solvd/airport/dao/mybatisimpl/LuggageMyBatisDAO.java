package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.ILuggageDAO;
import com.solvd.airport.models.Luggage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LuggageMyBatisDAO implements ILuggageDAO {

    private static final Logger logger = LogManager.getLogger(LuggageMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public LuggageMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Luggage getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Luggage luggage = session.selectOne("com.solvd.airport.mybatis.mappers.LuggageMapper.getById", id);
            logger.info("Fetched luggage: {}", luggage);
            return luggage;
        }
    }

    @Override
    public void add(Luggage luggage) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.LuggageMapper.insert", luggage);
            logger.info("Inserted luggage: {}", luggage);
        }
    }

    @Override
    public void update(Luggage luggage) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.LuggageMapper.update", luggage);
            logger.info("Updated luggage: {}", luggage);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.LuggageMapper.delete", id);
            logger.info("Deleted luggage with id={}", id);
        }
    }

    @Override
    public List<Luggage> getByPassengerId(int passengerId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Luggage> luggageList = session.selectList(
                    "com.solvd.airport.mybatis.mappers.LuggageMapper.getByPassengerId",
                    passengerId
            );
            logger.info("Fetched {} luggage items for passengerId={}", luggageList.size(), passengerId);
            return luggageList;
        }
    }
}



