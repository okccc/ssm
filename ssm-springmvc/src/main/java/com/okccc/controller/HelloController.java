package com.okccc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: okccc
 * @Date: 2023/11/1 11:08:50
 * @Desc: DispatcherServlet统一处理所有请求,不同请求对应不同的控制器,包含若干控制器方法
 *
 * MVC是一种软件架构思想
 * Model(模型层)：1.数据承载Bean(User/Order) 2.业务处理Bean(Service/Dao)
 * View(视图层)：html、jsp页面,用户交互和数据展示
 * Controller(控制层)：Servlet,处理请求和响应
 * 三层架构：表述层(SpringMVC)、业务逻辑层(XxxService)、数据访问层(MyBatis)
 *
 * SpringMVC开发步骤
 * 1.创建maven工程导入依赖,右键JBLJavaToWeb快速转换成web工程
 * 2.创建web.xml,配置前端控制器DispatcherServlet
 * 3.创建springmvc.xml,扫描控制层组件
 * 4.创建控制器XxxController,包含若干控制器方法
 * 5.部署到tomcat服务器：Edit Configurations - Add New Configuration - Tomcat Server - Local - Name - Deployment & Server
 *
 * SpringMVC核心组件
 * DispatcherServlet：框架提供,在web.xml配置生效,是整个流程的核心,前端控制器统一处理所有请求
 * HandlerMapping：框架提供,在springmvc.xml配置生效,内部缓存了很多Map<path, handler>,需加入IOC容器供DS查找路径对应的handler
 * HandlerAdapter：框架提供,在springmvc.xml配置生效,可以简化请求参数和响应数据,需加入IOC容器供DS调用
 * ViewResolver：框架提供,在springmvc.xml配置生效,需加入IOC容器供DS调用,前后端分离项目直接返回json不需要视图解析器
 * Handler：自定义,就是Controller类的方法,接收请求参数(param/path/json/共享域)、调用业务逻辑、响应前端数据(view/json)
 *
 * SpringMVC调用流程
 * 1.浏览器发送请求
 * 2.DS查找HandlerMapping,将请求路径和请求映射进行匹配,找到对应Handler执行,返回json或逻辑视图
 * 3.Thymeleaf视图解析器会渲染逻辑视图,加上前后缀将其拼接成物理视图,然后转发到WEB-INF目录下该视图对应的html页面
 * DispatcherServlet处理请求 - HandlerMapping匹配控制器方法 - (执行拦截器的preHandler方法) - HandlerAdapter执行控制器方法
 * - (执行拦截器的postHandler方法) - 处理ModelAndView渲染视图 - (执行拦截器的afterCompletion方法) - 将结果响应到浏览器
 */
@Controller  // 标识为控制层组件,交给IOC容器管理
@ResponseBody  // 前后端分离项目直接给前端返回数据,不走视图解析器
public class HelloController {

    // 将控制器方法注册到HandlerMapping,"/"表示当前工程的上下文路径 http://localhost:8088/springmvc/
    // http://localhost:8088/springmvc/hello
    @RequestMapping(value = "/hello")
    public String hello() {
        return "success";
    }
}
