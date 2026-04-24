package com.springaipoc.chatbasics.application.ports.driving;

import com.springaipoc.chatbasics.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
