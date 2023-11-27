package com.evg.order.service.impl;

import com.evg.order.enums.BpmnProcess;
import com.evg.order.service.CamundaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.Map;

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
}
