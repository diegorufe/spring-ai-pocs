package com.springaipoc.rag.application.ports.driven;

import com.springaipoc.rag.domain.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {

    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
