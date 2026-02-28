/*package com.td.AI.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemoryConfig {
    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository repository){

        return MessageWindowChatMemory.builder().chatMemoryRepository(repository).maxMessages(100).build();

    }

}*/
