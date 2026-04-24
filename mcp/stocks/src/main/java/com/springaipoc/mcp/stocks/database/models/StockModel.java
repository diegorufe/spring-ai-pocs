package com.springaipoc.mcp.stocks.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("stocks")
public class StockModel {

    @Id
    @Embedded.Nullable
    private StockIdModel id;
    private BigDecimal quantity;
}
