package com.okccc.service;

import com.okccc.pojo.User;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:13:41
 * @Desc:
 */
public interface UserService {

    List<User> queryAll();

    // 买书
    void buyBook(int userId, int bookId);

    // 结账
    void checkout(int userId, Integer[] bookIds);
}
