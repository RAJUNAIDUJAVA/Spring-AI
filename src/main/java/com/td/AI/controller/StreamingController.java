package com.td.AI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stream")
public class StreamingController {


    ChatClient chatClient;

    public StreamingController (ChatClient.Builder builder){

        this.chatClient = builder.build();

    }
    @GetMapping("call")
    public Flux<String> getResponse(){
        String message = "explain about spring boot and what are the modules are available ";
        return chatClient.prompt(message).stream().content();

    }
}
