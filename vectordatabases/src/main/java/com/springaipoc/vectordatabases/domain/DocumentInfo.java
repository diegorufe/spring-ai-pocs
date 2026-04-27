package com.springaipoc.vectordatabases.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentInfo {
    private String id;
    private String content;
    private Double score;
    private Map<String, Object> metadata;
}
