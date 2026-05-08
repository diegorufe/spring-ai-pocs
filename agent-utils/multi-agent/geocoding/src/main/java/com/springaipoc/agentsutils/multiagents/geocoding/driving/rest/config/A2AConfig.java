package com.springaipoc.agentsutils.multiagents.geocoding.driving.rest.config;

import io.a2a.spec.AgentCapabilities;
import io.a2a.spec.AgentCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class A2AConfig {

    @Bean
    public AgentCard agentCard(
            @Value("${server.port}") int port,
            @Value("${server.servlet.context-path}") String contextPath) {

        return new AgentCard.Builder()
                .name("geocoding")
                .description("""
                        You are a helpful assistant for know geolocalization for places.
                        """)
                .url("http://localhost:" + port + contextPath + "/")
                .version("1.0.0")
                .capabilities(new AgentCapabilities.Builder().streaming(false).build())
                .defaultInputModes(List.of("text"))
                .defaultOutputModes(List.of("text"))
                .skills(List.of())
                .protocolVersion("0.3.0")
                .build();
    }
}
