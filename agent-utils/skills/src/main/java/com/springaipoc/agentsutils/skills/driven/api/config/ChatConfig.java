package com.springaipoc.agentsutils.skills.driven.api.config;

import com.springaipoc.agentsutils.skills.driven.api.advisorts.MyLoggingAdvisor;
import com.springaipoc.agentsutils.skills.driven.api.constants.ChatConstants;
import org.springaicommunity.agent.tools.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
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
                .defaultToolCallbacks(SkillsTool.builder()
                        .addSkillsResources(this.skillPaths)
                        .toolDescriptionTemplate("""
                                MANDATORY: You MUST call this tool whenever a user request matches 
                                any of the available skills below. Do NOT answer from memory. 
                                Always invoke the matching skill first.
                                
                                Available skills (invoke by exact name):
                                %s
                                
                                Rule: If the user request relates to any skill above, 
                                call it immediately before responding.
                                """)
                        .build()
                )

                // Core Tools
                .defaultTools(
                        SmartWebFetchTool.builder(chatClientBuilder.clone().build()).build()
                )

                // Task orchestration
                .defaultTools(TodoWriteTool.builder().build())

                // Advisors
                .defaultAdvisors(
                        ToolCallAdvisor.builder().conversationHistoryEnabled(true).build(),
                        MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().maxMessages(500).build())
                                .order(Ordered.HIGHEST_PRECEDENCE + 1000)
                                .build()
                        // logging advisor
                        ,
                        MyLoggingAdvisor.builder()
                                .build())


                .build();
    }


}
