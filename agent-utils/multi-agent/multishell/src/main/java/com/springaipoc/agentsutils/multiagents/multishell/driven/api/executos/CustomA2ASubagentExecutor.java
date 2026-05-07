package com.springaipoc.agentsutils.multiagents.multishell.driven.api.executos;

import io.a2a.client.Client;
import io.a2a.client.ClientEvent;
import io.a2a.client.TaskEvent;
import io.a2a.client.config.ClientConfig;
import io.a2a.client.transport.jsonrpc.JSONRPCTransport;
import io.a2a.client.transport.jsonrpc.JSONRPCTransportConfig;
import io.a2a.spec.*;
import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.agent.common.task.subagent.SubagentDefinition;
import org.springaicommunity.agent.common.task.subagent.TaskCall;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentDefinition;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

@Slf4j
public class CustomA2ASubagentExecutor extends A2ASubagentExecutor {

    @Override
    public String execute(TaskCall taskCall, SubagentDefinition subagent) {
        AgentCard agentCard = ((A2ASubagentDefinition) subagent).getAgentCard();

        try {
            Message message = (new Message.Builder()).role(Message.Role.USER).parts(List.of(new TextPart(taskCall.prompt(), (Map) null))).build();
            CompletableFuture<String> responseFuture = new CompletableFuture();
            AtomicReference<String> responseText = new AtomicReference("");
            BiConsumer<ClientEvent, AgentCard> consumer = (event, card) -> {
                if (event instanceof TaskEvent taskEvent) {
                    Task completedTask = taskEvent.getTask();
                    log.info("Received task response: status={}", completedTask.getStatus().state());
                    if (completedTask.getArtifacts() != null) {
                        StringBuilder sb = new StringBuilder();

                        for (Artifact artifact : completedTask.getArtifacts()) {
                            if (artifact.parts() != null) {
                                for (Part<?> part : artifact.parts()) {
                                    if (part instanceof TextPart) {
                                        TextPart textPart = (TextPart) part;
                                        sb.append(textPart.getText());
                                    }
                                }
                            }
                        }

                        responseText.set(sb.toString());
                    }

                    responseFuture.complete((String) responseText.get());
                }

            };
            ClientConfig clientConfig = (new ClientConfig.Builder()).setAcceptedOutputModes(List.of("text")).build();
            Client client = Client.builder(agentCard).clientConfig(clientConfig).withTransport(JSONRPCTransport.class, new JSONRPCTransportConfig()).addConsumers(List.of(consumer)).build();
            client.sendMessage(message);
            String result = (String) responseFuture.get(5L, TimeUnit.MINUTES);
            log.info("Agent '{}' response: {}", subagent.getName(), result);
            return result;
        } catch (Exception e) {
            log.error("Error sending message to agent '{}': {}", subagent.getName(), e.getMessage());
            return String.format("Error communicating with agent '%s': %s", subagent.getName(), e.getMessage());
        }
    }
}
