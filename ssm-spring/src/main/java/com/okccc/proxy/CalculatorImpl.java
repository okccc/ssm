package com.okccc.proxy;

import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2023/10/7 17:45:43
 * @Desc: 普通实现类
 *
 * 代码缺陷：日志作为附加功能与核心业务叠在一起,并且存在大量重复代码
 * 解决思路：解耦,将附加功能的重复代码抽取出来,动态插入到每个业务方法中
 */
@Component
public class CalculatorImpl implements Calculator{

    @Override
    public int add(int a, int b) {
//        System.out.println("[INFO] add 方法开始了,参数是: " + a + "," + b);
        int result = a + b;
        System.out.println("add方法是核心业务逻辑");
//        System.out.println("[INFO] add 方法结束了,结果是: " + result);
        return result;
    }

    @Override
    public int sub(int a, int b) {
        return 0;
    }

    @Override
    public int mul(int a, int b) {
        return 0;
    }

    @Override
    public int div(int a, int b) {
        return 0;
    }
}
