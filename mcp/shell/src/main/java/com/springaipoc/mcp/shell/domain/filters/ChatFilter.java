package com.springaipoc.mcp.shell.domain.filters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatFilter {
    private String input;
}
