package com.okccc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: okccc
 * @Date: 2023/11/1 15:16:28
 * @Desc: SpringMVC组件配置类(controller, web相关),代替springmvc.xml
 *
 * WebMvcConfigurer接口提供了配置SpringMVC各种组件的方法,直接模糊拼接方法名重写即可
 * Controller、HandlerMapping、HandlerAdapter、视图解析器、静态资源处理、json转化器、全局异常处理器、拦截器
 */
@Configuration                                           // 使用注解标记为配置类
@ComponentScan(basePackages = {"com.okccc.controller"})  // 使用注解扫描指定组件,代替<context:component-scan>标签
@EnableWebMvc                                            // 使用注解开启mvc驱动,代替<mvc:annotation-driven>标签
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 配置默认Servlet处理静态资源
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
