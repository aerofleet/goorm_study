package com.goorm.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class LogExeTimeAspect {
    @Around("execution(* com.goorm.backend.service..*(..))")
    public Object lgoExeTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("{} 실행 시간: {}ms", joinPoint.getSignature().toShortString(), executionTime);

        return proceed;
    }
}
