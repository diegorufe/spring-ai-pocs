package com.springaipoc.mcp.products.application.ports.driven;

import com.springaipoc.mcp.products.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRepositoryPort {
    Mono<Product> findProductByCode(String code);

    Flux<Product> listProducts();
}
