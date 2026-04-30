package com.springaipoc.rag.driven.api.adapters;

import com.springaipoc.rag.application.ports.driven.ChatRepositoryPort;
import com.springaipoc.rag.domain.ChatFilter;
import com.springaipoc.rag.driven.api.constants.ChatConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static com.springaipoc.rag.driven.api.constants.ChatConstants.DEFAULT_CONVERSATION_ID;


@Repository
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements ChatRepositoryPort {

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    private final MessageChatMemoryAdvisor messageChatMemoryAdvisor;

    private ChatClient.ChatClientRequestSpec generateMessage(ChatFilter chatFilter) {
        Advisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .similarityThreshold(chatFilter.getSimilarityThreshold())
                        .vectorStore(this.vectorStore)
                        .topK(chatFilter.getTopK())
                        .build())
                .queryAugmenter(ContextualQueryAugmenter.builder()
                        .allowEmptyContext(chatFilter.isAllowEmptyContext())
                        .build())
                .order(0)
                .build();

        return this.chatClient
                .prompt()
                .system(ChatConstants.SYSTEM_MESSAGE_TEXT)
                .advisors(advisorSpec -> advisorSpec
                        .advisors(retrievalAugmentationAdvisor)
                        .advisors(this.messageChatMemoryAdvisor)
                        .param(ChatMemory.CONVERSATION_ID, DEFAULT_CONVERSATION_ID))
                .user(chatFilter.getInput());
    }

    @Override
    public Flux<String> streamChat(ChatFilter chatFilter) {
        return this.generateMessage(chatFilter)
                .stream()
                .chatResponse()
                .map(chatResponse -> Objects.requireNonNull(chatResponse.getResult()).getOutput().getText());
    }

    @Override
    public void clearMemory() {
        this.chatMemory.clear(DEFAULT_CONVERSATION_ID);
    }
}
