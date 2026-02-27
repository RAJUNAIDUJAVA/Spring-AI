package com.td.AI.controller;

import com.td.AI.customAdvicer.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("custom")
public class CustomAdvicerController {

    ChatClient chatClient;


    public CustomAdvicerController (ChatClient.Builder builder){
        this.chatClient = builder.defaultAdvisors(new TokenPrintAdvisor())
                .defaultSystem("You are helpful coding assistant. You are good in coding")
                .defaultOptions(ChatOptions.builder().temperature(0.3).maxTokens(201).build())
                .build();
    }

    @GetMapping("call")
    public String getResult(){
        String s = "Explain springboot in telgu";
        return chatClient.prompt(s).call().chatResponse().getResult().getOutput().getText();
    }
}
