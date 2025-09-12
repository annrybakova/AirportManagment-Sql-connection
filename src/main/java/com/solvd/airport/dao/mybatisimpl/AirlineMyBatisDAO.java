package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IAirlineDAO;
import com.solvd.airport.models.Airline;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class AirlineMyBatisDAO implements IAirlineDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public AirlineMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(Airline a) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO.add", a);
        }
    }

    @Override
    public Airline getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO.getById", id);
        }
    }

    // @Override
    // public List<Airline> getAll() {
    //     try (SqlSession session = sqlSessionFactory.openSession()) {
    //         return session.selectList("com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO.getAll");
    //     }
    // }

    @Override
    public void update(Airline a) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO.update", a);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO.delete", id);
        }
    }
}
