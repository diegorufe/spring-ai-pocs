package com.springaipoc.mcp.stores.application.ports.driving;

import com.springaipoc.mcp.stores.domain.Store;
import reactor.core.publisher.Mono;

public interface GetStoreByCodeUseCasePort {
    Mono<Store> execute(String code);
}
