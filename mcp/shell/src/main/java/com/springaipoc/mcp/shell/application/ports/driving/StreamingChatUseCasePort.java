package com.springaipoc.mcp.shell.application.ports.driving;

import com.springaipoc.mcp.shell.domain.filters.ChatFilter;
import reactor.core.publisher.Flux;

public interface StreamingChatUseCasePort {
    Flux<String> execute(ChatFilter chatFilter);
}
