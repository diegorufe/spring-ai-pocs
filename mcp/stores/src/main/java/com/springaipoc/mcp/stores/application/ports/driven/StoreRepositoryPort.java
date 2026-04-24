package com.springaipoc.mcp.stores.application.ports.driven;

import com.springaipoc.mcp.stores.domain.Store;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StoreRepositoryPort {
    Mono<Store> findStoreByCode(String code);

    Flux<Store> listStores();
}
