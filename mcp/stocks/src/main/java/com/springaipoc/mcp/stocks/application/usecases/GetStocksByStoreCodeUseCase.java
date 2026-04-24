package com.springaipoc.mcp.stocks.application.usecases;

import com.springaipoc.mcp.stocks.application.ports.driven.StockRepositoryPort;
import com.springaipoc.mcp.stocks.application.ports.driving.GetStocksByStoreCodeUseCasePort;
import com.springaipoc.mcp.stocks.domain.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStocksByStoreCodeUseCase implements GetStocksByStoreCodeUseCasePort {

    private final StockRepositoryPort stockRepositoryPort;

    @Override
    public Flux<Stock> execute(String storeCode) {
        log.info("Enter find stock by store code {}", storeCode);
        return this.stockRepositoryPort.findStocksByStoreCode(storeCode);
    }
}
