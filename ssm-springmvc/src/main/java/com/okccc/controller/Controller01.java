package com.okccc.controller;

import org.springframework.stereotype.Controller;
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
 */
@Controller  // 标识为控制层组件,交给IOC容器管理
@ResponseBody  // 前后端分离项目直接给前端返回数据,不走视图解析器
public class Controller01 {
}
