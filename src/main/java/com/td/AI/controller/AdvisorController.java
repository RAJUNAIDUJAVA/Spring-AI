package com.td.AI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvisorController {


    ChatClient chatClient;
    public AdvisorController(ChatClient.Builder builder, ChatMemory chatMemory){

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
       this.chatClient= builder.defaultAdvisors(new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("games","movies","action")),messageChatMemoryAdvisor).build();
    }

    @GetMapping("log/{message}")
    public String loggerAdvicerImplementation(@PathVariable String message){

        ChatOptions chatOptions = ChatOptions.builder().maxTokens(200).build();
        return chatClient.prompt(new Prompt(message,chatOptions)).call().content();
    }


}
