package com.springaipoc.chatmemory.domain.filters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatFilter {
    private String input;
}
