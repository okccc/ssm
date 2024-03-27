package com.okccc.advice;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author: okccc
 * @Date: 2023/10/16 18:20:59
 * @Desc: 统一管理所有切入点的类
 */
public class PointCut {

    @Pointcut(value = "execution(* com.okccc.proxy.*.*(..))")
    public void logPointCut() {}

    @Pointcut(value = "execution(* com.okccc.service.impl.*.*(..))")
    public void transactionPointCut() {}
}
