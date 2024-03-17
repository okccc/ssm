package com.okccc;

import com.okccc.pojo.A;
import com.okccc.pojo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: okccc
 * @Date: 2023/10/4 18:29:31
 * @Desc: 基于xml方式管理bean
 */
public class SpringTest01 {

    // 加载配置文件,创建IOC容器
    // java包和resources包最终都会加载到classes路径下,所以代码可以直接访问配置文件
    // 工程最终会打包到服务器运行,而服务器磁盘路径不一定有当前文件,所以选ClassPath而不是FileSystem
    private final ApplicationContext ioc = new ClassPathXmlApplicationContext("spring01.xml");

    @Test
    public void testIOC() {
        // 根据id获取bean
        Demo bean01 = (Demo) ioc.getBean("demo");
        System.out.println(bean01);

        // 根据类型获取bean(常用)
        // 要求IOC容器中指定类型的bean有且只有一个,因为默认是单例模式,IOC容器初始化时就会创建该对象
        // 在满足bean唯一性的前提下,其实是看(对象 instanceof 类型)返回的结果,bean类型、bean继承的父类、bean实现的接口都可以
        // 如果没有类型匹配的bean,抛异常：NoSuchBeanDefinitionException
        // 如果有多个类型匹配的bean,抛异常：NoUniqueBeanDefinitionException
        Demo bean02 = ioc.getBean(Demo.class);
        A a = ioc.getBean(A.class);
        System.out.println(bean02);
        System.out.println(a);
        System.out.println(bean02 instanceof A ? true : false);

        // 根据id和类型获取bean,当IOC容器中指定类型的bean不止一个时使用
        Demo bean03 = ioc.getBean("demo", Demo.class);
        System.out.println(bean03);
    }
}
