package com.springaipoc.agentsutils.multiagents.multishell.driven.api.adapters;

import com.springaipoc.agentsutils.multiagents.multishell.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.agentsutils.multiagents.multishell.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static com.springaipoc.agentsutils.multiagents.multishell.driven.api.constants.ChatConstants.DEFAULT_CONVERSATION_ID;

@Repository
@RequiredArgsConstructor
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
                .chatResponse()
                .map(chatResponse -> Objects.requireNonNull(Objects.requireNonNull(chatResponse.getResult()).getOutput().getText()));
    }

    @Override
    public void clearMemory() {
        this.chatMemory.clear(DEFAULT_CONVERSATION_ID);
    }
}
