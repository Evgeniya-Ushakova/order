package com.evg.order.service;

import com.evg.order.enums.BpmnProcess;
import org.camunda.bpm.engine.impl.bpmn.helper.BpmnProperties;

import java.util.Map;

public interface CamundaService {

    void startProcess(BpmnProcess process, String businessKey, Map<String, Object> vars);

}
