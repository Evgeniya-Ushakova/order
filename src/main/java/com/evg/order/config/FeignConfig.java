package com.evg.order.config;

import feign.Client;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j(topic = "FEIGN_CONFIG")
public class FeignConfig {

    @Bean
    public Client client() {
        return new TraceClient();
    }

    @Bean
    public Logger logger() {
        return new FeignClientLogger();
    }

    private static class FeignClientLogger extends Logger {

        @Override
        protected void log(String configKey, String format, Object... args) {
            LOGGER.debug(String.format(methodTag(configKey) + format, args));
        }

    }

}
