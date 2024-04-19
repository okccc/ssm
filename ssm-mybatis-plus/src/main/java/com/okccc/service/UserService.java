package com.okccc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.okccc.pojo.User;

/**
 * @Author: okccc
 * @Date: 2024/1/2 16:29:43
 * @Desc: Service层对Mapper层做了进一步封装,多了些批处理方法,效率略低但功能更加丰富
 */
public interface UserService extends IService<User> {
}
