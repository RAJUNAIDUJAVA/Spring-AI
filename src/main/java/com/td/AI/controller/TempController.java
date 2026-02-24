package com.td.AI.controller;

import com.td.AI.model.Response;
import com.td.AI.service.TempService;
import com.td.AI.tools.SimpleTool;
import com.td.AI.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @Autowired
    OllamaChatModel chatModel;
    @Autowired
    ChatClient client;
    @Autowired
    TempService service;
    @Autowired
    WeatherTool weatherTool;

    @GetMapping("/get/{location}")
    public Response getTemp(@PathVariable String location){
        return service.getResponse(location);
    }


    @GetMapping ("get2/{message}")
    public String getoutput(@PathVariable String message){
    return client.prompt().tools(weatherTool,new SimpleTool()).user(message).call().content();
    }


}
