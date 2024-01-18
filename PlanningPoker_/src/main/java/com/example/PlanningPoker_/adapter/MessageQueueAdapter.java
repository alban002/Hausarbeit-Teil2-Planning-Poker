package com.example.PlanningPoker_.adapter;

import org.springframework.amqp.core.AmqpTemplate;

import com.example.PlanningPoker_.application.MessageQueue;
import com.example.PlanningPoker_.domain.UserStory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageQueueAdapter implements MessageQueue {
	
    public static final boolean NON_DURABLE = false;
   
    
    // Definieren von Exchange- und Routing-Key-Konstanten für RabbitMQ.
    // TOPIC und KEY sind zwei Konzepte, die in der Nachrichtenübermittlung mit RabbitMQ verwendet werden:
    // Exchange (Austausch) und Routing Key (Routing-Schlüssel)
    // Ein Exchange (Austausch) eine Art Nachrichtenverteiler, der entscheidet, an welche Warteschlangen (Queues)
    // eine Nachricht weitergeleitet wird. Dabei gibt es verschiedene Arten von Exchanges (Direct, Fanout, Topic, und Headers.)
    // Und jeder Typ hat sein eigenes Routing-Verfahren zu den Queues. In diesem Fall wird Topic eingesetzt, das es ermöglicht Nachrichten an Queues 
    // basierend auf Muster-Übereinstimmung zu senden. D.h. eine Queue die an einen Bestimmten Key gebunden ist, würde Nachrichten
    // mit demselben Key erhalten, jedoch keine anderen. 
    private static final String TOPIC = "response.exchange";
    private static final String KEY = "routing.key";
    
    
    //ppQueue ist im so konfiguriert ist, dass sie Nachrichten akzeptiert, die an den response.exchange Exchange mit dem Routing Key routing.key gesendet werden. 
    public static final String MY_QUEUE_NAME = "ppQueue";
    
    private final AmqpTemplate amqpTemplate;
    
    public MessageQueueAdapter(AmqpTemplate amqpTemplate) {
    	// Konstruktor, der die erforderlichen Abhängigkeiten (AmqpTemplate) injiziert.
    	this.amqpTemplate = amqpTemplate;
    }
   
    public boolean sendenRabbitMQ(Object domainEvent) {
    	
    	String payload = "";
    	UserStory userStory = (UserStory) domainEvent;
    	
    	if (domainEvent != null) {
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		 
    		try {
    			// Die UserStory wird in einen JSON-String umgewandelt, um sie an RabbitMQ zu senden.
    			
    			payload = objectMapper.writeValueAsString(userStory);
    		
    		} catch (JsonProcessingException e) {
    			// Im Falle eines Fehlers bei der JSON-Erstellung wird eine Fehlermeldung ausgegeben und genauere Infos zur Ausnahme  gedruckt.
    			System.out.println("Fehler beim Erstellen eines JSON-Strings aus einer UserStory");
    			e.printStackTrace();
    			return false;
    		}
    	}
    	
    	// Die Methode convertAndSend des AmqpTemplates sendet die Nachricht an RabbitMQ mit dem angegebenen Exchange und Routing Key.
    	amqpTemplate.convertAndSend(TOPIC, KEY, payload);
    	
    	// Eine Erfolgsmeldung wird auf der Konsole ausgegeben.
    	System.out.println("RabbitMQ_INFO: UserStory mit ID "+ userStory.getUserStoryId().getUserStoryId() + " gesendet an " + MY_QUEUE_NAME + " mit Topic "+ TOPIC);
    	
    	return true;  	
    }
}