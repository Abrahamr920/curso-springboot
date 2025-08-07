package com.springboot.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect extends GreetingServicePointcuts {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("greetingLoggerPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("GreetingAspect\nAntes de: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }

    @After("greetingLoggerPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("GreetingAspect\nDespués de: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning("greetingLoggerPointcut()")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("\nDespués de retornar: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing("greetingLoggerPointcutError()")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("\nDespués de lanzar la excepción: " + methodName + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("greetingLoggerPointcut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        Object result = null;
        try {
            logger.info("\nAround Before return: " + methodName + Arrays.toString(joinPoint.getArgs()));
            result = joinPoint.proceed();
            logger.info("\nAround After return: " + result);
            return result;
        } catch (Throwable e) {
            logger.info("\nAround Exception return : " + methodName + Arrays.toString(joinPoint.getArgs()));
        }
        return result;
    }

}
