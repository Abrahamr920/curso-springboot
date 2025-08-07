package com.springboot.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointcuts {

    @Pointcut("execution(* *..services.GreetingService.*(..))")
    protected void greetingLoggerPointcut() {
    }

    @Pointcut("execution(* *..services.GreetingService.sayHelloError(..))")
    protected void greetingLoggerPointcutError() {
    }
}
