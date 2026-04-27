package com.springaipoc.vectordatabases.application.ports.driving;

import com.springaipoc.vectordatabases.domain.DocumentInfo;

import java.util.List;

public interface SaveDocumentInfoUseCasePort {
    void execute(List<DocumentInfo> documentInfos);
}
