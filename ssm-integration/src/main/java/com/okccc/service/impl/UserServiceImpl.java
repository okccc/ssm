package com.okccc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okccc.common.Result;
import com.okccc.common.ResultCodeEnum;
import com.okccc.mapper.UserMapper;
import com.okccc.pojo.PageBean;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/27 10:23:15
 * @Desc:
 *
 * userMapper提示报错是因为idea不知道UserMapper也是容器中的组件,如果有强迫症可以修改设置
 * IntelliJ IDEA - Settings - Editor - Inspections - 输入autowire - 取消勾选Spring
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result page(int pageSize, int currentPage) {
        // 设置分页参数
        PageHelper.startPage(currentPage, pageSize);  // sql limit x,y;

        // 查询数据
        List<User> list = userMapper.selectList();

        // 封装分页数据
        PageInfo<User> pageInfo = new PageInfo<>(list);

        // 封装成PageBean
        PageBean<User> pageBean = new PageBean<>(currentPage, pageSize, pageInfo.getTotal(), pageInfo.getList());

        return Result.build(pageBean, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result save(User user) {
        int rows = userMapper.insert(user);

        if (rows > 0) {
            return Result.build(ResultCodeEnum.SUCCESS);
        }

        return Result.build(ResultCodeEnum.FAIL);
    }

    @Override
    public Result update(User user) {
        // 判断id是否为空
        if (user.getId() == null) {
            return Result.build(ResultCodeEnum.FAIL);
        }

        int rows = userMapper.updateById(user);

        if (rows > 0) {
            return Result.build(ResultCodeEnum.SUCCESS);
        }

        return Result.build(ResultCodeEnum.FAIL);
    }

    @Override
    public Result remove(Integer id) {
        int rows = userMapper.deleteById(id);

        if (rows > 0) {
            return Result.build(ResultCodeEnum.SUCCESS);
        }

        return Result.build(ResultCodeEnum.FAIL);
    }

}
