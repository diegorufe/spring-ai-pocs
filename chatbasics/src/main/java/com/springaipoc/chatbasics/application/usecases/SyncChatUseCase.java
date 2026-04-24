package com.springaipoc.chatbasics.application.usecases;

import com.springaipoc.chatbasics.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.chatbasics.application.ports.driving.SyncChatUseCasePort;
import com.springaipoc.chatbasics.domain.filters.ChatFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncChatUseCase implements SyncChatUseCasePort {

    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public String execute(ChatFilter chatFilter) {
        return this.chatRepositoryPort.syncChat(chatFilter);
    }
}
