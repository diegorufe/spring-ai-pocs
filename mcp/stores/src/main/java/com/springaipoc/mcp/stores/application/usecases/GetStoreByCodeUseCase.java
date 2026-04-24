package com.springaipoc.mcp.stores.application.usecases;

import com.springaipoc.mcp.stores.application.ports.driven.StoreRepositoryPort;
import com.springaipoc.mcp.stores.application.ports.driving.GetStoreByCodeUseCasePort;
import com.springaipoc.mcp.stores.domain.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStoreByCodeUseCase implements GetStoreByCodeUseCasePort {

    private final StoreRepositoryPort storeRepositoryPort;

    @Override
    public Mono<Store> execute(String code) {
        log.info("Enter find store by code {}", code);
        return this.storeRepositoryPort.findStoreByCode(code);
    }
}
