package com.springaipoc.mcp.stocks.application.ports.driving;

import com.springaipoc.mcp.stocks.domain.Stock;
import reactor.core.publisher.Flux;

public interface GetStocksByProductCodeUseCasePort {
    Flux<Stock> execute(String productCode);
}
