package com.okccc.service.impl;

import com.okccc.dao.UserDao;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:14:45
 * @Desc:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }
}

// 基于xml的自动装配(了解)
//public class UserServiceImpl implements UserService {
//
//    private UserDao userDao;
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public List<User> queryAll() {
//        return userDao.queryAll();
//    }
//}