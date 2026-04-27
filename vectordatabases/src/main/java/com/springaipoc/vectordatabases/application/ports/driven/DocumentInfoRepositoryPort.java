package com.springaipoc.vectordatabases.application.ports.driven;

import com.springaipoc.vectordatabases.domain.DocumentInfo;
import com.springaipoc.vectordatabases.domain.filters.DocumentInfoFilter;

import java.util.List;

public interface DocumentInfoRepositoryPort {

    void save(List<DocumentInfo> documentInfos);

    void deleteAll();

    List<DocumentInfo> search(DocumentInfoFilter documentInfoFilter);
}
