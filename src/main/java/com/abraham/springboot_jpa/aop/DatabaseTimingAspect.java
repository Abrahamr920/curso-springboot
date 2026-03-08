package com.abraham.springboot_jpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatabaseTimingAspect {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseTimingAspect.class);

    @Value("${app.db.timing.enabled:true}")
    private boolean enabled;

    @Value("${app.db.timing.warn-threshold-ms:500}")
    private long warnThresholdMs;

    @Around("execution(* com.abraham.springboot_jpa.repositories..*(..))")
    public Object logRepositoryExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!enabled) {
            return joinPoint.proceed();
        }

        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedMs = (System.nanoTime() - start) / 1_000_000;
            String method = joinPoint.getSignature().toShortString();
            if (elapsedMs >= warnThresholdMs) {
                logger.warn("DB TIME {} ms - {}", elapsedMs, method);
            } else {
                logger.info("DB TIME {} ms - {}", elapsedMs, method);
            }
        }
    }
}
