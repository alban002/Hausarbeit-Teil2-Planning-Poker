package com.example.PlanningPoker_.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.PlanningPoker_.domain.UserStory;

@Service
public class KafkaProducerService {

    // Definieren des Kafka-Topics, auf das Nachrichten gesendet werden sollen.
    private static final String TOPIC = "userstories";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        // Konstruktor, der die erforderlichen Abhängigkeiten (KafkaTemplate und ObjectMapper) injiziert.
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // Diese Methode sendet eine UserStory-Nachricht an das definierte Kafka-Topic.
    public void sendUserStory(UserStory userStory) {
        try {
            // Die UserStory wird in JSON umgewandelt, um sie an Kafka zu senden.
            String message = objectMapper.writeValueAsString(userStory);
            
            // Die KafkaTemplate.send-Methode sendet die Nachricht an das definierte Kafka-Topic.
            kafkaTemplate.send(TOPIC, message);
            
            // Eine Erfolgsmeldung wird auf der Konsole ausgegeben, wenn die Nachricht erfolgreich gesendet wurde.
            System.out.println("KAFKA_PRODUCER_INFO: UserStory mit ID "+ userStory.getUserStoryId().getUserStoryId() + " gesendet an TOPIC " + TOPIC);
        } catch (Exception e) {
            // Im Falle eines Fehlers wird eine Fehlermeldung auf der Konsole ausgegeben und die Ausnahme wird gedruckt.
            System.out.println("Could not send UserStory to Kafka");
            e.printStackTrace();
        }
    }
}