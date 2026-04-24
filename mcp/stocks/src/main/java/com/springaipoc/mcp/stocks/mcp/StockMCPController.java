package com.springaipoc.mcp.stocks.mcp;

import com.springaipoc.mcp.stocks.application.ports.driving.GetStocksByProductCodeUseCasePort;
import com.springaipoc.mcp.stocks.application.ports.driving.GetStocksByStoreCodeUseCasePort;
import com.springaipoc.mcp.stocks.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockMCPController {

    private final GetStocksByProductCodeUseCasePort getStocksByProductCodeUseCasePort;

    private final GetStocksByStoreCodeUseCasePort getStocksByStoreCodeUseCasePort;

    @McpTool(name = "getStocksByProductCode", description = "Get stocks by product code")
    public Mono<List<Stock>> getStocksByProductCode(@McpToolParam(description = "Product code") String productCode) {
        return this.getStocksByProductCodeUseCasePort.execute(productCode).collectList();
    }

    @McpTool(name = "getStocksByStoreCode", description = "Get stocks by store code")
    public Mono<List<Stock>> getStocksByStoreCode(@McpToolParam(description = "Store code") String storeCode) {
        return this.getStocksByStoreCodeUseCasePort.execute(storeCode).collectList();
    }

}
