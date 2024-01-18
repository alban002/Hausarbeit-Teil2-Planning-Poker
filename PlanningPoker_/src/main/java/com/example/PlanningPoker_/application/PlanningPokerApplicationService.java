package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.adapter.FestlegungsversuchResult;
import com.example.PlanningPoker_.adapter.KafkaProducerService;
import com.example.PlanningPoker_.domain.PlanningPokerDomainService;
import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;

public class PlanningPokerApplicationService implements PlanningPokerService {

    private UserStoryRepository userStoryRepository;
    private PlanningPokerDomainService planningPokerDomainService;
    private KafkaProducerService kafkaProducerService;
    private MessageQueue messageQueue;

    public PlanningPokerApplicationService(UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService, MessageQueue messageQueue, KafkaProducerService kafkaProducerService) {
        this.userStoryRepository = userStoryRepository;
        this.planningPokerDomainService = planningPokerDomainService;
        this.messageQueue = messageQueue;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public FestlegungsversuchResult endgueltigeEstimationFestlegenKafka(int userStoryId, int finalEstimation, String rolle) {
        // Überprüfen, ob der Benutzer die Berechtigung (die richtige Rolle) hat
        boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen(rolle);

        if (!istBerechtigt) {
            return FestlegungsversuchResult.PERMISSION_DENIED;
        }

        // UserStory anhand der ID aus dem Repository abrufen
        UserStory userStory = userStoryRepository.findById(new UserStoryId(userStoryId));

        if (userStory == null) {
            return FestlegungsversuchResult.USER_STORY_NOT_FOUND;
        }

        // Final Estimation setzen und in der Datenbank speichern
        userStory.setFinalEstimation(finalEstimation);
        userStoryRepository.save(userStory);

        // Asynchrone Kommunikation zum anderen Service über Kafka
        kafkaProducerService.sendUserStory(userStory);

        return FestlegungsversuchResult.SUCCESS;
    }

    @Override
    public FestlegungsversuchResult endgueltigeEstimationFestlegenRabbitMQ(int userStoryId, int finalEstimation, String Rolle) {
        // Überprüfen, ob der Benutzer die Berechtigung hat
        boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen(Rolle);

        if (!istBerechtigt) {
            return FestlegungsversuchResult.PERMISSION_DENIED;
        }

        // UserStory anhand der ID aus dem Repository abrufen
        UserStory userStory = userStoryRepository.findById(new UserStoryId(userStoryId));

        if (userStory == null) {
            return FestlegungsversuchResult.USER_STORY_NOT_FOUND;
        }

        // Final Estimation setzen und in der Datenbank speichern
        userStory.setFinalEstimation(finalEstimation);
        userStoryRepository.save(userStory);

        // Asynchrone Kommunikation zum anderen Service über RabbitMQ
        messageQueue.sendenRabbitMQ(userStory);

        return FestlegungsversuchResult.SUCCESS;
    }

    @Override
    public boolean userStoryTabelleErstellen() {
        return userStoryRepository.createUserStoryTable();
    }

    // Methode zur Befüllung der UserStory-Tabelle mit Beispieldaten
    public void populateUserStoryTable() {
        UserStory userStory1 = new UserStory("ERSTEBeschreibung", "ERSTERTitel", 2);
        UserStory userStory2 = new UserStory("ZWEITEBeschreibung", "ZWEITERTitel", 8);
        UserStory userStory3 = new UserStory("DRITTEBeschreibung", "DRITTERTitel", 9);
        userStoryRepository.save(userStory1);
        userStoryRepository.save(userStory2);
        userStoryRepository.save(userStory3);
    }

    //Metode zum Löschen einer UserStory aus der Datenbank anhand der ID
    @Override
    public boolean deleteUserStoryById(int id) {
        UserStory userStoryDB = userStoryRepository.findById(new UserStoryId(id));

        if (userStoryDB == null) {
            return false;
        } else {
            userStoryRepository.deleteById(id);
            return true;
        }
    }
}