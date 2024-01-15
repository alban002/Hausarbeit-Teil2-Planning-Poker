package com.example.PlanningPoker_.adapter;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PlanningPoker_.application.PlanningPokerService;

@RestController
@RequestMapping("/planningPoker")
public class PlanningPokerController {

    // Die zugewiesene Implementierung ist PlanningPokerApplicationService.
    private PlanningPokerService planningPokerService;
    
    public PlanningPokerController(PlanningPokerService planningPokerService) {
        this.planningPokerService = planningPokerService;
    }
    
    //curl -X POST http://localhost:8090/planningPoker/createUserStoryTable
    // Endpunkt zum Erstellen der UserStory-Tabelle.
    @PostMapping("/createUserStoryTable")
    public ResponseEntity<String> createUserStoryTable() {
        try {
            planningPokerService.userStoryTabelleErstellen();
            return ResponseEntity.ok("Tabelle erfolgreich erstellt oder bereits vorhanden");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim Anlegen der UserStory-Tabelle");
        }
    }
    
    // curl -X POST http://localhost:8090/planningPoker/fillUpUserStoryTable
    // Endpunkt zum Befüllen der UserStory-Tabelle.
    @PostMapping("/fillUpUserStoryTable")
    public ResponseEntity<String> fillUpUserStoryTable() {
        try {
            planningPokerService.populateUserStoryTable();
            return ResponseEntity.ok("Tabelle erfolgreich befüllt");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim Anlegen der UserStory-Tabelle");
        }
    }
    
    //Befehl fuer Test mit Rolle ProductOwner
  	//curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdRabbitMQ/1?finalEstimationValue=8&participantRole=ProductOwner"
  	//Oder fuer: RegularParticipant
  	//curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdRabbitMQ/1?finalEstimationValue=8&participantRole=RegularParticipant"
    // Endpunkt zum Festlegen der endgültigen Estimation einer UserStory über RabbitMQ.
    @PostMapping("/finalEstimationByIdRabbitMQ/{userStoryId}")
    public ResponseEntity<String> finalEstimationByIdRabbitMQ(
            @PathVariable int userStoryId,
            @RequestParam("finalEstimationValue") int finalEstimationValue,
            @RequestParam("participantRole") String participantRole) {

        // Überprüfen der gültigen Teilnehmerrolle (ProductOwner oder RegularParticipant).
        if (!participantRole.equals("ProductOwner") && !participantRole.equals("RegularParticipant")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Ungültige Teilnehmerrolle: " + participantRole);
        }

        // Aufruf der entsprechenden Methode in PlanningPokerService und Speicherung des Ergebnisses.
        FestlegungsversuchResult result = planningPokerService.endgueltigeEstimationFestlegenRabbitMQ(userStoryId, finalEstimationValue, participantRole);

        //Je nach Ergebnis wird eine andere Response zurueckgegeben 
        switch (result) {
            case SUCCESS:
                return ResponseEntity.ok("Endgültige Estimation wurde auf " + finalEstimationValue + " festgelegt");
            case PERMISSION_DENIED:
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                     .body("Keine Berechtigung zum Festlegen einer endgültigen Estimation");
            case USER_STORY_NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("UserStory mit ID " + userStoryId + " nicht gefunden");
            case OTHER_ERROR:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Ein unerwarteter Fehler ist aufgetreten");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Ein unerwarteter Fehler ist aufgetreten");
        }
    }
    
    //Befehl fuer Test mit Rolle ProductOwner
  	//curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdKafka/1?finalEstimationValue=8&participantRole=ProductOwner"
  	//Oder fuer: RegularParticipant
  	//curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdKafka/1?finalEstimationValue=8&participantRole=RegularParticipant"
    // Endpunkt zum Festlegen der endgültigen Estimation einer UserStory über Kafka.
    @PostMapping("/finalEstimationByIdKafka/{userStoryId}")
    public ResponseEntity<String> finalEstimationByIdKafka(
            @PathVariable int userStoryId,
            @RequestParam("finalEstimationValue") int finalEstimationValue,
            @RequestParam("participantRole") String participantRole) {

        // Überprüfen der gültigen Teilnehmerrolle (ProductOwner oder RegularParticipant).
        if (!participantRole.equals("ProductOwner") && !participantRole.equals("RegularParticipant")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Ungültige Teilnehmerrolle: " + participantRole);
        }

        // Aufruf der entsprechenden Methode in PlanningPokerService und Speicherung des Ergebnisses.
        FestlegungsversuchResult result = planningPokerService.endgueltigeEstimationFestlegenKafka(userStoryId, finalEstimationValue, participantRole);

        //Je nach Ergebnis wird eine andere Response zurueckgegeben 
        switch (result) {
            case SUCCESS:
                return ResponseEntity.ok("Endgültige Estimation wurde auf " + finalEstimationValue + " festgelegt");
            case PERMISSION_DENIED:
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                     .body("Keine Berechtigung zum Festlegen einer endgültigen Estimation");
            case USER_STORY_NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("UserStory mit ID " + userStoryId + " nicht gefunden");
            case OTHER_ERROR:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Ein unerwarteter Fehler ist aufgetreten");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Ein unerwarteter Fehler ist aufgetreten");
        }
    }
    
    // curl -X DELETE "http://localhost:8090/planningPoker/deleteUserStoryById/3"
    // Endpunkt zum Löschen einer UserStory anhand ihrer ID.
    @DeleteMapping("/deleteUserStoryById/{id}")
    public ResponseEntity<String> deleteUserStoryById(@PathVariable int id) {
        try {
            // Aufruf der Methode in PlanningPokerService und Verarbeitung des Ergebnisses.
            boolean isDeleted = planningPokerService.deleteUserStoryById(id);
            if (isDeleted) {
                return ResponseEntity.ok("UserStory mit ID " + id + " wurde erfolgreich gelöscht.");
            } else {
                String errorMessage = "Keine UserStory mit der ID " + id + " gefunden oder konnte nicht gelöscht werden.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(errorMessage);
            }
        } catch (DataAccessException e) {
            String errorMessage = "Fehler beim Löschen der UserStory mit ID: " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(errorMessage);
        }
    }
}