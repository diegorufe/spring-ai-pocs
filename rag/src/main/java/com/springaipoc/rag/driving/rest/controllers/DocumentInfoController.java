package com.springaipoc.rag.driving.rest.controllers;

import com.springaipoc.rag.application.ports.driving.DeleteAllDocumentInfoUseCasePort;
import com.springaipoc.rag.application.ports.driving.EtlUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents-info")
@RequiredArgsConstructor
public class DocumentInfoController {

    private final EtlUseCasePort etlUseCasePort;

    private final DeleteAllDocumentInfoUseCasePort deleteAllDocumentInfoUseCasePort;

    @PostMapping(value = "/etl")
    public Mono<Void> etl() {
        return this.etlUseCasePort.execute();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAll() {
        this.deleteAllDocumentInfoUseCasePort.execute();
        return ResponseEntity.noContent().build();
    }

}
