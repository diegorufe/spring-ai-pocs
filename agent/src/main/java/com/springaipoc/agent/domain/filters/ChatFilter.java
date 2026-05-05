package com.springaipoc.agent.domain.filters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatFilter {
    private String input;
}
