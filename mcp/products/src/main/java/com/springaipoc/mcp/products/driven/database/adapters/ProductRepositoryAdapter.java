package com.springaipoc.mcp.products.driven.database.adapters;

import com.springaipoc.mcp.products.application.ports.driven.ProductRepositoryPort;
import com.springaipoc.mcp.products.domain.Product;
import com.springaipoc.mcp.products.driven.database.models.ProductModel;
import com.springaipoc.mcp.products.driven.database.repositories.ProductR2DBCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductR2DBCRepository productR2DBCRepository;

    @Override
    public Mono<Product> findProductByCode(String code) {
        return this.productR2DBCRepository.findById(code)
                .map(this::toDomain);
    }

    @Override
    public Flux<Product> listProducts() {
        return this.productR2DBCRepository.findAll()
                .map(this::toDomain);
    }

    private Product toDomain(ProductModel productModel) {
        return new Product(productModel.getCode(), productModel.getName());
    }
}
