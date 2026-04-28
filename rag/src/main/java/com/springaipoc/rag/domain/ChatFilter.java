package com.springaipoc.rag.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatFilter {
    private String input;
    private Integer topK;
    private String filterExpression;
    private Double similarityThreshold;
    private boolean allowEmptyContext;
}
