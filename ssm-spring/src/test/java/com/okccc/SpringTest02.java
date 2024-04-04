package com.okccc;

import com.alibaba.druid.pool.DruidDataSource;
import com.okccc.config.SpringConfig;
import com.okccc.controller.UserController;
import com.okccc.factory.UserFactoryBean;
import com.okccc.pojo.Demo;
import com.okccc.proxy.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @Author: okccc
 * @Date: 2023/10/4 11:21:10
 * @Desc: 基于注解方式管理bean
 */
@SpringJUnitConfig(value = {SpringConfig.class})  // 整合JUnit,不需要手动创建IOC容器中的bean,直接注入即可
public class SpringTest02 {

    // 创建IOC容器,基于注解方式管理bean
    AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    public void testIOC() {
        System.out.println(ioc.getBean(Demo.class));
        System.out.println(ioc.getBean(UserFactoryBean.class));
        System.out.println(ioc.getBean(DruidDataSource.class));
        System.out.println(ioc.getBean(JdbcTemplate.class));
    }

    @Test
    public void testIocByAnnotation() {
        // 基于注解的自动装配
        UserController userController = ioc.getBean(UserController.class);
        userController.queryAll();
    }

    @Test
    public void testProxy() {
        // 目标类自己实现
        CalculatorImpl target = new CalculatorImpl();
        System.out.println(target.add(10, 20));

        // 使用静态代理实现
        CalculatorStaticProxy staticProxy = new CalculatorStaticProxy(target);
        System.out.println(staticProxy.add(10, 20));

        // 使用动态代理实现
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // jdk动态代理必须使用接口类型接收,因为目标类和代理类是兄弟关系类加载器不一样(ArrayList和LinkedList都实现了List接口)
        // java.lang.ClassCastException: class jdk.proxy2.$Proxy39 cannot be cast to class com.okccc.proxy.CalculatorImpl
//        CalculatorImpl dynamicProxy = (CalculatorImpl) proxyFactory.getProxy();
        Calculator dynamicProxy = (Calculator) proxyFactory.getProxy();
        System.out.println(dynamicProxy.add(10, 20));
    }

    @Test
    public void testAop() {
        // 1.目标类有接口使用jdk动态代理,接口类型接收,因为放入IOC容器的对象并不是目标类而是代理类(看不到但存在)
        // NoSuchBeanDefinitionException: No qualifying bean of type 'com.okccc.proxy.CalculatorImpl' available
//        CalculatorImpl calculator = ioc.getBean(CalculatorImpl.class);
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(10, 20);
        // 2.目标类没有接口使用cglib动态代理,类类型接收
        CalculatorDemo calculatorDemo = ioc.getBean(CalculatorDemo.class);
        calculatorDemo.add(10, 20);
        // 可以打断点Debug观察AOP的底层实现
    }

    @Test
    public void testTxByXml() {
        // 基于xml的声明式事务
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("spring02.xml");
        UserController userController = ioc.getBean(UserController.class);
        userController.buyBook(1, 1);
    }

    @Autowired
    private UserController userController;
    @Test
    public void testTxByAnnotation() {
        // 基于注解的声明式事务
//        userController.buyBook(1, 1);
        // 测试事务属性：传播行为
        userController.checkout(1, new Integer[]{1, 2});
    }
}
