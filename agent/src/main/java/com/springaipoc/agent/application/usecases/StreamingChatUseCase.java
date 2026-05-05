package com.springaipoc.agent.application.usecases;

import com.springaipoc.agent.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.agent.application.ports.driving.StreamingChatUseCasePort;
import com.springaipoc.agent.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StreamingChatUseCase implements StreamingChatUseCasePort {

    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public Flux<String> execute(ChatFilter chatFilter) {
        return this.chatRepositoryPort.streamChat(chatFilter);
    }
}
