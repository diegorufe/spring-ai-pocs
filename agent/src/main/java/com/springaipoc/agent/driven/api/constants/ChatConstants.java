package com.springaipoc.agent.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
        You are a helpful assistant with access to specialized skills.
        IMPORTANT: Always check available tools/skills before answering.
        If a skill matches the user's request, you MUST invoke it.
        Never answer from your own knowledge when a relevant skill exists.
        
        Skills:
        - When user says "hello", "hi", "hey", "how are you" → call Skill(command="greetings")
        """;

    public static final String DEFAULT_CONVERSATION_ID = "DEFAULT_CONVERSATION_ID";

}
