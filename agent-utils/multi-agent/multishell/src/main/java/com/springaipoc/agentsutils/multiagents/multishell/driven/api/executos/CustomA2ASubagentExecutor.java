package com.springaipoc.agentsutils.multiagents.multishell.driven.api.executos;

import io.a2a.client.Client;
import io.a2a.client.ClientEvent;
import io.a2a.client.TaskEvent;
import io.a2a.client.config.ClientConfig;
import io.a2a.client.transport.jsonrpc.JSONRPCTransport;
import io.a2a.client.transport.jsonrpc.JSONRPCTransportConfig;
import io.a2a.spec.*;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springaicommunity.agent.common.task.subagent.SubagentDefinition;
import org.springaicommunity.agent.common.task.subagent.TaskCall;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentDefinition;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentExecutor;

import java.util.List;
import java.util.Map;
import java.util.UUID;
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
            Message message = (new Message.Builder()).role(Message.Role.USER)
                    .contextId(UUID.randomUUID().toString())
                    .messageId(UUID.randomUUID().toString())
                    .taskId(UUID.randomUUID().toString())
                    .parts(List.of(new TextPart(taskCall.prompt(), null))).build();
            CompletableFuture<String> responseFuture = new CompletableFuture<>();
            BiConsumer<ClientEvent, AgentCard> consumer = getClientEventAgentCardBiConsumer(responseFuture);
            ClientConfig clientConfig = (new ClientConfig.Builder()).setAcceptedOutputModes(List.of("text")).build();
            Client client = Client.builder(agentCard)
                    .clientConfig(clientConfig).withTransport(JSONRPCTransport.class, new JSONRPCTransportConfig()).addConsumers(List.of(consumer)).build();
            client.sendMessage(message);
            String result = responseFuture.get(5L, TimeUnit.MINUTES);
            log.info("Agent '{}' response: {}", subagent.getName(), result);
            return result;
        } catch (Exception e) {
            log.error("Error sending message to agent '{}': {}", subagent.getName(), e.getMessage());
            return String.format("Error communicating with agent '%s': %s", subagent.getName(), e.getMessage());
        }
    }

    private static @NonNull BiConsumer<ClientEvent, AgentCard> getClientEventAgentCardBiConsumer(CompletableFuture<String> responseFuture) {
        AtomicReference<String> responseText = new AtomicReference<>("");
        return (event, card) -> {
            if (event instanceof TaskEvent taskEvent) {
                Task completedTask = taskEvent.getTask();
                log.info("Received task response: status={}", completedTask.getStatus().state());
                if (completedTask.getArtifacts() != null) {
                    StringBuilder sb = getBuilder(completedTask);

                    responseText.set(sb.toString());
                }

                responseFuture.complete(responseText.get());
            }

        };
    }

    private static @NonNull StringBuilder getBuilder(Task completedTask) {
        StringBuilder sb = new StringBuilder();

        for (Artifact artifact : completedTask.getArtifacts()) {
            if (artifact.parts() != null) {
                for (Part<?> part : artifact.parts()) {
                    if (part instanceof TextPart textPart) {
                        sb.append(textPart.getText());
                    }
                }
            }
        }
        return sb;
    }
}
