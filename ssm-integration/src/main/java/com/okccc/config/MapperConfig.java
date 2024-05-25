package com.okccc.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author: okccc
 * @Date: 2023/12/25 17:18:05
 * @Desc: mybatis配置类(sqlSessionFactory, mapper, datasource),代替mybatis-config.xml
 *
 * mybatis核心api
 * InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
 * SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
 * SqlSession sqlSession = sqlSessionFactory.openSession();
 * UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
 *
 * SqlSessionFactoryBuilder在创建SqlSessionFactory后就不需要了,所以最佳作用域是方法域,无需IOC容器管理
 * SqlSessionFactory创建后应该在应用运行期间一直存在,为了避免重复创建多次,其最佳作用域是应用域,需要IOC容器管理
 * Mapper代理对象会被Service注入,需要IOC容器管理
 * 结论：需要将SqlSessionFactory和Mapper加入IOC容器,自己实现略复杂,可以使用mybatis-spring整合包提供的SqlSessionFactoryBean
 *
 * 整合mybatis出现的问题
 * 如果将dataSource和mybatis放一起,会出现@value注解不生效的问题
 * 原因：mybatis-spring第三方包的组件会优先加载,@Value注解要等spring扫描才生效,所以此时sqlSessionFactory还读不到datasource
 * 解决：分开配置,将datasource单独放一个配置类
 */
@Configuration  // 使用注解标记为配置类
@PropertySource("classpath:jdbc.properties")  // 使用注解读取外部文件,代替<context:property-placeholder>标签
public class MapperConfig {

    /**
     * 1.将SqlSessionFactory加入IOC容器
     * SqlSessionFactoryBean(ioc) -> getObject() -> SqlSessionFactory
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        // 创建SqlSessionFactoryBean对象
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // mybatis核心配置顺序：properties - settings - typeAliases - typeHandlers - plugins - environments - mappers
        // 方式1：读取配置文件
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        // 方式2：废弃配置文件,完全使用api设置(推荐)
        // 设置datasource
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 设置settings
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 日志实现,SLF4J/LOG4J2/STDOUT_LOGGING
        configuration.setLogImpl(Slf4jImpl.class);
        // 将下划线映射成驼峰规则
        configuration.setMapUnderscoreToCamelCase(true);
        // 设置映射范围,NONE/PARTIAL/FULL
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        // 开启延迟加载,需要啥就查啥而不是一次性全部查出来,可以对比DEBUG信息执行的sql条数
        configuration.setLazyLoadingEnabled(true);
        // 开启缓存,可以对比DEBUG信息执行的sql次数
        configuration.setCacheEnabled(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        // 设置typeAliases
        sqlSessionFactoryBean.setTypeAliasesPackage("com.okccc.pojo");

        // 设置plugins
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties prop = new Properties();
        prop.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(prop);
        sqlSessionFactoryBean.setPlugins(pageInterceptor);

        // 返回对象
        return sqlSessionFactoryBean;
    }

    /**
     * 2.将Mapper代理对象加入IOC容器
     * SqlSessionFactory -> openSession() -> SqlSession -> getMapper -> Mapper代理对象(ioc)
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        // 创建MapperScannerConfigurer对象
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        // 设置mapper接口和映射文件所在的包
        mapperScannerConfigurer.setBasePackage("com.okccc.mapper");

        // 返回对象
        return mapperScannerConfigurer;
    }

}
