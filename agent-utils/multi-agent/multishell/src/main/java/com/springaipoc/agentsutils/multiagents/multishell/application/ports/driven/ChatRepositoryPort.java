package com.springaipoc.agentsutils.multiagents.multishell.application.ports.driven;

import com.springaipoc.agentsutils.multiagents.multishell.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {


    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
