package com.okccc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Author: okccc
 * @Date: 2023/10/7 15:10:00
 * @Desc: 使用配置类代替spring.xml(主流)
 *
 * xml方式总结
 * 1.所有内容都写在xml配置文件,和java代码分离,解析效率低
 * 2.<bean>声明对象,<property>属性赋值,<context:property-placeholder>引入外部文件
 * 3.IOC容器具体实现选择ClassPathXmlApplicationContext对象
 *
 * xml+注解方式总结
 * 1.<bean>被IOC注解代替,<property>被DI注解代替
 * IOC注解：@Component @Controller @Service @Repository
 * DI注解：@Autowired @Qualifier @Resource @Value
 * 2.xml配置文件依然需要,<context:component-scan>扫描组件,<context:property-placeholder>引入外部文件
 * 3.IOC容器具体实现选择ClassPathXmlApplicationContext对象
 *
 * 配置类+注解方式总结
 * 1.彻底抛弃xml配置文件,使用配置类代替
 * 2.自定义类使用IOC和DI注解,第三方类使用@Bean注解
 * 3.IOC容器具体实现选择AnnotationConfigApplicationContext对象
 */
@Configuration                                // 使用注解标记为配置类
@ComponentScan(basePackages = {"com.okccc"})  // 使用注解扫描指定组件,代替<context:component-scan>标签
@PropertySource("classpath:jdbc.properties")  // 使用注解读取外部文件,代替<context:property-placeholder>标签
public class SpringConfig {

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

    /**
     * 实例化JdbcTemplate对象,需要注入IOC容器中的DataSource
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
