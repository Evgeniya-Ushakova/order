package com.evg.order.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface DelegatorLoggingHandler {
    Object monitoring(ProceedingJoinPoint joinPoint);

}
