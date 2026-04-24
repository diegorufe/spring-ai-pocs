package com.springaipoc.chatbasics.application.usecases;

import com.springaipoc.chatbasics.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.chatbasics.application.ports.driving.ClearMemoryChatUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearMemoryChatUseCase implements ClearMemoryChatUseCasePort {

    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public void execute() {
        this.chatRepositoryPort.clearMemory();
    }
}
