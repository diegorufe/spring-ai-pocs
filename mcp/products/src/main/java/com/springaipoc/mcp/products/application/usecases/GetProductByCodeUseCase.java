package com.springaipoc.mcp.products.application.usecases;

import com.springaipoc.mcp.products.application.ports.driven.ProductRepositoryPort;
import com.springaipoc.mcp.products.application.ports.driving.GetProductByCodeUseCasePort;
import com.springaipoc.mcp.products.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByCodeUseCase implements GetProductByCodeUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Mono<Product> execute(String code) {
        log.info("Enter find product by code {}", code);
        return this.productRepositoryPort.findProductByCode(code);
    }
}
