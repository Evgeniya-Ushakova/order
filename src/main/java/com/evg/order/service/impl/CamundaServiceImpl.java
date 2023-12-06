package com.evg.order.service.impl;

import com.evg.order.enums.BpmnProcess;
import com.evg.order.enums.ErrorMessageCode;
import com.evg.order.exception.BadRequestException;
import com.evg.order.service.CamundaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CamundaServiceImpl implements CamundaService {

    private final RuntimeService runtimeService;


    @Override
    public void startProcess(BpmnProcess process, String businessKey, Map<String, Object> vars) {
        LOGGER.info("Start process = {} with businessKey = {} and vars = {}", process.getProcessId(), businessKey, vars);
        runtimeService.createProcessInstanceByKey(process.getProcessId())
                .businessKey(businessKey)
                .setVariables(vars)
                .execute();
    }

    @Override
    public void createMessageCorrelation(BpmnProcess process, String businessKey, String message, Map<String, Object> vars) {
        LOGGER.info("Start message correlation from process: {} with message: {} and vars: {}",
                process.getProcessId(),
                message,
                vars);
        String id = getActivityInstanceIdByBusinessKeyWithThrow(process, businessKey);
        correlateMessage(process, id, message, vars);
    }

    private String getActivityInstanceIdByBusinessKeyWithThrow(BpmnProcess process, String businessKey) {
        LOGGER.info("Get instanceId by businessKey: {} for process: {}", businessKey, process.getProcessId());
        List<ProcessInstance> ids = getActivityInstanceIdByBusinessKey(process, businessKey);
        if(ids.size() != 1) {
            throw new BadRequestException(ErrorMessageCode.UNKNOWN_EXCEPTION.getCode(),
                    String.format("More that or zero process had been launched for businessKey: %s", businessKey));
        }
        return ids.stream()
                .findFirst()
                .map(Execution::getId)
                .get();
    }

    private List<ProcessInstance> getActivityInstanceIdByBusinessKey(BpmnProcess process, String businessKey) {
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(process.getProcessId());

        List<ProcessInstance> processInstances = processInstanceQuery.unlimitedList();
        LOGGER.info("Active instance ids: {}, size: {}, by businessLKey: {}", processInstances, processInstances.size(), businessKey);
        return processInstances;
    }

    private void correlateMessage(BpmnProcess process,
                                  String executionId,
                                  String message,
                                  Map<String, Object> vars) {
        Optional.ofNullable(getEventSubscription(executionId, message))
                .ifPresent(eventSubscription -> {
                    try {
                        runtimeService.messageEventReceived(eventSubscription.getEventName(),
                                eventSubscription.getExecutionId(),
                                vars);
                    } catch (Exception e) {
                        LOGGER.error("Create message correlation failed for process: {} with message: {} and vars : {}",
                                process.getProcessId(),
                                message,
                                vars,
                                e);
                        throw new BadRequestException(ErrorMessageCode.UNKNOWN_EXCEPTION.getCode(), e.getMessage());
                    }
                });
    }

    private EventSubscription getEventSubscription(String executionId, String eventName) {
        return runtimeService.createEventSubscriptionQuery()
                .processInstanceId(executionId)
                .eventName(eventName)
                .singleResult();
    }
}
