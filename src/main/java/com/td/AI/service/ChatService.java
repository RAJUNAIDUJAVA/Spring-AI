package com.td.AI.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private VectorStore vectorStore;

    public void saveData(List<String> list){
        List<Document> documentList = list.stream().map(Document::new).toList();
        vectorStore.add(documentList);
    }
}
