package com.springaipoc.mcp.stocks.application.ports.driven;

import com.springaipoc.mcp.stocks.domain.Stock;
import reactor.core.publisher.Flux;

public interface StockRepositoryPort {
    Flux<Stock> findStocksByStoreCode(String storeCode);

    Flux<Stock> findStocksByProductCode(String productCode);
}
