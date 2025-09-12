package com.solvd.airport.services.mybatisimpl;

import com.solvd.airport.dao.mybatisimpl.TicketMyBatisDAO;
import com.solvd.airport.models.Ticket;
import com.solvd.airport.services.interfaces.ITicketService;
import com.solvd.airport.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSessionFactory;

public class TicketServiceMyBatis implements ITicketService {

    private final TicketMyBatisDAO dao;

    public TicketServiceMyBatis() {
        SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        this.dao = new TicketMyBatisDAO(sqlSessionFactory);
    }

    @Override
    public void add(Ticket ticket) {
        dao.add(ticket);
    }

    @Override
    public Ticket getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void update(Ticket ticket) {
        dao.update(ticket);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

}
