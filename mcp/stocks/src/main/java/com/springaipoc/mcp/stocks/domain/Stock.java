package com.springaipoc.mcp.stocks.domain;

import java.math.BigDecimal;

public record Stock(String storeCode, String productCode, BigDecimal quantity) {
}
