package com.okccc.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: okccc
 * @Date: 2023/10/16 14:42:35
 * @Desc: AOP实现步骤：创建切面 - 定义通知 - 配置切入点
 *
 * 横切关注点：从核心业务代码中抽取出来的非核心业务代码,比如记录日志、事务处理、异常处理、权限控制、缓存控制
 * 切面：封装通知方法的类
 * 通知：实现横切关注点的方法
 * 连接点：抽取横切关注点的位置
 * 切入点：定位连接点的方式,通过切入点表达式指定当前通知要插入到哪些目标方法
 *
 * 通知方法：在切面中通过指定注解标识,包括@Before/@AfterReturning/@AfterThrowing/@After/@Around
 * 在通知方法的形参位置添加JoinPoint接口类型的参数可以获取目标方法的方法名和参数列表
 * 前置通知：在被代理的目标方法开始前执行
 * 返回通知：在被代理的目标方法成功结束后执行,returning属性获取目标方法的返回值
 * 异常通知：在被代理的目标方法异常结束后执行,throwing属性获取目标方法抛出的异常
 * 后置通知：在被代理的目标方法最终结束后执行
 * 环绕通知：使用try...catch...finally结构围绕整个被代理的目标方法,包括上面四种通知对应的所有位置
 *
 * 切入点表达式：标识通知方法的注解的value属性
 * value = "execution(public int com.okccc.proxy.CalculatorImpl.add(int, int))"
 * value = "execution(* com.okccc.proxy.CalculatorImpl.*(..))"
 * 第一个*表示任意的权限修饰符和返回值类型
 * 第二个*表示任意方法,get*是以get开头的方法,包名.类名也可以用*.*表示,*Service是以Service结尾的类或接口
 * ()表示没有参数,(..)表示任意参数列表,(String..)表示第一个参数是String类型,(..int)表示最后一个参数是int类型
 */
@Aspect     // 切面类必须添加@Aspect注解,Aspect是AOP思想的具体实现
@Component  // 切面类和目标类都要添加@Component注解,交给IOC容器管理
public class LogAspect {

    // 设置公共的切入点表达式方便重用,也可以单独放一个类集中管理
    @Pointcut(value = "execution(* com.okccc.proxy.*.*(..))")
    public void logPointCut(){}

//    @Before(value = "execution(public int com.okccc.proxy.CalculatorImpl.add(int, int))")
//    @Before(value = "execution(* com.okccc.proxy.CalculatorImpl.*(..))")
//    @Before(value = "logPointCut()")
    @Before(value = "com.okccc.advice.PointCut.logPointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取目标方法的签名信息,包括方法名/修饰符/所属类或接口
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName());
        System.out.println(signature.getModifiers());
        System.out.println(signature.getDeclaringType());
        // 获取目标方法的参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println("[AOP前置通知] " + signature.getName() + " 方法开始了,参数列表：" + Arrays.asList(args));
    }

    @AfterReturning(value = "logPointCut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[AOP返回通知] " + signature.getName() + " 方法成功结束了,返回值是：" + result);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "throwable")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[AOP异常通知] " + signature.getName() + " 方法抛异常了,异常类型是：" + throwable);
    }

    @After("logPointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[AOP后置通知] " + signature.getName() + " 方法执行完毕");
    }

//    @Around("com.okccc.advice.PointCut.transactionPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        // 环绕通知方法返回值要和目标方法返回值一致
        Object result;
        // 获取目标方法的方法名和参数列表
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        try {
            System.out.println("[AOP环绕通知 - 开启事务(模拟)] " + signature.getName() + " 方法开始了,参数列表：" + Arrays.asList(args));
            // JoinPoint只能获取目标方法信息,ProceedingJoinPoint还能执行目标方法
            result = joinPoint.proceed(args);
            System.out.println("[AOP环绕通知 - 提交事务(模拟)] " + signature.getName() + " 方法成功结束了,返回值是：" + result);
        } catch (Throwable throwable) {
            System.out.println("[AOP环绕通知 - 回滚事务(模拟)] " + signature.getName() + " 方法抛异常了,异常类型是：" + throwable);
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("[AOP环绕通知 - 最终结束] 关闭数据库连接");
        }
        return result;
    }
}
