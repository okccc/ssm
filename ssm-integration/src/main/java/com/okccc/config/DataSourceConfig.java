package com.okccc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @Author: okccc
 * @Date: 2023/12/25 17:58:19
 * @Desc: datasource配置类
 */
@Configuration
@PropertySource("classpath:jdbc.properties")  // 使用注解读取外部文件,代替<context:property-placeholder>标签
public class DataSourceConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /**
     * 实例化DataSource对象添加到IOC容器,相当于<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
     */
    @Bean  // 使用注解标记方法代替<bean>标签,方法名就是id属性,返回值类型就是class属性
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}
