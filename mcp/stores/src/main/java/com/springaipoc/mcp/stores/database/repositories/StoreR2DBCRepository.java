package com.springaipoc.mcp.stores.database.repositories;

import com.springaipoc.mcp.stores.database.models.StoreModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StoreR2DBCRepository extends ReactiveCrudRepository<StoreModel, String> {


}
