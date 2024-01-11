package com.example.PlanningPoker_.adapter;

import org.springframework.amqp.core.AmqpTemplate;

import com.example.PlanningPoker_.application.MessageQueue;
import com.example.PlanningPoker_.domain.UserStory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageQueueAdapter implements MessageQueue{
	
	
    public static final boolean NON_DURABLE = false;
    public static final String MY_QUEUE_NAME = "ppQueue";
    
    private static final String TOPIC= "response.exchange";
    private static final String KEY= "routing.key";
    
    private final AmqpTemplate amqpTemplate;
    
    public MessageQueueAdapter (AmqpTemplate amqpTemplate) {
    	this.amqpTemplate = amqpTemplate;
    }
   
    public boolean sendenRabbitMQ(Object domainEvent) {
    	
    	String payload = "";
    	UserStory userStory = (UserStory)domainEvent;
    	
    	if (domainEvent != null) {
   		
    		ObjectMapper objectMapper = new ObjectMapper();
    		 
    		try {
    			payload = objectMapper.writeValueAsString(userStory);
    		
    		} catch (JsonProcessingException e) {
    			System.out.println("Fehler beim Erstellen eines JSON-Strings aus einer UserStory");
    			e.printStackTrace();
    			return false;
    		}
  		
   		
    	}
    	amqpTemplate.convertAndSend(TOPIC, KEY , payload);
    	// Die Konstante TOPIC ist eine Exchange des Types Topic, wei  in der GUI unter Exchanges von RabbitMQ einsehbar ist
    	System.out.println("RabbitMQ_INFO: UserStory mit ID "+ userStory.getUserStoryId().getUserStoryId() + " gesendet an " + MY_QUEUE_NAME + " mit Topic "+ TOPIC);
    	return true;  	
    }
}