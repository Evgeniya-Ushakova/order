package com.evg.order.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectService {

    Object monitoringDelegator(ProceedingJoinPoint joinPoint);

}
