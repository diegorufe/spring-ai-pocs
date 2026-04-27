package com.springaipoc.vectordatabases.application.usecases;

import com.springaipoc.vectordatabases.application.ports.driven.DocumentInfoRepositoryPort;
import com.springaipoc.vectordatabases.application.ports.driving.DeleteAllDocumentInfoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAllDocumentInfoUseCase implements DeleteAllDocumentInfoUseCasePort {

    private final DocumentInfoRepositoryPort documentInfoRepositoryPort;

    @Transactional
    @Override
    public void execute() {
        this.documentInfoRepositoryPort.deleteAll();
    }
}
