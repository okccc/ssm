package com.okccc.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: okccc
 * @Date: 2023/10/7 17:53:08
 * @Desc: 校验切面优先级,value属性值越小切面优先级越高,前置更先执行后置更后执行
 *
 * 出现多个切面嵌套时要慎重,比如事务切面优先级高,那么在缓存中命中数据的情况下事务切面的操作都浪费了
 * 此时应该将缓存切面的优先级提高,在事务操作之前先检查缓存中是否存在目标数据
 * http://heavy_code_industry.gitee.io/code_heavy_industry/assets/img/img012.b353bc56.png
 * http://heavy_code_industry.gitee.io/code_heavy_industry/assets/img/img013.53c41dc7.png
 * http://heavy_code_industry.gitee.io/code_heavy_industry/assets/img/img014.ee4ed40a.png
 */
@Order(value = 1)
@Aspect
@Component
public class AspectOrder {

    @Before(value = "execution(* com.okccc.proxy.*.*(..))")
    public void beforeAdvice() {
        System.out.println("我优先级高先执行");
    }
}
