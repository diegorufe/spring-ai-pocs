package com.springaipoc.mcp.stores.mcp;

import com.springaipoc.mcp.stores.application.ports.driving.GetStoreByCodeUseCasePort;
import com.springaipoc.mcp.stores.application.ports.driving.ListStoresUseCasePort;
import com.springaipoc.mcp.stores.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreMCPController {

    private final GetStoreByCodeUseCasePort getStoreByCodeUseCasePort;

    private final ListStoresUseCasePort listStoresUseCasePort;

    @McpTool(name = "getStoreByCode", description = "Find store by code")
    public Mono<Store> getStoreByCode(
            @McpToolParam(description = "Store code") String storeCode
    ) {
        return this.getStoreByCodeUseCasePort.execute(storeCode);
    }

    @McpTool(name = "listStores", description = "List all stores")
    public Mono<List<Store>> listStores(
    ) {
        return this.listStoresUseCasePort.execute()
                .collectList();
    }


}
