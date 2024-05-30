package com.okccc.service;

import com.okccc.common.Result;
import com.okccc.pojo.User;

/**
 * @Author: okccc
 * @Date: 2023/12/27 10:22:36
 * @Desc:
 */
public interface UserService {

    Result page(int pageSize, int currentPage);

    Result save(User user);

    Result update(User user);

    Result remove(Integer id);

}
