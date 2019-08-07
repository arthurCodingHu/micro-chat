package com.chat.web.common.aspect;

import com.chat.web.common.DistributedLimit;
import com.chat.web.common.annotations.DistributedLimitAnno;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author franky
 * @description 限流切面，判断是否达到峰值
 * @date 2019-04-18
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LimitAspect {

    @Autowired
    DistributedLimit distributedLimit;

    @Pointcut("@annotation(com.chat.web.common.annotations.DistributedLimitAnno)")
    public void limit() {
    }

    @Before("limit()")
    public void BeforeLimit(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLimitAnno distributedLimitAnno = method.getAnnotation(DistributedLimitAnno.class);
        String key =  distributedLimitAnno.limitKey();
        int limit = distributedLimitAnno.limit();
        boolean executeLimit = distributedLimit.distributedLock(key, String.valueOf(limit));
        if (!executeLimit) {
           throw new RuntimeException("exceeded limit");
        }
    }

}
