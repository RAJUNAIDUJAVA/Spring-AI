package com.td.AI;

import com.td.AI.service.ChatService;
import com.td.AI.utility.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiApplicationTests {
	@Autowired
	private ChatService service;

	@Test
	void contextLoads() {
	}


	@Test
	void saveDataToVectorDB(){
		System.out.println("Saving into vector DB");
		service.saveData(Helper.getData());
		System.out.println("Data saved successfully");
	}



}
