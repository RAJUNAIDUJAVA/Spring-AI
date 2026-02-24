package com.td.AI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TempConfig {
    @Bean
    public RestClient restClient(){
        return RestClient.builder().baseUrl("http://api.weatherapi.com/v1")
        .build();
    }
    /*@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }

    /*public OllamaChatOptions chatOptions(OllamaChatOptions.Builder builder){
        return builder.wit
    }*/


}
