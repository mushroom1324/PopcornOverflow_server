package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndPointExporter() {
        return new ServerEndpointExporter();
    }
}
