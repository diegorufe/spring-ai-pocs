package com.springaipoc.mcp.stocks.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockIdModel {
    private String storeCode;
    private String productCode;
}
