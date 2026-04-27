package com.springaipoc.vectordatabases.driven.database.adapters;

import com.springaipoc.vectordatabases.application.ports.driven.DocumentInfoRepositoryPort;
import com.springaipoc.vectordatabases.domain.DocumentInfo;
import com.springaipoc.vectordatabases.domain.filters.DocumentInfoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.VectorStoreRetriever;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentInfoRepositoryAdapter implements DocumentInfoRepositoryPort {

    private final VectorStore vectorStore;

    private final VectorStoreRetriever vectorStoreRetriever;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void save(List<DocumentInfo> documentInfos) {
        this.vectorStore.add(documentInfos.stream().map(
                v -> new Document(v.getContent(), v.getMetadata())
        ).toList());
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.execute("""
                TRUNCATE vector_store
                """);
    }

    @Override
    public List<DocumentInfo> search(DocumentInfoFilter documentInfoFilter) {
        if (documentInfoFilter.getInput() == null) {
            return List.of();
        }

        return this.vectorStoreRetriever.similaritySearch(SearchRequest.builder()
                .query(documentInfoFilter.getInput())
                .topK(documentInfoFilter.getTopK())
                .filterExpression(documentInfoFilter.getFilterExpression())
                        .similarityThreshold(documentInfoFilter.getSimilarityThreshold())
                .build()).stream().map(
                v -> DocumentInfo.builder()
                        .id(v.getId())
                        .content(v.getFormattedContent())
                        .score(v.getScore())
                        .metadata(v.getMetadata())
                        .build()
        ).toList();
    }
}
