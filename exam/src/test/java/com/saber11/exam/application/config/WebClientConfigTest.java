package com.saber11.exam.application.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class WebClientConfigTest {

    @Test
    void webClientCreatesBean() {
        WebClientConfig config = new WebClientConfig();

        WebClient webClient = config.webClient();

        assertNotNull(webClient);
    }
}
