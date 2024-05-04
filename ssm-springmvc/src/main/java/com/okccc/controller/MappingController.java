package com.okccc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: okccc
 * @Date: 2023/11/3 15:00:16
 * @Desc: HandlerMapping查找Handler
 *
 * 1.value属性：通过请求路径匹配(常用)
 * value属性是String[],请求路径匹配数组中任意一个值,该请求就会被当前控制器方法执行
 * 如果请求路径不匹配会报错 HTTP 404 - NOT FOUND
 * 请求路径支持通配符"?"  "*"  "**" -> "/user/?"  "/user/*"  "/user/**"
 * "?"表示任意单个字符(不包括"?"和"/",因为地址栏里的"?"是请求路径和请求参数的分隔符,"/"是请求路径之间的分隔符)
 * "*"表示单层任意字符串,"**"表示任意层数的任意字符串
 *
 * 2.method属性：通过请求方式匹配(常用)
 * method属性是RequestMethod[],请求方式匹配数组中任意一个值,该请求就会被当前控制器方法执行
 * 如果请求方式不匹配会报错 HTTP 405 - Method 'XXX' is not supported.
 * 不同请求方式的进阶注解：@GetMapping、@PostMapping、@PutMapping、@DeleteMapping
 * GET请求 @RequestMapping(value="/login", method=RequestMethod.GET) -> @GetMapping(value="/login")
 *
 * 3.params属性：通过请求参数匹配,有四种表达式(了解)
 * "username"：当前匹配请求的请求参数必须携带username
 * "!username"：当前匹配请求的请求参数不能携带username
 * "key=value"：当前匹配请求的请求参数必须携带key且key=value
 * "key!=value"：当前匹配请求的请求参数可以不携带key,如果携带key那么key!=value
 * 如果请求参数不匹配会报错 HTTP 400 - Invalid request parameters.
 *
 * 4.headers属性：通过请求头信息匹配,有四种表达式(了解)
 * "username"：当前匹配请求的请求头必须携带username
 * "!username"：当前匹配请求的请求头不能携带username
 * "key=value"：当前匹配请求的请求头必须携带key且key=value
 * "key!=value"：当前匹配请求的请求头可以不携带key,如果携带key那么key!=value
 * 如果请求头不匹配会报错 HTTP 404,比如headers={"referer"}那必须是跳转过来的页面,首次访问就会404
 */
@RestController
@RequestMapping(value = "/user")
public class MappingController {

    // http://localhost:8088/springmvc/user/
    @RequestMapping(value = {"/", "/index", "/aaa"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }

    // http://localhost:8088/springmvc/user/login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "success";
    }

    // http://localhost:8088/springmvc/user/sos/ant
    @RequestMapping(value = "/s?s/ant")
    public String testWildcard() {
        return "success";
    }

}
