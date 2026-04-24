package com.springaipoc.chatmemory.application.ports.driving;

import com.springaipoc.chatmemory.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
