package com.okccc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: okccc
 * @Date: 2023/10/7 17:51:56
 * @Desc: 动态生成任意代理对象的工厂类,运行时根据传入的对象生成该对象的代理对象
 *
 * jdk动态代理(默认)：目标类必须有接口,最终生成的代理类和目标类实现相同接口,为啥是接口不是类？因为jdk生成的代理类会默认继承Proxy类
 * cglib动态代理：目标类可以没有接口,最终生成的代理类会继承目标类
 *
 * 静态代理和动态代理都得自己实现代码,实际开发中不需要写这些复杂的代理,AOP框架可以简化动态代理实现
 */
public class ProxyFactory {

    // 目标对象,因为类型不确定所以用Object接收
    private final Object target;

    // 生产代理对象的工厂类
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // jdk动态代理基于反射机制实现,主要用到java.lang.reflect包下的Proxy类和InvocationHandler接口
    public Object getProxy() {
        // 获取代理类的类加载器,因为类要执行必须先通过类加载器加载
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 获取代理类实现的所有接口,因为代理类要和目标类实现同样的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 具体的代理动作
        InvocationHandler handler = new InvocationHandler() {
            @Override
            // proxy是代理对象,method是目标方法,args是目标方法的参数列表
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 代理对象实现附加功能
                System.out.println("[INFO] " + method.getName() + " 方法开始了,参数是：" + Arrays.toString(args));
                // 目标对象实现核心业务,因为目标类型不确定所以这里使用反射实现
                Object result = method.invoke(target, args);
                System.out.println("[INFO] " + method.getName() + " 方法结束了,结果是：" + result);
                return result;
            }
        };
        // 生成代理对象
        return Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
