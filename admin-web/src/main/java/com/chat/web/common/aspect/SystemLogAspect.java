package com.chat.web.common.aspect;

import com.bove.commons.lang.RStringUtils;
import com.chat.web.common.annotations.SystemLogAnno;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author franky
 * @description 系统日志记录
 * @date 2019-05-11
 */
@Aspect
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Order(1)
@Component
@Slf4j
public class SystemLogAspect {

//    @Autowired
//    private SystemLogMapper systemLogMapper;

    @Before("@annotation(com.chat.web.common.annotations.SystemLogAnno)")
    public void annotation(JoinPoint joinPoint) {
        try {
            log.info("日志记录 start === {}", getControllerMethodDescription(joinPoint));
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String url = request.getRequestURI();
//            MarketUser marketUser = SessionHelper.getLoginUser(request);
//            String ip = getRequestIp(request);
//            if (ip.startsWith("0:0:0:0:0") || ip.startsWith("localhost")) {
//                ip = "127.0.0.1";
//            }
//            log.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "方法描述:" + getControllerMethodDescription(joinPoint) + ";请求人:" + marketUser.getAccount() + ";请求IP:" + ip);
//            SystemLog systemLog = new SystemLog();
//            systemLog.setCreationTime(new Date());
//            systemLog.setMarketCode(marketUser.getMarketCode());
//            systemLog.setModuleName(getControllerMethodDescription(joinPoint));
//            systemLog.setMenuUrl(url);
//            systemLog.setOperation(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
//            systemLog.setUserIp(ip);
//            systemLog.setUserCode(marketUser.getCode());
//            systemLog.setUserName(marketUser.getAccount());
//            systemLogMapper.insert(systemLog);
        } catch (Exception e) {
            log.error("error:记录日志失败,", e);
        }
    }

    public static String getRequestIp(HttpServletRequest request) {
        //请求的IP
        String ip = request.getHeader("X-Real-IP");
        if (RStringUtils.isEmpty(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description = method.getAnnotation(SystemLogAnno.class).description();
                    break;
                }
            }
        }


        return description;
    }
}
