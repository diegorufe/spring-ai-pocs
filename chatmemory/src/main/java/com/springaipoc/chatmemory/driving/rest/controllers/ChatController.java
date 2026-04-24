package com.springaipoc.chatmemory.driving.rest.controllers;

import com.springaipoc.chatmemory.application.ports.driving.ClearMemoryChatUseCasePort;
import com.springaipoc.chatmemory.application.ports.driving.StreamingChatUseCasePort;
import com.springaipoc.chatmemory.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final StreamingChatUseCasePort streamingChatUseCase;
    private final ClearMemoryChatUseCasePort clearMemoryChatUseCasePort;

    @Value("${spring.ai.ollama.chat.options.model}")
    private String model;

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(
            @RequestBody ChatFilter chatFilter
    ) {
        return this.streamingChatUseCase.execute(chatFilter);
    }

    @PostMapping(value = "/clear")
    public ResponseEntity<Void> clear() {
        this.clearMemoryChatUseCasePort.execute();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/model")
    public ResponseEntity<String> getModel() {
        return ResponseEntity.ok(this.model);
    }

}
