package com.evg.order.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import static com.evg.order.utils.Utils.getRequestBodyAsString;

@Slf4j(topic = "CAMUNDA_ADMIN")
public class CamundaProcessEngineFilter extends ProcessEngineAuthenticationFilter {

    private static final String NULL_USER = "null user";
    private static final String EXCAMAD_PING = "/engine-rest/engine";

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        try {
            super.doFilter(requestWrapper, response, chain);
        } finally {
            if (!Objects.equals(EXCAMAD_PING, requestWrapper.getRequestURI())) {
                String headerAuth = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
                String user = getUserName(headerAuth);
                String requestBody = Objects.equals(requestWrapper.getMethod(), "POST")
                        ? getRequestBodyAsString(requestWrapper)
                        : StringUtils.EMPTY;
                LOGGER.info(">> request engine URL {}, user {}. Request body: {}", requestWrapper.getRequestURL().toString(), user, requestBody);
            }
        }
    }

    private String getUserName(String headerAuth) {
        try {
            return new String(Base64.getDecoder().decode(headerAuth.split(" ")[1])).split(":")[0];
        } catch (Exception ex) {
            LOGGER.info("Cannot calculate camunda user name: {}", ex.getMessage());
            return NULL_USER;
        }
    }
}
