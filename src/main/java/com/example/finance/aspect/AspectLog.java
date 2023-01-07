package com.example.finance.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectLog {

    @Around("@annotation(com.example.finance.annotation.ToLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method " + joinPoint.getSignature().getName() + "start");
        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " end");
        return proceed;
    }


}
