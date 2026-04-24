package com.springaipoc.mcp.products.driving.mcp;

import com.springaipoc.mcp.products.application.ports.driving.GetProductByCodeUseCasePort;
import com.springaipoc.mcp.products.application.ports.driving.ListProductsUseCasePort;
import com.springaipoc.mcp.products.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMCPController {

    private final GetProductByCodeUseCasePort getProductByCodeUseCasePort;

    private final ListProductsUseCasePort listProductsUseCasePort;

    @McpTool(name = "getProductByCode", description = "Find product by code")
    public Mono<Product> getProductByCode(
            @McpToolParam(description = "Product code") String productCode
    ) {
        return this.getProductByCodeUseCasePort.execute(productCode);
    }

    @McpTool(name = "listProducts", description = "List all products")
    public Mono<List<Product>> listProducts(
    ) {
        return this.listProductsUseCasePort.execute().collectList();
    }


}
