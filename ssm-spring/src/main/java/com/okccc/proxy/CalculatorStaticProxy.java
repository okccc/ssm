package com.okccc.proxy;

/**
 * @Author: okccc
 * @Date: 2023/10/7 17:48:36
 * @Desc: 静态代理类
 *
 * 代码缺陷：代理对象和目标对象绑死了,如果其它目标类想附加日志还得声明更多静态代理类
 * 解决思路：如果有一个专门生产代理对象的工厂类就好了,可以动态生成任意目标类的代理类
 */
public class CalculatorStaticProxy implements Calculator{

    // 目标对象
    private final Calculator target;

    // 代理对象
    public CalculatorStaticProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int a, int b) {
        // 代理对象实现附加功能
        System.out.println("[INFO] add 方法开始了,参数是: " + a + "," + b);
        // 目标对象实现核心业务
        int result = target.add(a, b);
        System.out.println("[INFO] add 方法结束了,结果是: " + result);
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
