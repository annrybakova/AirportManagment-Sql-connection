package com.solvd.airport.services.mybatisimpl;

import com.solvd.airport.dao.mybatisimpl.AirportMyBatisDAO;
import com.solvd.airport.models.Airport;
import com.solvd.airport.services.interfaces.IAirportService;
import com.solvd.airport.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;

public class AirportServiceMyBatis implements IAirportService {
    private final AirportMyBatisDAO dao;

    public AirportServiceMyBatis() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.dao = new AirportMyBatisDAO(sqlSessionFactory);
    }

    @Override
    public void add(Airport airport) {
        dao.add(airport);
    }

    @Override
    public Airport getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void update(Airport airport) {
        dao.update(airport);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
