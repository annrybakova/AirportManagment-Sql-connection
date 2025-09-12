package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.ITicketDAO;
import com.solvd.airport.models.Ticket;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketMyBatisDAO implements ITicketDAO {

    private static final Logger logger = LogManager.getLogger(TicketMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public TicketMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Ticket getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Ticket ticket = session.selectOne("com.solvd.airport.mybatis.mappers.TicketMapper.getById", id);
            logger.info("Fetched ticket: {}", ticket);
            return ticket;
        }
    }

    @Override
    public void add(Ticket ticket) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.TicketMapper.insert", ticket);
            logger.info("Inserted ticket: {}", ticket);
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.TicketMapper.update", ticket);
            logger.info("Updated ticket: {}", ticket);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.TicketMapper.delete", id);
            logger.info("Deleted ticket with id={}", id);
        }
    }

        @Override
    public List<Ticket> getByPassengerId(int passengerId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Ticket> ticketList = session.selectList(
                    "com.solvd.airport.mybatis.mappers.TicketMapper.getByPassengerId",
                    passengerId
            );
            logger.info("Fetched {} ticket items for passengerId={}", ticketList.size(), passengerId);
            return ticketList;
        }
    }
}



