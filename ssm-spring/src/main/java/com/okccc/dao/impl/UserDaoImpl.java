package com.okccc.dao.impl;

import com.okccc.dao.UserDao;
import com.okccc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:15:52
 * @Desc:
 *
 * CREATE TABLE `t_user` (
 *   `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *   `username` varchar(20) DEFAULT NULL COMMENT '用户名',
 *   `balance` double(10,2) unsigned DEFAULT NULL COMMENT '余额',
 * PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * insert into `t_user` values (1,'admin',50);
 *
 * CREATE TABLE `t_book` (
 *   `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *   `book_name` varchar(20) DEFAULT NULL COMMENT '图书名称',
 *   `price` double(10,2) DEFAULT NULL COMMENT '价格',
 *   `stock` int(10) unsigned DEFAULT NULL COMMENT '库存',
 * PRIMARY KEY (`book_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * insert into `t_book` values (1,'斗破苍穹',80,100),(2,'斗罗大陆',50,100);
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> queryAll() {
        String sql = "select * from ssm.t_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Double getPriceByBookId(Integer bookId) {
        String sql = "select price from ssm.t_book where book_id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String sql = "update ssm.t_book set stock = stock - 1 where book_id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    @Override
    public void updateBalance(Integer userId, Double price) {
        String sql = "update ssm.t_user set balance = balance - ? where user_id = ?";
        jdbcTemplate.update(sql, price, userId);
    }
}

// 基于xml的自动装配(了解)
//public class UserDaoImpl implements UserDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<User> queryAll() {
//        String sql = "select * from ssm.t_user";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//    }
//}
