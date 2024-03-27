package com.okccc.proxy;

import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2023/10/17 18:41:36
 * @Desc:
 */
@Component
public class CalculatorDemo {

    public int add(int a, int b) {
//        System.out.println("[INFO] add 方法开始了,参数是: " + a + "," + b);
        int result = a + b;
        System.out.println("add方法是核心业务逻辑");
//        System.out.println("[INFO] add 方法结束了,结果是: " + result);
        return result;
    }
}
