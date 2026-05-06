package com.springaipoc.agentsutils.skills.application.ports.driving;

import com.springaipoc.agentsutils.skills.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
