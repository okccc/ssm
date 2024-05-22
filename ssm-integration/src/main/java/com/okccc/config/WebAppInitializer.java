package com.okccc.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author: okccc
 * @Date: 2023/11/1 15:42:28
 * @Desc: IOC容器初始化配置类,代替web.xml,其中web容器是root容器的子容器,FrameworkServlet源码667行
 *
 * web项目启动时会自动调用WebApplicationInitializer接口的onStartup方法,进行IOC容器的初始化操作
 * WebApplicationInitializer
 *     AbstractContextLoaderInitializer
 *         AbstractDispatcherServletInitializer
 *             AbstractAnnotationConfigDispatcherServletInitializer
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 该方法不用重写,如果重写的话下面这行不能删除,会调用父类的registerDispatcherServlet()方法,如果不写访问页面会404
        super.onStartup(servletContext);
        System.out.println("========== AppInitializer.onStartup ==========");
    }

    /**
     * RootIoc容器配置类
     * 源码分析：WebApplicationInitializer 169行 - AbstractContextLoaderInitializer 49/59/83行 -
     * AbstractAnnotationConfigDispatcherServletInitializer 55/89行 - 创建IOC容器,加载Spring和Mybatis组件配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataSourceConfig.class, MapperConfig.class, ServiceConfig.class};
    }

    /**
     * WebIoc容器配置类
     * 源码分析：WebApplicationInitializer 169行 - AbstractDispatcherServletInitializer 65/83/127行 -
     * AbstractAnnotationConfigDispatcherServletInitializer 73/98行 - 创建IOC容器,加载SpringMVC组件配置类(HandlerMapping、HandlerAdapter、ViewResolver、Controller)
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfiguration.class};
    }

    /**
     * DispatcherServlet拦截路径
     * 源码分析：WebApplicationInitializer 169行 - AbstractDispatcherServletInitializer 65/97/157行 - 设置DispatcherServlet的url-pattern
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
