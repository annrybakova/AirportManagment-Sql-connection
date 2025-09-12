package com.solvd.airport.services.mybatisimpl;

import com.solvd.airport.dao.mybatisimpl.AirlineMyBatisDAO;
import com.solvd.airport.dao.mybatisimpl.FlightMyBatisDAO;
import com.solvd.airport.models.Airline;
import com.solvd.airport.models.Flight;
import com.solvd.airport.services.interfaces.IFlightService;
import com.solvd.airport.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightServiceMyBatis implements IFlightService {
    private static final Logger logger = LogManager.getLogger(FlightServiceMyBatis.class);

    private final FlightMyBatisDAO flightDAO;
    private final AirlineMyBatisDAO airlineDAO;

    public FlightServiceMyBatis() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.flightDAO = new FlightMyBatisDAO(sqlSessionFactory);
        this.airlineDAO = new AirlineMyBatisDAO(sqlSessionFactory);
    }

    @Override
    public void add(Flight flight) {
        flightDAO.add(flight);
    }

    @Override
    public Flight getById(int id) {
        return flightDAO.getById(id);
    }

    @Override
    public void update(Flight flight) {
        flightDAO.update(flight);
    }

    @Override
    public void delete(int id) {
        flightDAO.delete(id);
    }

    @Override
    public void scheduleFlight(Flight flight) {
        flightDAO.add(flight);
    }

    public void addAirline(Airline airline) {
        airlineDAO.add(airline);
        logger.info("Added airline: {}", airline.getName());
    }

    public Airline getAirlineById(int id) {
        Airline airline = airlineDAO.getById(id);
        if (airline != null) {
            logger.info("Fetched airline with id={}", id);
        } else {
            logger.warn("No airline found with id={}", id);
        }
        return airline;
    }
}
