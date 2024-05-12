package com.okccc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2023/11/14 09:57:04
 * @Desc: 域对象共享数据
 *
 * 1.请求域：共享范围是本次请求及请求转发,使用场景：当前查询到的xx信息,包含Model、ModelMap、Map、ModelAndView四种方式
 * 2.会话域：共享范围是本次会话,可以跨多个请求,会话关闭就失效,使用场景：记录用户的登录状态和操作历史
 * 3.应用域：共享范围是本应用,可以跨多个会话,项目关闭或重启就失效,使用场景：Spring框架的IOC容器
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController {

    // 使用Model向请求域共享数据(常用)
    // http://localhost:8088/springmvc/share/model
    @RequestMapping(value = "/model")
    public String model(Model model) {
        // attributeName不能写成requestScope/sessionScope/applicationScope,这些都是注解关键字
        model.addAttribute("requestData", "hello Model");
        return "success";
    }

    // 使用ModelMap向请求域共享数据
    // http://localhost:8088/springmvc/share/modelMap
    @RequestMapping(value = "/modelMap")
    public String modelMap(ModelMap modelMap) {
        modelMap.addAttribute("requestData", "hello ModelMap");
        return "success";
    }

    // 使用Map向请求域共享数据
    // http://localhost:8088/springmvc/share/map
    @RequestMapping(value = "/map")
    public String map(Map<String, Object> map) {
        map.put("requestData", "hello Map");
        return "success";
    }

    // 使用ModelAndView向请求域共享数据
    // http://localhost:8088/springmvc/share/modelAndView
    @RequestMapping(value = "/modelAndView")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        // Model功能：向请求域共享数据
        modelAndView.addObject("requestData", "hello ModelAndView");
        // View功能：渲染视图实现页面跳转
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // 使用原生ServletAPI向请求域共享数据(过于原始)
    // http://localhost:8088/springmvc/share/api
    @RequestMapping(value = "/api")
    public String api(HttpServletRequest request) {
//        request.setAttribute("requestData", "hello ServletApi");
        request.setAttribute("requestData", "hello ServletApi");
        return "success";
    }

    // http://localhost:8088/springmvc/share/session
    @RequestMapping("/session")
    public String session(HttpSession session) {
        session.setAttribute("sessionData", "hello Session");
        return "success";
    }

    // http://localhost:8088/springmvc/share/application
    @RequestMapping("/application")
    public String application(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("applicationData", "hello Application");
        return "success";
    }

}
