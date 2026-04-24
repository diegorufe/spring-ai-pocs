package com.springaipoc.mcp.stocks.application.usecases;

import com.springaipoc.mcp.stocks.application.ports.driven.StockRepositoryPort;
import com.springaipoc.mcp.stocks.application.ports.driving.GetStocksByProductCodeUseCasePort;
import com.springaipoc.mcp.stocks.domain.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStocksByProductCodeUseCase implements GetStocksByProductCodeUseCasePort {

    private final StockRepositoryPort stockRepositoryPort;

    @Override
    public Flux<Stock> execute(String productCode) {
        log.info("Enter find stock by product code {}", productCode);
        return this.stockRepositoryPort.findStocksByProductCode(productCode);
    }
}
