package com.evg.order.service;

import com.evg.order.enums.BpmnProcess;
import org.camunda.bpm.engine.impl.bpmn.helper.BpmnProperties;

import java.util.Map;
import java.util.Objects;

public interface CamundaService {

    void startProcess(BpmnProcess process, String businessKey, Map<String, Object> vars);

    void createMessageCorrelation(BpmnProcess process, String businessKey, String message, Map<String, Object> vars);

}
