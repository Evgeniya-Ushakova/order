package com.evg.order.aspect.impl;

import com.evg.order.aspect.AspectService;
import com.evg.order.aspect.DelegatorLoggingHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j(topic = "ASPECT")
@RequiredArgsConstructor
public class AspectServiceImpl implements AspectService {

    private final DelegatorLoggingHandler delegatorLoggingHandler;

    @SneakyThrows
    @Override
    @Around("execution(* org.camunda.bpm.engine.delegate.JavaDelegate.*(..))")
    public Object monitoringDelegator(ProceedingJoinPoint joinPoint) {
        return delegatorLoggingHandler.monitoring(joinPoint);
    }

}
