package com.springaipoc.agentsutils.skills.driven.api.adapters;

import com.springaipoc.agentsutils.skills.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.agentsutils.skills.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.springaipoc.agentsutils.skills.driven.api.constants.ChatConstants.DEFAULT_CONVERSATION_ID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatRepositoryAdapter implements ChatRepositoryPort {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    private ChatClient.ChatClientRequestSpec generateMessage(ChatFilter chatFilter) {
        return this.chatClient
                .prompt(chatFilter.getInput())
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, DEFAULT_CONVERSATION_ID));
    }

    @Override
    public Flux<String> streamChat(ChatFilter chatFilter) {
        return this.generateMessage(chatFilter)
                .stream()
                .content();
    }

    @Override
    public String syncChat(ChatFilter chatFilter) {
        return this.generateMessage(chatFilter)
                .call()
                .content();
    }

    @Override
    public void clearMemory() {
        this.chatMemory.clear(DEFAULT_CONVERSATION_ID);
    }
}
