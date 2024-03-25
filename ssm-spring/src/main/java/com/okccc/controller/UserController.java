package com.okccc.controller;

import com.okccc.pojo.User;
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
