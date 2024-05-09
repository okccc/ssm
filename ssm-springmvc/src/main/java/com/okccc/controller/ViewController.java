package com.okccc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: okccc
 * @Date: 2023/11/15 14:36:00
 * @Desc: ViewResolver
 *
 * View的作用是渲染数据,将Model中的数据展示给用户
 * 转发视图和重定向视图都可以实现页面跳转,一般业务逻辑处理失败用转发,处理成功用重定向
 * 转发：地址栏url不变,是当前项目下的资源跳转
 * 重定向：地址栏url会变,可以是当前项目也可以是项目外的资源跳转(百度、知乎),属于二次请求
 *
 * 1.转发视图
 * SpringMVC默认转发视图是InternalResourceView
 * 当控制器方法中的视图名称以"forward:"为前缀时会创建InternalResourceView视图
 * 此时视图名称不会被Thymeleaf解析,而是将前缀"forward:"去掉,剩余部分作为最终路径通过转发的方式实现跳转
 * 因为无法渲染视图所以InternalResourceView已经废弃,现在转发视图都是用ThymeleafView
 *
 * 2.重定向视图
 * SpringMVC默认重定向视图是RedirectView
 * 当控制器方法中的视图名称以"redirect:"为前缀时会创建RedirectView视图
 * 此时视图名称不会被Thymeleaf解析,而是将前缀"redirect:"去掉,剩余部分作为最终路径通过重定向的方式实现跳转
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController {

    // 普通视图
    // http://localhost:8088/springmvc/view/common
    @RequestMapping(value = "/common")
    public String common() {
        return "success";
    }

    // 转发视图
    // http://localhost:8088/springmvc/view/forward
    @RequestMapping(value = "/forward")
    public String forward() {
        return "forward:/share/model";
    }

    // 重定向视图
    // http://localhost:8088/springmvc/view/redirect
    @RequestMapping(value = "/redirect")
    public String redirect() {
        return "redirect:/share/model";
    }

    // http://localhost:8088/springmvc/view/redirect/baidu
    @RequestMapping(value = "/redirect/baidu")
    public String redirectBaidu() {
        return "redirect:https://www.baidu.com";
    }

}
