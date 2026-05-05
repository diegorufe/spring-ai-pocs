package com.springaipoc.agent.application.ports.driving;

import com.springaipoc.agent.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
