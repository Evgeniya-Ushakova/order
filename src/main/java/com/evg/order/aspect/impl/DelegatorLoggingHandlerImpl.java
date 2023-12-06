package com.evg.order.aspect.impl;

import com.evg.order.aspect.DelegatorLoggingHandler;
import com.evg.order.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

import static com.evg.order.enums.BpmnVars.REQUEST_ID;

@Service
@Slf4j(topic = "DELEGATOR_LOGGING_HANDLER")
@RequiredArgsConstructor
public class DelegatorLoggingHandlerImpl implements DelegatorLoggingHandler {

    private static final String UNKNOWN_PROCESS = "unknownProcess";

    @SneakyThrows
    @Override
    public Object monitoring(ProceedingJoinPoint joinPoint) {
        String[] classPath = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        String delegatorName = classPath[classPath.length - 1];
        DelegateExecution execution = (DelegateExecution) Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof DelegateExecution)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "DelegatorLoggingHandler: Delegate execution can't be null!"));
        String businessKey = execution.getBusinessKey();
        try {
            traceRequestId(execution);
            logStart(businessKey, delegatorName);
            return joinPoint.proceed();
        } catch (Exception e) {
            logError(businessKey, e, delegatorName);
            throw e;
        } finally {
            logFinish(businessKey, delegatorName);
            Utils.clearRequestId();

        }
    }

    private String getProcessDefinition(DelegateExecution execution) {
        try {
            return ((ExecutionEntity) execution).getProcessDefinition().getKey();
        } catch (Exception e) {
            LOGGER.error("Can not extract processDefinition from delegate execution", e);
            return UNKNOWN_PROCESS;
        }
    }

    private void logStart(String businessKey, String delegatorName) {
        LOGGER.info("Start {} with opportunityId: {}", delegatorName, businessKey);
    }

    private void logFinish(String businessKey, String delegatorName) {
        LOGGER.info("Finish {} with opportunityId: {}", delegatorName, businessKey);
    }

    private void logError(String businessKey, Throwable e, String delegatorName) {
        LOGGER.error(String.format("Delegator %s with opportunityId: %s had failed: %s",
                delegatorName,
                businessKey,
                e.getMessage()), e);
    }

    private void traceRequestId(DelegateExecution execution) {
        Optional.ofNullable(execution.getVariable(REQUEST_ID.getName()))
                .map(String::valueOf)
                .ifPresent(Utils::putMdcRequestId);
    }

}
