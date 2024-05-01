package com.okccc.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;

/**
 * @Author: okccc
 * @Date: 2023/11/9 18:04:02
 * @Desc: 校验web项目启动时的操作
 */
public class Main implements WebApplicationInitializer {

    /**
     * Configure the given {@link ServletContext} with any servlets, filters, listeners
     * context-params and attributes necessary for initializing this web application. See
     * examples {@linkplain WebApplicationInitializer above}.
     *
     * @param servletContext the {@code ServletContext} to initialize
     * @throws ServletException if any call against the given {@code ServletContext}
     *                          throws a {@code ServletException}
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // web项目启动时会自动调用该方法,进行一系列初始化操作
        System.out.println("Main.onStartup");
    }
}
