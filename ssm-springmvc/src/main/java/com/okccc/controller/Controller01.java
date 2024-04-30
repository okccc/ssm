package com.okccc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: okccc
 * @Date: 2023/11/1 11:08:50
 * @Desc:
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
 */
@Controller  // 标识为控制层组件,交给IOC容器管理
@ResponseBody  // 前后端分离项目直接给前端返回数据,不走视图解析器
public class Controller01 {

    // 将控制器方法注册到HandlerMapping,"/"表示当前工程的上下文路径 http://localhost:8088/springmvc/
    // http://localhost:8088/springmvc/hello
    @RequestMapping(value = "/hello")
    public String hello() {
        return "success";
    }
}
