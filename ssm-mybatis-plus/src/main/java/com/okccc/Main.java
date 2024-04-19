package com.okccc;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: okccc
 * @Date: 2024/1/2 16:09:23
 * @Desc:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.okccc.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * 将mybatis-plus插件加入ioc容器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // mybatis-plus插件合集
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        return interceptor;
    }

}