package com.example.BacklogManagement_;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


public class BMEventListener {

	private static final String MY_QUEUE_NAME = "ppQueue";

	private BacklogManagementService backlogManagementService;

	public BMEventListener (BacklogManagementService backlogManagementService) {
		this.backlogManagementService = backlogManagementService;
	}
	
	@RabbitListener(queues = MY_QUEUE_NAME)
	 public void listen(String message) {
		 System.out.println("Geänderte UserStory ist angekommen: "+ message);
		 
		 try {
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode userStoryNode = mapper.readTree(message);
		        //Werte aus dem JSON-Objekt extrahieren
		        
		        UserStoryId userStoryId = new UserStoryId(userStoryNode.get("userStoryId").get("userStoryId").asInt());
		        String title = userStoryNode.get("title").asText();
		        String description = userStoryNode.get("description").asText();
		        int finalEstimation = userStoryNode.get("finalEstimation").asInt();
		        UserStory angekommeneUserStory= new UserStory(userStoryId, title, description, finalEstimation);

		        backlogManagementService.userStoryUpdaten(angekommeneUserStory);
		        
		    } catch (Exception e) {
		        System.out.println("Fehler bei der Deserialisierung der User Story");
		        e.printStackTrace();
		    }
	}
}