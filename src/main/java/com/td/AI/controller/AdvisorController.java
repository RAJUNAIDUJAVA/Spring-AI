package com.td.AI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvisorController {


    ChatClient chatClient;
    public AdvisorController(ChatClient.Builder builder){

       ChatMemory chatMemory = MessageWindowChatMemory.builder()
               .chatMemoryRepository(new InMemoryChatMemoryRepository())
               .maxMessages(30)
               .build();
       this.chatClient= builder.defaultAdvisors(PromptChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    @GetMapping("log/{message}")
    public String loggerAdvicerImplementation(@PathVariable String message){

        ChatOptions chatOptions = ChatOptions.builder().maxTokens(200).build();
        return chatClient.prompt(new Prompt(message,chatOptions)).call().content();
    }

    @GetMapping("/{conversationId}")
    public String home(@PathVariable String conversationId, @RequestParam String message){
        return chatClient.prompt().advisors(advisor-> advisor.param("conversationId", conversationId))
                .user(message).call().content();
    }





}
