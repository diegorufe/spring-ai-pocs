package com.springaipoc.vectordatabases.application.usecases;

import com.springaipoc.vectordatabases.application.ports.driven.DocumentInfoRepositoryPort;
import com.springaipoc.vectordatabases.application.ports.driving.SaveDocumentInfoUseCasePort;
import com.springaipoc.vectordatabases.domain.DocumentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveDocumentInfoUseCase implements SaveDocumentInfoUseCasePort {

    private final DocumentInfoRepositoryPort documentInfoRepositoryPort;

    @Transactional
    @Override
    public void execute(List<DocumentInfo> documentInfos) {
        this.documentInfoRepositoryPort.save(documentInfos);
    }
}
