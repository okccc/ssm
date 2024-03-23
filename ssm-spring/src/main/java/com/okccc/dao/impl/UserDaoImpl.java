package com.okccc.dao.impl;

import com.okccc.dao.UserDao;
import com.okccc.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:15:52
 * @Desc: 基于xml的自动装配(了解)
 *
 * CREATE TABLE `t_user` (
 *   `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *   `username` varchar(20) DEFAULT NULL COMMENT '用户名',
 *   `balance` double(10,2) unsigned DEFAULT NULL COMMENT '余额',
 * PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * insert into `t_user` values (1,'admin',50);
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> queryAll() {
        String sql = "select * from ssm.t_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
