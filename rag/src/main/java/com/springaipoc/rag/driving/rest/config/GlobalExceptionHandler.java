package com.springaipoc.rag.driving.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Exception>> handleGeneric(Exception ex) {
        log.error("CA error", ex);

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Mono<ResponseEntity<Void>> handleGeneric(NoResourceFoundException ex) {
        return Mono.just(ResponseEntity.noContent().build());
    }
}
