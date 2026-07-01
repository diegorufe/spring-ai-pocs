package com.springaipoc.agentsutils.skills.application.usecases;

import com.springaipoc.agentsutils.skills.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.agentsutils.skills.application.ports.driving.SyncChatUseCasePort;
import com.springaipoc.agentsutils.skills.domain.filters.ChatFilter;
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
