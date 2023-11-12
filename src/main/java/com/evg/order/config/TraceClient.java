package com.evg.order.config;


import feign.Client;
import feign.Request;
import feign.Response;
import feign.okhttp.OkHttpClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j(topic = "FEIGN_INTERCEPTOR")
public class TraceClient extends Client.Default {

    private static final Pattern BEARER_PATTERN = Pattern.compile("Bearer \\S*");
    private static final String CRYPT = "*******************";

    private static final String[] HOSTS = {""};

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public TraceClient() {
        super(new CustomSSlSocketFactory(HOSTS), new CustomHostnameVerifier(HOSTS));
    }


    @Override
    public Response execute(Request request, Request.Options options) {
        LOGGER.info("Start trace request: {}", cryptData(request.toString()));
        try {
            Response response = executeResponse(request, options);
            String responseCode = getResponseCode(response);
            String responseBody = getResponseBody(response);
            LOGGER.info("Finish trace request to: {} Status: {} with response: {}", request.url(), responseCode, responseBody);
            return response.toBuilder().body(responseBody, StandardCharsets.UTF_8).build();
        } catch (Exception e) {
            LOGGER.error("Error at feign request!", e);
            throw e;
        }
    }

    private String getResponseCode(Response response) {
        return Objects.nonNull(response) ? String.valueOf(response.status()) : StringUtils.EMPTY;
    }

    @SneakyThrows
    private String getResponseBody(Response response) {
        return Objects.nonNull(response.body()) ? StreamUtils.copyToString(response.body().asInputStream(), StandardCharsets.UTF_8) : StringUtils.EMPTY;
    }

    private String cryptData(String text) {
        try {
            return text.replaceAll(BEARER_PATTERN.pattern(), CRYPT);
        } catch (Exception e) {
            LOGGER.warn("Error at cryptPassword. Message: {}", e.getMessage());
            return text;
        }
    }

    @SneakyThrows
    private Response executeResponse(Request request, Request.Options options) {
        if (request.httpMethod() == Request.HttpMethod.PATCH) {
            return okHttpClient.execute(request, options);
        }
        return super.execute(request, options);
    }

}
