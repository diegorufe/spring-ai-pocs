package com.springaipoc.mcp.stores.application.usecases;

import com.springaipoc.mcp.stores.application.ports.driven.StoreRepositoryPort;
import com.springaipoc.mcp.stores.application.ports.driving.ListStoresUseCasePort;
import com.springaipoc.mcp.stores.domain.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListStoresUseCase implements ListStoresUseCasePort {

    private final StoreRepositoryPort storeRepositoryPort;


    @Override
    public Flux<Store> execute() {
        log.info("Enter list stores");
        return this.storeRepositoryPort.listStores();
    }
}
