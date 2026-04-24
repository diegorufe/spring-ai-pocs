package com.springaipoc.chatbasics.application.ports.driven;

import com.springaipoc.chatbasics.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {

    String syncChat(ChatFilter chatFilter);

    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
