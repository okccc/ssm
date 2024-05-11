package com.okccc.controller;

import com.okccc.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/11/16 15:40:02
 * @Desc: RestFul：Http协议的标准使用方案和风格,规定了如何设计请求路径、请求方式、参数传递
 *
 * 请求路径：url回归本质,统一资源定位符应该是名词,不能包含动词,通过请求方式来指定动作
 * 请求方式：没有请求体使用GET/DELETE(url资源唯一 ? 路径参数 : param参数),有请求体使用POST/PUT(json参数)
 * 参数传递：param参数、路径参数、json参数
 *
 * 操作类型    传统方式                  Rest风格(简洁优雅,以前crud要设计4个不同url,现在只要1个)
 * 查询操作    /user/getUser?id=1       GET /user/1
 * 保存操作    /user/saveUser           POST /user
 * 更新操作    /user/updateUser         PUT /user
 * 删除操作    /user/deleteUser?id=1    DELETE /user/1
 *
 * 3.HiddenHttpMethodFilter
 * 浏览器只支持GET和POST请求,SpringMVC提供了请求方法过滤器将POST请求转换为PUT或DELETE
 * 实现方式：先设置form表单请求方式method="post",再添加name="_method" value="put/delete"指定最终请求方式
 */
@RestController  // 点进去发现就等于 @Controller + @ResponseBody
@RequestMapping(value = "user")
public class RestfulController {

    // 根据id查询用户
    // http://localhost:8088/springmvc/user/1
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Integer id) {
        System.out.println("id = " + id);
        return null;
    }

    // 分页查询
    // http://localhost:8088/springmvc/user/page
    // http://localhost:8088/springmvc/user/page?page=1&size=10
    @GetMapping(value = "/page")
    public List<User> getUsers(@RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "5") int size) {
        System.out.println("page=" + page + ", size=" + size);
        return null;
    }

    // 添加用户
    // http://localhost:8088/springmvc/user
    @PostMapping()
    public void addUser(@RequestBody User user) {
        System.out.println("user = " + user);
    }

    // 更新用户
    // http://localhost:8088/springmvc/user
    @PutMapping()
    public void updateUser(@RequestBody User user) {
        System.out.println("user = " + user);
    }

    // 根据id删除用户
    // http://localhost:8088/springmvc/user/1
    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println("id = " + id);
    }

}
