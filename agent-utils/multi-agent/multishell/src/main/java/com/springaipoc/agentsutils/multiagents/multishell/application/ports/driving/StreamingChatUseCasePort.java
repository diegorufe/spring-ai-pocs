package com.springaipoc.agentsutils.multiagents.multishell.application.ports.driving;

import com.springaipoc.agentsutils.multiagents.multishell.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
