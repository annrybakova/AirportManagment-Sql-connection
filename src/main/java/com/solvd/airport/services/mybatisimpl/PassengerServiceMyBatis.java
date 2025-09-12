package com.solvd.airport.services.mybatisimpl;

import com.solvd.airport.dao.mybatisimpl.LuggageMyBatisDAO;
import com.solvd.airport.dao.mybatisimpl.PassengerMyBatisDAO;
import com.solvd.airport.dao.mybatisimpl.TicketMyBatisDAO;
import com.solvd.airport.models.Luggage;
import com.solvd.airport.models.Passenger;
import com.solvd.airport.services.interfaces.IPassengerService;
import com.solvd.airport.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassengerServiceMyBatis implements IPassengerService {
    private static final Logger logger = LogManager.getLogger(PassengerServiceMyBatis.class);

    private final PassengerMyBatisDAO passengerDAO;
    private final TicketMyBatisDAO ticketDAO;
    private final LuggageMyBatisDAO luggageDAO;

    public PassengerServiceMyBatis() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.passengerDAO = new PassengerMyBatisDAO(sqlSessionFactory);
        this.ticketDAO = new TicketMyBatisDAO(sqlSessionFactory);
        this.luggageDAO = new LuggageMyBatisDAO(sqlSessionFactory);
    }

    @Override
    public void add(Passenger passenger) {
        passengerDAO.add(passenger);
    }

    @Override
    public Passenger getById(int id) {
        return passengerDAO.getById(id);
    }

    @Override
    public void update(Passenger passenger) {
        passengerDAO.update(passenger);
    }

    @Override
    public void delete(int id) {
        passengerDAO.delete(id);
    }

    public void addLuggage(Luggage luggage) {
        luggageDAO.add(luggage);
        logger.info("Added luggage id={} for passengerId={}", luggage.getLuggageId(), luggage.getPassengerId());
    }
}
