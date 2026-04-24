package com.springaipoc.mcp.products.application.usecases;

import com.springaipoc.mcp.products.application.ports.driven.ProductRepositoryPort;
import com.springaipoc.mcp.products.application.ports.driving.ListProductsUseCasePort;
import com.springaipoc.mcp.products.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListProductsUseCase implements ListProductsUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;


    @Override
    public Flux<Product> execute() {
        log.info("Enter list products");
        return this.productRepositoryPort.listProducts();
    }
}
