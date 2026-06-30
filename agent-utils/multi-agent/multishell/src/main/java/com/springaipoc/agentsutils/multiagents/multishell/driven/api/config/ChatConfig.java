package com.springaipoc.agentsutils.multiagents.multishell.driven.api.config;

import com.springaipoc.agentsutils.multiagents.multishell.driven.api.advisorts.MyLoggingAdvisor;
import com.springaipoc.agentsutils.multiagents.multishell.driven.api.constants.ChatConstants;
import com.springaipoc.agentsutils.multiagents.multishell.driven.api.executos.CustomA2ASubagentExecutor;
import org.springaicommunity.agent.common.task.subagent.SubagentReference;
import org.springaicommunity.agent.common.task.subagent.SubagentType;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentDefinition;
import org.springaicommunity.agent.subagent.a2a.A2ASubagentResolver;
import org.springaicommunity.agent.tools.TodoWriteTool;
import org.springaicommunity.agent.tools.task.TaskTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
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
                .defaultTools(
                        // Task tool
                        TaskTool.builder()
                                // Remote A2A subagent
                                .subagentReferences(
                                        new SubagentReference("http://localhost:9001/geocoding", A2ASubagentDefinition.KIND),
                                        new SubagentReference("http://localhost:9002/weather", A2ASubagentDefinition.KIND)
                                )
                                .subagentTypes(
                                        new SubagentType(new A2ASubagentResolver(), new CustomA2ASubagentExecutor())
                                )
                                .build(),
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
