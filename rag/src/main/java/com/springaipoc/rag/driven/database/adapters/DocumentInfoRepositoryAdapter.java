package com.springaipoc.rag.driven.database.adapters;


import com.springaipoc.rag.application.ports.driven.DocumentInfoRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class DocumentInfoRepositoryAdapter implements DocumentInfoRepositoryPort {

    private static final int MAX_LENGTH_DOCUMENT = 2000;

    private final VectorStore vectorStore;

    private final JdbcTemplate jdbcTemplate;

    private final TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
            .withChunkSize(1000)
            .withMinChunkSizeChars(400)
            .withMinChunkLengthToEmbed(10)
            .withMaxNumChunks(5000)
            .withKeepSeparator(true)
            .build();

    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();


    @SneakyThrows
    @Override
    public void etl() {
        Resource[] resources = this.resourcePatternResolver.getResources("classpath*:docs/*");
        List<Document> documents = Arrays.stream(resources)
                .flatMap(resource -> {
                    MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                            .withHorizontalRuleCreateDocument(true)
                            .withIncludeCodeBlock(true)
                            .withIncludeBlockquote(true)
                            .withAdditionalMetadata("filename", Objects.requireNonNull(resource.getFilename()))
                            .build();

                    List<Document> markdownDocuments = new MarkdownDocumentReader(resource, config).get();
                    // Documentos que no necesitan splitt
                    List<Document> finalDocuments = new ArrayList<>(markdownDocuments.stream().filter(document -> document.getText() != null && document.getText().length() <= MAX_LENGTH_DOCUMENT).toList());

                    // Documentos que necesitan splitt
                    finalDocuments.addAll(markdownDocuments.stream().filter(document -> document.getText() != null && document.getText().length() > MAX_LENGTH_DOCUMENT).flatMap(
                            document -> this.tokenTextSplitter.split(document).stream()
                    ).toList());

                    return finalDocuments.stream();
                })
                .toList();

        this.vectorStore.add(documents);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.execute("""
                TRUNCATE vector_store
                """);
    }
}
