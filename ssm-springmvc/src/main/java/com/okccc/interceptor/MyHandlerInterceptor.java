package com.okccc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: okccc
 * @Date: 2023/11/17 17:56:07
 * @Desc: 拦截器：在请求到达具体handler之前统一执行,比如设置编码格式、登录保护、权限控制等
 *
 * 过滤器只能过滤springmvc外部流程,拦截器可以拦截springmvc内部流程,尽量使用拦截器不要用过滤器
 *
 * 拦截器原理：DispatcherServlet源码doDispatch()方法1041行
 *
 * 1.拦截器使用步骤
 * a.自定义类实现HandlerInterceptor接口
 * b.springmvc.xml配置自定义拦截器
 *
 * 2.拦截器的三个抽象方法
 * preHandle()：在控制器方法执行之前执行,返回值表示对控制器方法的拦截(false)或放行(true)
 * postHandle()：在控制器方法执行之后执行
 * afterCompletion()：在控制器方法执行之后,且渲染视图完毕之后执行
 *
 * 3.多个拦截器执行顺序和springmvc.xml中配置的顺序有关
 * 如果所有拦截器preHandle()都返回true：preHandle()按照配置顺序执行,postHandle()和afterCompletion()按照配置反序执行
 * 如果某个拦截器preHandle()返回false：preHandle()都执行,postHandle()都不执行,返回false之前的拦截器的afterCompletion()会执行
 */
public class MyHandlerInterceptor implements HandlerInterceptor {

    // 自定义类实现接口居然没有报错提示？说明该接口没有抽象方法或者抽象方法都有默认实现方式

    // handler之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor01.preHandle");
        return true;
    }

    // handler之后执行,如果handler报错就不执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor01.postHandle");
    }

    // 渲染视图之后一定会执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor01.afterCompletion");
    }
}
