package com.springaipoc.agentsutils.multiagents.weather.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
        You are a helpful assistant for know weather.
        IMPORTANT: Always use skills only
        
        Skills:
         - When user ask for weather, temperature, forecast, climate conditions → call Skill(command="weather")
        """;

}
