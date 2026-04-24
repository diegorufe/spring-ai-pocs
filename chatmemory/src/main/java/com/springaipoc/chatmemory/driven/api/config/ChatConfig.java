package com.springaipoc.chatmemory.driven.api.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.memory.repository.jdbc.PostgresChatMemoryRepositoryDialect;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.springaipoc.chatmemory.driven.api.constants.ChatConstants.SYSTEM_MESSAGE_TEXT;

@Configuration
public class ChatConfig {

    @Bean
    ChatClient chatClient(
            ChatModel chatModel,
            ChatMemory postgressChatMemory
    ) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_MESSAGE_TEXT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(postgressChatMemory)
                                .build()
                )
                .build();
    }

    @Bean
    ChatMemory postgressChatMemory(
            JdbcTemplate jdbcTemplate
    ) {
        ChatMemoryRepository chatMemoryRepository = JdbcChatMemoryRepository.builder()
                .jdbcTemplate(jdbcTemplate)
                .dialect(new PostgresChatMemoryRepositoryDialect())
                .build();

        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }
}
