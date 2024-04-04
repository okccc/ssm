package com.okccc.controller;

import com.okccc.pojo.User;
import com.okccc.service.CheckoutService;
import com.okccc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/4 10:12:45
 * @Desc:
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckoutService checkoutService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public void queryAll() {
        List<User> users = userService.queryAll();
        System.out.println(users);
    }

    public void buyBook(int userId, int bookId) {
        userService.buyBook(userId, bookId);
    }

    public void checkout(int userId, Integer[] bookIds) {
        // 对比相同类和不同类在调用方法时的事务传播行为,加深对声明式事务的理解
//        userService.checkout(userId, bookIds);
        checkoutService.checkout(userId, bookIds);
    }
}

// 基于xml的自动装配(了解)
//public class UserController {
//
//    private UserService userService;
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    public void queryAll() {
//        List<User> users = userService.queryAll();
//        System.out.println(users);
//    }
//}
