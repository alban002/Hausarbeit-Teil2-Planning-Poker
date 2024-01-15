package com.example.BacklogManagement_.adapter;

//Importiert die Pattern-Klasse für reguläre Ausdrücke
import java.util.regex.Pattern; 

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
//Importiert die RabbitMQ-Listener-Annotation
import org.springframework.amqp.rabbit.annotation.RabbitListener; 

import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;
import com.fasterxml.jackson.core.JsonProcessingException;

//Importiert den ObjectMapper für JSON-Operationen
import com.fasterxml.jackson.databind.ObjectMapper; 
//Importiert die JsonNode-Klasse für das Parsen von JSON
import com.fasterxml.jackson.databind.JsonNode; 

// Klasse, die als Listener für RabbitMQ-Nachrichten dient
public class BMEventListener {

	// Name der Queue, auf die der Listener hört
	private static final String MY_QUEUE_NAME = "ppQueue"; 
	// Name des Topics, unter dem die Nachrichten veröffentlicht werden
	private static final String TOPIC= "response.exchange"; 

    // 	Service für die Verarbeitung der UserStories
	private BacklogManagementService backlogManagementService; 

	public BMEventListener (BacklogManagementService backlogManagementService) {
		// Konstruktor injiziert den BacklogManagementService
		this.backlogManagementService = backlogManagementService; 
	}
	
	// RabbitMQ Listener Methode, die auf Nachrichten von MY_QUEUE_NAME hört
	@RabbitListener(queues = MY_QUEUE_NAME)
	public void listen(String message) {
 		 try {
 			 	// Erstellt eine Instanz von ObjectMapper
		        ObjectMapper mapper = new ObjectMapper(); 
		        // Parst das JSON aus der Nachricht in ein JsonNode
		        JsonNode userStoryNode = mapper.readTree(message); 

		        // Extrahiert Werte aus dem JSON-Objekt
		        UserStoryId userStoryId = new UserStoryId(userStoryNode.get("userStoryId").get("userStoryId").asInt());
		        String title = userStoryNode.get("title").asText();
		        String description = userStoryNode.get("description").asText();
		        int finalEstimation = userStoryNode.get("finalEstimation").asInt();
		        
		        // Erstellt eine neue UserStory-Instanz mit den extrahierten Daten
		        UserStory angekommeneUserStory = new UserStory(userStoryId, title, description, finalEstimation);

		        // Gibt eine Nachricht in der Konsole aus, dass eine UserStory angekommen ist
		        System.out.println("RabbitMQ_INFO: UserStory mit ID " + angekommeneUserStory.getUserStoryId().getId() + " von " + MY_QUEUE_NAME + " mit Topic " + TOPIC + "angekommen");
		        // Ruft den Service auf, um die empfangene UserStory zu aktualisieren\r\n"
		        backlogManagementService.userStoryUpdaten(angekommeneUserStory);
 		} catch (Exception e) {
	        // Fehlerbehandlung: Gibt eine Fehlermeldung aus, wenn ein Problem bei der Deserialisierung auftritt
	        System.out.println("Fehler bei der Deserialisierung der User Story");
	     // Druckt den Stacktrace des Fehlers aus, um die Fehlersuche zu erleichtern
	        e.printStackTrace(); 
	    }
	}
}