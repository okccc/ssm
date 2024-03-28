package com.okccc.dao;

import com.okccc.pojo.User;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:15:30
 * @Desc:
 */
public interface UserDao {

    /**
     * 查询所有用户
     */
    List<User> queryAll();

    /**
     * 根据id查询图书价格
     */
    Double getPriceByBookId(Integer bookId);

    /**
     * 更新图书库存
     */
    void updateStock(Integer bookId);

    /**
     * 更新用户余额
     */
    void updateBalance(Integer userId, Double price);
}
