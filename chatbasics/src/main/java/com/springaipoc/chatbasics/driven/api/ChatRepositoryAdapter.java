package com.springaipoc.chatbasics.driven.api;

import com.springaipoc.chatbasics.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.chatbasics.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static com.springaipoc.chatbasics.driven.api.constants.ChatConstants.DEFAULT_CONVERSATION_ID;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements ChatRepositoryPort {


    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    @Override
    public String syncChat(ChatFilter chatFilter) {
        return this.generateMessage(chatFilter)
                .call()
                .content();
    }

    private ChatClient.ChatClientRequestSpec generateMessage(ChatFilter chatFilter) {
        return this.chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, DEFAULT_CONVERSATION_ID))
                .user(chatFilter.getInput());
    }

    @Override
    public Flux<String> streamChat(ChatFilter chatFilter) {
        return this.generateMessage(chatFilter)
                .stream()
                .chatResponse()
                .map(chatResponse -> Objects.requireNonNull(chatResponse.getResult()).getOutput().getText());
        // De esta forma acumulamos mensaje y lo enviamos cada vez de nuevo entero concatenado con lo anterior
//                .filter(s -> !s.isEmpty())
//                .scan(new StringBuilder(), StringBuilder::append)
//                .map(StringBuilder::toString);
    }

    @Override
    public void clearMemory() {
        this.chatMemory.clear(DEFAULT_CONVERSATION_ID);
    }
}
