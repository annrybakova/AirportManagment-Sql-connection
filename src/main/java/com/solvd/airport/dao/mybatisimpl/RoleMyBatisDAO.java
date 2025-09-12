package com.solvd.airport.dao.mybatisimpl;

import com.solvd.airport.dao.interfaces.IRoleDAO;
import com.solvd.airport.models.Role;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleMyBatisDAO implements IRoleDAO {

    private static final Logger logger = LogManager.getLogger(RoleMyBatisDAO.class);
    private final SqlSessionFactory sqlSessionFactory;

    public RoleMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Role getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Role role = session.selectOne("com.solvd.airport.mybatis.mappers.RoleMapper.getById", id);
            logger.info("Fetched role: {}", role);
            return role;
        }
    }

    @Override
    public void add(Role role) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.airport.mybatis.mappers.RoleMapper.insert", role);
            logger.info("Inserted role: {}", role);
        }
    }

    @Override
    public void update(Role role) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.airport.mybatis.mappers.RoleMapper.update", role);
            logger.info("Updated role: {}", role);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.airport.mybatis.mappers.RoleMapper.delete", id);
            logger.info("Deleted role with id={}", id);
        }
    }
}


