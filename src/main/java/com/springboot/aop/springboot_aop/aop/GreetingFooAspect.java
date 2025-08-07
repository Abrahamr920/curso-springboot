package com.springboot.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect extends GreetingServicePointcuts {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Before("greetingLoggerPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("GreetingFooAspect\nAntes de: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }

    @After("greetingLoggerPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("GreetingFooAspect\nDespués de: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }
}
