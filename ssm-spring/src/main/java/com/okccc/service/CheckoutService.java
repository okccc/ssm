package com.okccc.service;

/**
 * @Author: okccc
 * @Date: 2023/10/10 11:11:48
 * @Desc:
 */
public interface CheckoutService {

    // 结账
    void checkout(int userId, Integer[] bookIds);
}
