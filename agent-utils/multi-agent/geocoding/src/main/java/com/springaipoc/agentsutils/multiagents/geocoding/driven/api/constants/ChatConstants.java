package com.springaipoc.agentsutils.multiagents.geocoding.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
        You are a helpful assistant for know geolocalization for places.
        IMPORTANT: Always use skills only
        
        Skills:
        - When user ask for geocoding or how to know latitude or longitude  → call Skill(command="geocoding")
        """;

}
