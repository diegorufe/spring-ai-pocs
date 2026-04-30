package com.springaipoc.rag.application.usecases;

import com.springaipoc.rag.application.ports.driven.DocumentInfoRepositoryPort;
import com.springaipoc.rag.application.ports.driving.EtlUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class EtlUseCase implements EtlUseCasePort {

    private final DocumentInfoRepositoryPort documentInfoRepositoryPort;


    @Override
    public Mono<Void> execute() {
        return Mono.fromRunnable(this.documentInfoRepositoryPort::etl)
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
