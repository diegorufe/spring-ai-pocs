package com.springaipoc.chatbasics.application.ports.driving;

import com.springaipoc.chatbasics.domain.filters.ChatFilter;

public interface SyncChatUseCasePort {
    String execute(ChatFilter chatFilter);
}
