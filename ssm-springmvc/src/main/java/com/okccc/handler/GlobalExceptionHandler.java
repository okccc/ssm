package com.okccc.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: okccc
 * @Date: 2023/11/17 15:22:03
 * @Desc: 全局异常处理器,统一定义异常处理Handler
 *
 * AOP应用场景：记录日志、事务处理、异常处理、权限控制、性能监控、缓存控制
 *
 * 事务处理两种方式：
 * 编程式事务：所有操作都是硬编码实现,繁琐且没法复用
 * 声明式事务：通过xml或注解控制事务的提交和回滚,将业务逻辑和事务控制分离,提高代码可读性和可维护性
 *
 * 异常处理两种方式：
 * 编程式异常：使用try-catch显式地捕获异常,与业务代码混在一起,可读性较差
 * 声明式异常：通过xml或注解统一处理异常,将业务逻辑和异常处理分离,提高代码可读性和可维护性
 */
@Slf4j
@RestControllerAdvice  // 点进去发现就等于 @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    // 所有异常都会触发,但是具体的异常处理优先级更高
    @ExceptionHandler(value = Exception.class)
    public Object error(Exception e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    // 空指针异常时触发
    @ExceptionHandler(value = NullPointerException.class)
    public Object error(NullPointerException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    // 算术异常时触发
    @ExceptionHandler(value = ArithmeticException.class)
    public Object error(ArithmeticException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

    // 类型转换异常时触发
    @ExceptionHandler(value = ClassCastException.class)
    public Object error(ClassCastException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }

}
