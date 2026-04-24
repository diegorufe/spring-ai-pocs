package com.springaipoc.mcp.stocks.database.repositories;

import com.springaipoc.mcp.stocks.database.models.StockIdModel;
import com.springaipoc.mcp.stocks.database.models.StockModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StockR2DBCRepository extends ReactiveCrudRepository<StockModel, StockIdModel> {

    @Query("SELECT * FROM stocks WHERE store_code = :storeCode")
    Flux<StockModel> findByStoreCode(String storeCode);

    @Query("SELECT * FROM stocks WHERE product_code = :productCode")
    Flux<StockModel> findByProductCode(String productCode);
}
