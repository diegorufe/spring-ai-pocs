package com.springaipoc.mcp.products.application.ports.driving;

import com.springaipoc.mcp.products.domain.Product;
import reactor.core.publisher.Mono;

public interface GetProductByCodeUseCasePort {
    Mono<Product> execute(String code);
}
