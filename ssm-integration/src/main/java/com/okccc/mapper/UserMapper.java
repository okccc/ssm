package com.okccc.mapper;

import com.okccc.pojo.User;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/26 19:01:22
 * @Desc:
 */
public interface UserMapper {

    List<User> selectList();

    int insert(User user);

    int updateById(User user);

    int deleteById(Integer id);

}
