package com.springaipoc.agentsutils.skills.application.ports.driven;

import com.springaipoc.agentsutils.skills.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {

    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
