package com.okccc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: okccc
 * @Date: 2023/10/7 15:10:00
 * @Desc: Spring组件配置类(service, aop, tx),代替spring.xml
 */
@Configuration                                        // 使用注解标记为配置类
@ComponentScan(basePackages = {"com.okccc.service"})  // 使用注解扫描指定组件,代替<context:component-scan>标签
@EnableAspectJAutoProxy                               // 使用注解开启Aspectj,代替<aop:aspectj-autoproxy>标签
@EnableTransactionManagement                          // 使用注解开启事务管理,代替<tx:annotation-driven>标签
public class ServiceConfig {

    /**
     * 将TransactionManager加入IOC容器,需要依赖注入DataSource
     */
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
