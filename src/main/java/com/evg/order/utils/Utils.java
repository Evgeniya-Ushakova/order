package com.evg.order.utils;

import org.slf4j.MDC;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;

public class Utils {

    public static final String MDC_REQUEST_ID = "requestId";
    public static final String HEADER_REQUEST_ID = "X-B3-RequestId";

    public static String getMdcRequestId() {
        return MDC.get(MDC_REQUEST_ID);
    }

    public static String getRequestBodyAsString(ContentCachingRequestWrapper requestWrapper) {
        return new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
    }

}
