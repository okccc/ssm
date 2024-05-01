package com.okccc.config;

import jakarta.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author: okccc
 * @Date: 2023/11/1 15:42:28
 * @Desc: web项目初始化配置类,代替web.xml
 *
 * web项目启动时会自动调用WebApplicationInitializer接口的onStartup方法,可以进行一系列初始化操作
 * WebApplicationInitializer
 *     AbstractContextLoaderInitializer
 *         AbstractDispatcherServletInitializer
 *             AbstractAnnotationConfigDispatcherServletInitializer
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 源码分析：WebApplicationInitializer 169行 - AbstractContextLoaderInitializer 49/59/83行 -
     * AbstractAnnotationConfigDispatcherServletInitializer 55/89行 - 创建IOC容器,加载Spring组件配置类(Service、Mapper),SSM整合时使用
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * 源码分析：WebApplicationInitializer 169行 - AbstractDispatcherServletInitializer 65/83/127行 -
     * AbstractAnnotationConfigDispatcherServletInitializer 73/98行 - 创建IOC容器,加载SpringMVC组件配置类(HandlerMapping、HandlerAdapter、ViewResolver、Controller)
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfiguration.class};
    }

    /**
     * 源码分析：WebApplicationInitializer 169行 - AbstractDispatcherServletInitializer 65/97/157行 - 设置DispatcherServlet的url-pattern
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 配置过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        // Spring编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        // SpringMVC处理请求方式的过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}
