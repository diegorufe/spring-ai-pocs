package com.springaipoc.rag.driven.database.adapters;


import com.springaipoc.rag.application.ports.driven.DocumentInfoRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DocumentInfoRepositoryAdapter implements DocumentInfoRepositoryPort {

    private static final int MAX_LENGTH_DOCUMENT = 2000;

    private static final int MAX_DOCUMENTS_SAVE = 5;

    private static final int MAX_ATTEMPTS = 3;

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


        AtomicInteger counter = new AtomicInteger();

        // En local con ollama se nos quedará sin memoria, por lo que particionamos a la hora de guardar
        Map<Integer, List<Document>> grouped =
                documents.stream()
                        .collect(Collectors.groupingBy(i -> counter.getAndIncrement() / MAX_DOCUMENTS_SAVE));

        grouped.values().forEach(this::addWithRetry);
    }

    @SneakyThrows
    private void addWithRetry(List<Document> docs) {
        for (int attempt = 1; true; attempt++) {
            try {
                this.vectorStore.add(docs);
                return;
            } catch (Exception e) {
                log.warn("Intento {}/{} fallido: {}", attempt, MAX_ATTEMPTS, e.getMessage());
                if (attempt == MAX_ATTEMPTS) {
                    throw new RuntimeException("Fallo tras " + MAX_ATTEMPTS + " intentos", e);
                }
                Thread.sleep(1000L * attempt); // backoff: 1s, 2s, 3s
            }
        }
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.execute("""
                TRUNCATE vector_store
                """);
    }
}
