package com.example.BacklogManagement_.adapter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumerService {

    private final BacklogManagementService backlogManagementService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(BacklogManagementService backlogManagementService, ObjectMapper objectMapper) {
        // Konstruktor, um die Abhängigkeiten zu injizieren.
        this.backlogManagementService = backlogManagementService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "userstories", groupId = "backlog-management-group")
    // @KafkaListener definiert, dass diese Methode auf dem "userstories"-Kafka-Topic lauscht und zur Gruppe "backlog-management-group" gehört.
    public void listen(String message) {
        try {
            // Versuchen, die empfangene Nachricht zu deserialisieren.
            JsonNode jsonNode = objectMapper.readTree(message);
            
            // Extrahiere die erforderlichen Informationen aus der JSON-Nachricht.
            UserStoryId userStoryId = new UserStoryId(jsonNode.get("userStoryId").get("userStoryId").asInt());
            String title = jsonNode.get("title").asText();
            String description = jsonNode.get("description").asText();
            int finalEstimation = jsonNode.get("estimation").asInt();
            
            // Erstelle ein UserStory-Objekt mit den extrahierten Informationen.
            UserStory userStory = new UserStory(userStoryId, title, description, finalEstimation);
            
            // Gib eine Info-Nachricht aus, dass die User Story angekommen ist.
            System.out.println("KAFKA_CONSUMER_INFO: UserStory mit ID " + userStoryId.getId() + " angekommen");
            
            // Rufe die backlogManagementService.userStoryUpdaten-Methode auf, um die User Story zu verarbeiten.
            backlogManagementService.userStoryUpdaten(userStory);
        } catch (Exception e) {
            // Wenn eine Ausnahme auftritt, gib eine Fehlermeldung aus.
            System.out.println("KAFKA_CONSUMER_INFO: UserStory konnte nicht deserialisiert werden");
        }
    }
}
