package com.springaipoc.mcp.stocks.database.adapters;

import com.springaipoc.mcp.stocks.application.ports.driven.StockRepositoryPort;
import com.springaipoc.mcp.stocks.database.models.StockModel;
import com.springaipoc.mcp.stocks.database.repositories.StockR2DBCRepository;
import com.springaipoc.mcp.stocks.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class StockRepositoryAdapter implements StockRepositoryPort {

    private final StockR2DBCRepository stockR2DBCRepository;

    private Stock toDomain(StockModel stockModel) {
        return new Stock(stockModel.getId().getStoreCode(), stockModel.getId().getProductCode(), stockModel.getQuantity());
    }

    @Override
    public Flux<Stock> findStocksByStoreCode(String storeCode) {
        return this.stockR2DBCRepository.findByStoreCode(storeCode).map(this::toDomain);
    }

    @Override
    public Flux<Stock> findStocksByProductCode(String productCode) {
        return this.stockR2DBCRepository.findByProductCode(productCode).map(this::toDomain);
    }
}
