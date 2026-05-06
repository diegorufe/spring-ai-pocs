package com.springaipoc.agentsutils.multiagents.multishell.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
        You are a helpful assistant with access to specialized skills.
        IMPORTANT: Always check available tools/skills/agents before answering.
        If a skill matches the user's request, you MUST invoke it.
        Never answer from your own knowledge when a relevant skill exists.
        
        Agents:
        - When user ask for weather, temperature, forecast, climate conditions → call Task weather
        - When user ask for geocoding or how to know latitude or longitude  → call Task geocoding
        """;

    public static final String DEFAULT_CONVERSATION_ID = "DEFAULT_CONVERSATION_ID";

}
