package com.td.AI.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rag")
@Slf4j
public class SimpleRAGController {

    @Value("classpath:/user-message.st")
    private Resource userMessage;

    @Value("classpath:/system-message.st")
    private Resource systemMessage;

    private ChatClient chatClient;
    private VectorStore vectorStore;

    public SimpleRAGController (ChatClient.Builder builder, ChatMemory chatMemory, VectorStore vectorStore){

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        this.chatClient = builder.defaultAdvisors(messageChatMemoryAdvisor,new SimpleLoggerAdvisor())
                .defaultSystem("You are helpful coding assistant. You are good in coding")
                .defaultOptions(ChatOptions.builder().temperature(0.3).maxTokens(400).build())
                .build();

        this.vectorStore= vectorStore;

    }

    @GetMapping("/get")
    public String chat(@RequestParam (value = "q", required = true) String query){

        // Load Data from Vector DB
        SearchRequest searchRequest = SearchRequest.builder()
                .topK(3)
                .similarityThreshold(0.6)
                .query(query)
                .build();

        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        // extract data from list of documents
        List<String> documentsList= documents.stream().map(Document::getText).toList();

        String contextData= String.join("," ,documentsList);
        System.out.println(contextData);

        log.info("Context data: {}",contextData);

        String str = chatClient.prompt()
                .system(system-> system.text(this.systemMessage).param("documents",contextData))
                .user(user -> user.text(this.userMessage).param("query",query))
                .call()
                .content();

        return str;

    }


}
