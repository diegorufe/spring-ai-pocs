package com.springaipoc.agentsutils.multiagents.multishell.driven.api.config;

import com.springaipoc.agentsutils.multiagents.multishell.driven.api.advisorts.MyLoggingAdvisor;
import com.springaipoc.agentsutils.multiagents.multishell.driven.api.constants.ChatConstants;
import org.springaicommunity.agent.common.task.subagent.SubagentReference;
import org.springaicommunity.agent.common.task.subagent.SubagentType;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentDefinition;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentExecutor;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentResolver;
import org.springaicommunity.agent.tools.*;
import org.springaicommunity.agent.tools.task.TaskTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ChatConfig {

    @Bean
    ChatClient chatClient(
            ChatClient.Builder chatClientBuilder
    ) {
        return chatClientBuilder
                // Main agent prompt
                .defaultSystem(p -> p.text(ChatConstants.SYSTEM_MESSAGE_TEXT))

                // Task tool
                .defaultToolCallbacks(
                        TaskTool.builder()
                                // Remote A2A subagent
                                .subagentReferences(
                                        new SubagentReference("http://localhost:9001/geocoding", A2ASubagentDefinition.KIND)
//                                        new SubagentReference("http://localhost:9002/weather", A2ASubagentDefinition.KIND)
                                )
                                .subagentTypes(
                                        new SubagentType(new A2ASubagentResolver(), new A2ASubagentExecutor())
//                                        new SubagentType(new A2ASubagentResolver(), new A2ASubagentExecutor())
                                )
                                .build()
                )

                // Task orchestration
                .defaultTools(
                        TodoWriteTool.builder().build()
                )

                // Core Tools
                .defaultTools(
                        ShellTools.builder().build(),
                        FileSystemTools.builder().build(),
                        GrepTool.builder().build(),
                        GlobTool.builder().build(),
                        SmartWebFetchTool.builder(chatClientBuilder.clone().build()).build()
                )

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
