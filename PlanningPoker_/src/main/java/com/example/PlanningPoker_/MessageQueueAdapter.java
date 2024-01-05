package com.example.PlanningPoker_;

import org.springframework.amqp.core.AmqpTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageQueueAdapter implements MessageQueue{
	

    public static final boolean NON_DURABLE = false;
    public static final String MY_QUEUE_NAME = "ppQueue";
    
    private final AmqpTemplate amqpTemplate;
    
    public MessageQueueAdapter (AmqpTemplate amqpTemplate) {
    	this.amqpTemplate = amqpTemplate;
    }
   
    public boolean senden(Object domainEvent) {
    	
    	String payload = "";
    	
    	if (domainEvent != null) {
    		
    		UserStory userStory = (UserStory)domainEvent;
    		
   		
    		ObjectMapper objectMapper = new ObjectMapper();
    		 
    		try {
    			payload = objectMapper.writeValueAsString(userStory);
    		
    		} catch (JsonProcessingException e) {
    			System.out.println("Fehler beim Erstellen eines JSON-Strings aus einer UserStory");
    			e.printStackTrace();
    			return false;
    		}
    		
    		System.out.println("DEBUG INFO Payload: "+ payload);
    		
    	}
    	amqpTemplate.convertAndSend("response.exchange", "routing.key" , payload);
    	return true;  	
    }
}
