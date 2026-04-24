package com.springaipoc.mcp.products.application.ports.driving;

import com.springaipoc.mcp.products.domain.Product;
import reactor.core.publisher.Flux;

public interface ListProductsUseCasePort {
    Flux<Product> execute();
}
