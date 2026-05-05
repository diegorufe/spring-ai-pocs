package com.springaipoc.agent.application.ports.driven;

import com.springaipoc.agent.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {

    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
