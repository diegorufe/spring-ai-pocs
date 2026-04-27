package com.springaipoc.vectordatabases.driving.rest.controllers;

import com.springaipoc.vectordatabases.application.ports.driving.DeleteAllDocumentInfoUseCasePort;
import com.springaipoc.vectordatabases.application.ports.driving.SaveDocumentInfoUseCasePort;
import com.springaipoc.vectordatabases.application.ports.driving.SearchDocumentInfoUseCasePort;
import com.springaipoc.vectordatabases.domain.DocumentInfo;
import com.springaipoc.vectordatabases.domain.filters.DocumentInfoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents-info")
@RequiredArgsConstructor
public class DocumentInfoController {

    private final DeleteAllDocumentInfoUseCasePort deleteAllDocumentInfoUseCasePort;

    private final SaveDocumentInfoUseCasePort saveDocumentInfoUseCasePort;

    private final SearchDocumentInfoUseCasePort searchDocumentInfoUseCasePort;

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll() {
        this.deleteAllDocumentInfoUseCasePort.execute();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody List<DocumentInfo> documentInfos) {
        this.saveDocumentInfoUseCasePort.execute(documentInfos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/search")
    public ResponseEntity<List<DocumentInfo>> search(@RequestBody DocumentInfoFilter documentInfoFilter) {
        final List<DocumentInfo> documentsInfo = this.searchDocumentInfoUseCasePort.execute(documentInfoFilter);
        return ResponseEntity.ok(documentsInfo);
    }
}
