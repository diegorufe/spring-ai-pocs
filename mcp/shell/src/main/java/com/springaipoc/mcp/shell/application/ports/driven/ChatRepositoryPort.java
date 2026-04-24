package com.springaipoc.mcp.shell.application.ports.driven;

import com.springaipoc.mcp.shell.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface ChatRepositoryPort {


    Flux<String> streamChat(ChatFilter chatFilter);

    void clearMemory();
}
