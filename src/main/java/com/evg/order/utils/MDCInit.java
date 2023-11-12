package com.evg.order.utils;

import org.slf4j.MDC;

import static com.evg.otus.utils.Utils.MDC_REQUEST_ID;

public class MDCInit implements AutoCloseable{

    public MDCInit(String requestId) {
        MDC.put(MDC_REQUEST_ID, requestId);
    }


    @Override
    public void close() throws Exception {
    }
}
