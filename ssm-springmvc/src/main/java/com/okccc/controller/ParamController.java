package com.okccc.controller;

import com.okccc.pojo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/11/7 16:26:30
 * @Desc: HandlerAdapter调用Handler
 *
 * Handler作用：1.接收请求参数(param/json/pathVariable/共享域) - 2.调用业务方法 - 3.响应前端数据(json/页面/转发和重定向)
 */
@RestController
@RequestMapping(value = "/param")
public class ParamController {

    // 1.param参数传递
    // @RequestParam 将前端请求参数和控制器方法的形参绑定,value属性可以省略
    // 如果不加注解默认也是param参数传递,只要前端请求参数和控制器方法的形参相同即可,不同或者不传就是null
    // http://localhost:8088/springmvc/param/data1?password=123
    @GetMapping(value = "/data1")
    public String data1(@RequestParam(value = "username", required = false, defaultValue = "admin") String username,
                        @RequestParam(value = "password") String password) {
        return "username=" + username + "&password=" + password;
    }

    // 特殊情况1：当前端页面使用多选框提交参数时,一个key可能会对应多个value,需要使用集合类型接收
    // 此时控制器方法的形参必须加@RequestParam注解,否则前端直接拼字符串会类型异常 java.lang.IllegalStateException
    // http://localhost:8088/springmvc/param/data2?hobbys=lol&hobbys=war3
    @GetMapping(value = "/data2")
    public String data2(@RequestParam(value = "hobbys") List<String> hobbys) {
        return "hobbys=" + hobbys;
    }

    // 特殊情况2：实体类参数,比如用户注册时要将User对象插入数据库表,前端请求参数和实体类的属性要保持一致,POST请求可以用Postman调试
    // http://localhost:8088/springmvc/param/data3
    @PostMapping(value = "/data3")
    public User data3(User user) {
        return user;
    }

    // 2.路径参数传递
    // @PathVariable 将url中的占位符{xxx}和控制器方法的形参绑定,value属性可以省略
    // http://localhost:8088/springmvc/param/data4/aaa/123
    @GetMapping(value = "/data4/{username}/{password}")
    public String data4(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
        return "username=" + username + "&password=" + password;
    }

    // 3.JSON参数传递
    // @RequestBody将前端传入的JSON反序列化成Java对象,@ResponseBody将Java对象序列化成JSON响应给前端
    // HTTP 415 - 不支持的媒体类型 Content-Type application;json;charset=UTF-8 is not supported.
    // 分析：json是前端的数据格式,java本身并不支持,所以要给HandlerAdapter配置json解析器
    // 解决：1.pom.xml导入jackson依赖  2.springmvc.xml开启mvc注解驱动
    // http://localhost:8088/springmvc/param/data5
    @PostMapping(value = "/data5")
    public User data5(@RequestBody User user) {
        return user;
    }

    // =========================我是分界线,下面了解即可=========================

    // 接收请求头和Cookie信息
    // @RequestHeader、@CookieValue分别将请求头、Cookie信息和控制器方法的形参绑定
    // http://localhost:8088/springmvc/param/data6
    @RequestMapping(value = "/data6")
    public String data6(
            @RequestParam(value = "username", required = false, defaultValue = "admin") String username,
            @RequestHeader(value = "Host") String host,
            @CookieValue(value = "JSESSIONID") String cookie) {
        return "username=" + username + ", Host=" + host + ", JSESSIONID=" + cookie;
    }

    // http://localhost:8088/springmvc/param/save
    @RequestMapping(value = "/save")
    public String save(HttpServletResponse response) {
        Cookie cookie = new Cookie("JSESSIONID", "415A4AC178C59DACE0B2C9CA727CDD84");
        response.addCookie(cookie);
        return "ok";
    }

    // 原生ServletApi对象(过于原始)
    // http://localhost:8088/springmvc/param/api?username=aaa
    @RequestMapping(value = "/api")
    public String api(HttpServletRequest request) {
        return "username=" + request.getParameter("username") +
                ", Host=" + request.getHeaders("Host") +
                ", Cookie=" + Arrays.toString(request.getCookies());
    }

}
