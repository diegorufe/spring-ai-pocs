package com.springaipoc.agentsutils.skills.driven.api.config;

import com.springaipoc.agentsutils.skills.driven.api.advisorts.MyLoggingAdvisor;
import com.springaipoc.agentsutils.skills.driven.api.constants.ChatConstants;
import org.springaicommunity.agent.tools.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class ChatConfig {

    @Value("${agent.skills.paths}")
    private List<Resource> skillPaths;

    @Bean
    ChatClient chatClient(
            ChatClient.Builder chatClientBuilder
    ) {
        return chatClientBuilder
                // Main agent prompt
                .defaultSystem(p -> p.text(ChatConstants.SYSTEM_MESSAGE_TEXT))

                // Skills
                .defaultTools(SkillsTool.builder()
                        .addSkillsResources(this.skillPaths)
                        .toolDescriptionTemplate("""
                        You have access to a special tool named 'Skill'.
                        CRITICAL RULE: If the user asks for anything related to the available skills below, DO NOT try to answer with regular text. You MUST call the tool 'Skill' with the exact name of the skill in the 'command' parameter.
                        
                        Available skills that you MUST trigger automatically:
                        %s
                        """)
                        .build(),
                        // Core Tools
                        ShellTools.builder().build(),
                        FileSystemTools.builder().build(),
                        SmartWebFetchTool.builder(chatClientBuilder.clone().build()).build(),
                        // Task orchestration
                        TodoWriteTool.builder().build()
                )

                // Advisors
                .defaultAdvisors(
                        ToolCallingAdvisor.builder()
                                .conversationHistoryEnabled(true).build(),
                        MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().maxMessages(500).build())
                                .build()
                        // logging advisor
                        ,
                        MyLoggingAdvisor.builder()
                                .build())
                .build();
    }


}
