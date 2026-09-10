package com.springaipoc.mcp.shell.driven.api;

import com.springaipoc.mcp.shell.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.mcp.shell.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static com.springaipoc.mcp.shell.driven.api.constants.ChatConstants.DEFAULT_CONVERSATION_ID;


@Repository
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements ChatRepositoryPort {


    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    private final ToolCallbackProvider toolCallbackProvider;

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
                .content();
    }

    @Override
    public void clearMemory() {
        this.chatMemory.clear(DEFAULT_CONVERSATION_ID);
    }
}
