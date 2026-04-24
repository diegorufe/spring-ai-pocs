package com.springaipoc.mcp.stores.database.adapters;

import com.springaipoc.mcp.stores.application.ports.driven.StoreRepositoryPort;
import com.springaipoc.mcp.stores.database.models.StoreModel;
import com.springaipoc.mcp.stores.database.repositories.StoreR2DBCRepository;
import com.springaipoc.mcp.stores.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryAdapter implements StoreRepositoryPort {

    private final StoreR2DBCRepository storeR2DBCRepository;


    @Override
    public Mono<Store> findStoreByCode(String code) {
        return this.storeR2DBCRepository.findById(code)
                .map(this::toDomain);
    }

    @Override
    public Flux<Store> listStores() {
        return this.storeR2DBCRepository.findAll()
                .map(this::toDomain);
    }

    private Store toDomain(StoreModel storeModel) {
        return new Store(storeModel.getCode(), storeModel.getName());
    }

}
