package com.okccc.controller;

import com.okccc.common.Result;
import com.okccc.common.ResultCodeEnum;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/26 18:50:02
 * @Desc: Get请求可通过浏览器测试,Post/Put/Delete请求得使用Postman测试
 */
@CrossOrigin  // 解决前后端联调的跨域问题：允许其他源访问我们的Controller,浏览器就不拦截了
@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    // http://localhost:8088/ssm/user/5/2
    @GetMapping(value = "/{pageSize}/{currentPage}")
    public Result page(@PathVariable int pageSize, @PathVariable int currentPage) {
        Result result = userService.page(pageSize, currentPage);
        log.info("page query data is: {}", result);
        return result;
    }

    @PostMapping()
    public Result save(@Validated @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(ResultCodeEnum.FAIL);
        }
        return userService.save(user);
    }

    @PutMapping
    public Result update(@Validated @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                log.error(error.toString());
            }
            return Result.build(ResultCodeEnum.FAIL);
        }
        return userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public Result remove(@PathVariable Integer id) {
        return userService.remove(id);
    }

}
