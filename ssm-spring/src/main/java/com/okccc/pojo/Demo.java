package com.okccc.pojo;

import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2023/10/2 15:33:48
 * @Desc:
 */
@Component
public class Demo implements A {

    // java类默认包含无参构造

    public void test() {
        System.out.println("hello world");
    }
}
