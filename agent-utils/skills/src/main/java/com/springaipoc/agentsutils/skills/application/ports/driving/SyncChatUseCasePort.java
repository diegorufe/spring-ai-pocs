package com.springaipoc.agentsutils.skills.application.ports.driving;


import com.springaipoc.agentsutils.skills.domain.filters.ChatFilter;

public interface SyncChatUseCasePort {
    String execute(ChatFilter chatFilter);
}
