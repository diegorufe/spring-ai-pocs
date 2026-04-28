package com.springaipoc.rag.driven.api.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.springaipoc.rag.driven.api.constants.ChatConstants.SYSTEM_MESSAGE_TEXT;

@Configuration
public class ChatConfig {

    @Bean
    ChatClient chatClient(
            ChatModel chatModel,
            ChatMemory chatMemory
    ) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_MESSAGE_TEXT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build()
                )
                .build();
    }
}
