package com.springaipoc.vectordatabases.application.ports.driving;

import com.springaipoc.vectordatabases.domain.DocumentInfo;
import com.springaipoc.vectordatabases.domain.filters.DocumentInfoFilter;

import java.util.List;

public interface SearchDocumentInfoUseCasePort {
    List<DocumentInfo> execute(DocumentInfoFilter documentInfoFilter);
}
