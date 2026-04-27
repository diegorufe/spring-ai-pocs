package com.springaipoc.vectordatabases.application.usecases;

import com.springaipoc.vectordatabases.application.ports.driven.DocumentInfoRepositoryPort;
import com.springaipoc.vectordatabases.application.ports.driving.SearchDocumentInfoUseCasePort;
import com.springaipoc.vectordatabases.domain.DocumentInfo;
import com.springaipoc.vectordatabases.domain.filters.DocumentInfoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchDocumentInfoUseCase implements SearchDocumentInfoUseCasePort {

    private final DocumentInfoRepositoryPort documentInfoRepositoryPort;

    @Override
    public List<DocumentInfo> execute(DocumentInfoFilter documentInfoFilter) {
        return this.documentInfoRepositoryPort.search(documentInfoFilter);
    }
}
