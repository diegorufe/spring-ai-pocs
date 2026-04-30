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

    @Builder.Default
    private Integer topK = 5;

    @Builder.Default
    private Double similarityThreshold = 0.5;

    @Builder.Default
    private boolean allowEmptyContext = false;
}
