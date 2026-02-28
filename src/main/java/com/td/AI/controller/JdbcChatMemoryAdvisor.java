/*
package com.td.AI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jdbc")
public class JdbcChatMemoryAdvisor {

    ChatClient chatClient;

    public JdbcChatMemoryAdvisor (ChatClient.Builder builder,JdbcChatMemoryRepository repository){

        ChatMemory chatMemory= MessageWindowChatMemory.builder().chatMemoryRepository(repository).maxMessages(100).build();
        this.chatClient= builder.defaultAdvisors(PromptChatMemoryAdvisor.builder(chatMemory).build()).build();
    }
    @GetMapping("/call")
    public String getResponse(){
        String message = "explain spring security";
        return chatClient.prompt(message).call().content();
    }
}
*/
