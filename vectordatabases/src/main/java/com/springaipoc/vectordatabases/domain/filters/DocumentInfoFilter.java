package com.springaipoc.vectordatabases.domain.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentInfoFilter {
    private String input;
    private int topK;
    private String filterExpression;
    @Builder.Default
    private double similarityThreshold = 0.1;
}
