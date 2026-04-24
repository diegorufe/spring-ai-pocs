package com.springaipoc.mcp.products.driven.database.repositories;

import com.springaipoc.mcp.products.driven.database.models.ProductModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductR2DBCRepository extends ReactiveCrudRepository<ProductModel, String> {

    
}
