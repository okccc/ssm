package com.okccc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.okccc.mapper.UserMapper;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: okccc
 * @Date: 2024/1/2 16:30:18
 * @Desc:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
