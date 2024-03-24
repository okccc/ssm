package com.okccc;

import com.alibaba.druid.pool.DruidDataSource;
import com.okccc.config.SpringConfig;
import com.okccc.factory.UserFactoryBean;
import com.okccc.pojo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: okccc
 * @Date: 2023/10/4 11:21:10
 * @Desc: 基于配置类+注解方式管理bean
 */
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
}
