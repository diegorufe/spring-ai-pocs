package com.springaipoc.mcp.shell.driven.api.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

import static com.springaipoc.mcp.shell.driven.api.constants.ChatConstants.SYSTEM_MESSAGE_TEXT;

@Configuration
public class ChatConfig {


    @Bean
    ChatClient chatClient(
            ChatModel chatModel,
            ChatMemory chatMemory,
            ToolCallbackProvider toolCallbackProvider
    ) {
        // Para tools sse es necesario obtenerlo al principio, no es capaz de hacerlo de forma dinámica
        // Esto se debe que dentro de un FLUX o un Mono no se puede realizar block por que no esta permitido
        // Si fuera tipo SYNC no habria problema por que realiza una llamada HTTP y no realiza block para obtener el resultado.
        final ToolCallback[] callbacks = Mono
                .fromCallable(toolCallbackProvider::getToolCallbacks)
                .subscribeOn(Schedulers.boundedElastic())
                .block();

        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_MESSAGE_TEXT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build()
                )
                .defaultToolCallbacks(Objects.requireNonNull(callbacks))
                .build();
    }
}
