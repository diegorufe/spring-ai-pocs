package com.springaipoc.mcp.stores.application.ports.driving;

import com.springaipoc.mcp.stores.domain.Store;
import reactor.core.publisher.Flux;

public interface ListStoresUseCasePort {
    Flux<Store> execute();
}
