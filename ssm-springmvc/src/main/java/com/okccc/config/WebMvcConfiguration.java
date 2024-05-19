package com.okccc.config;

import com.okccc.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;
import java.util.Properties;

/**
 * @Author: okccc
 * @Date: 2023/11/1 15:16:28
 * @Desc: SpringMVC组件配置类,代替springmvc.xml
 *
 * WebMvcConfigurer接口提供了配置SpringMVC各种组件的方法,直接模糊拼接方法名重写即可
 */
@Configuration                                           // 使用注解标记为配置类
@ComponentScan(basePackages = {"com.okccc.controller"})  // 使用注解扫描指定组件,代替<context:component-scan>标签
@EnableWebMvc                                            // 使用注解开启mvc驱动,代替<mvc:annotation-driven>标签
public class WebMvcConfiguration implements WebMvcConfigurer {

    // 使用@Bean注解标记方法代替<bean>标签,方法名就是id属性,返回值类型就是class属性

    // 配置HandlerMapping(框架自动加载,可以省略)
    @Bean
    public HandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    // 配置HandlerAdapter(框架自动加载,可以省略)
    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    // 配置默认Servlet处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 配置Thymeleaf视图解析器,并注入模板引擎
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(springTemplateEngine);
        return viewResolver;
    }

    // 配置模板引擎,并注入模板解析器
    @Bean
    public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver springResourceTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(springResourceTemplateResolver);
        return templateEngine;
    }

    // 配置模板解析器
    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    // 配置JSP视图解析器,SpringMVC允许配置多个ViewResolver,通过order指定优先级
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.order(2);
        registry.jsp("/WEB-INF/templates/",".jsp");
    }

    // 配置异常解析器
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        // 使用框架提供的SimpleMappingExceptionResolver,也可以自定义GlobalExceptionHandler
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.put("java.lang.Exception", "error");
        prop.put("java.lang.ArithmeticException", "error");
        prop.put("java.lang.NullPointerException", "error");
        exceptionResolver.setExceptionMappings(prop);
        exceptionResolver.setExceptionAttribute("exception");
        resolvers.add(exceptionResolver);
    }

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 方案1：默认拦截所有
//        registry.addInterceptor(new MyHandlerInterceptor());

        // 方案2：添加拦截路径,可以是单个路径也可以是通配符路径,/*表示单层路径,/**表示所有路径
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/user/*");

        // 方案3：排除拦截路径
//        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/user/*").excludePathPatterns("/user/sos/ant");
    }

}
