package com.springaipoc.rag.application.ports.driving;

import reactor.core.publisher.Mono;

public interface EtlUseCasePort {
    Mono<Void> execute();
}
