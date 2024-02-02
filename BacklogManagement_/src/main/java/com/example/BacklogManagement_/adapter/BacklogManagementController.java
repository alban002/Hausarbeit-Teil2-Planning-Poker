package com.example.BacklogManagement_.adapter;

//Importiert die Klasse für den Umgang mit Datenzugriffsfehlern
import org.springframework.dao.DataAccessException; 
//Importiert Klassen für HTTP-Statuscodes
import org.springframework.http.HttpStatus; 
//Importiert die Klasse für Entitäten in Antworten
import org.springframework.http.ResponseEntity; 
//Importiert Annotationen für die Webanbindung
import org.springframework.web.bind.annotation.*; 
//Importiert den Service
import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.application.UserStoryDTO;
//Importiert das UserStory-Modell
import com.example.BacklogManagement_.domain.UserStory; 

@RestController // Markiert die Klasse als Controller für REST-Endpunkte
@RequestMapping("/backlogManagement") // Definiert den Basispfad für alle Routen in dieser Klasse
public class BacklogManagementController {
	
	// Service-Instanz für Geschäftslogik
	private BacklogManagementService backlogManagementService; 

	public BacklogManagementController(BacklogManagementService backlogManagementService) {
		// Konstruktor injiziert den Service
		this.backlogManagementService = backlogManagementService; 
	}
	
	//curl -X POST http://localhost:8100/backlogManagement/createUserStoryTable
	
	// Methode zum Erstellen einer UserStory-Tabelle
	@PostMapping("/createUserStoryTable")
	public ResponseEntity<String> createUserStoryTable() {
		try {
			// Ruft den Service auf, um die Tabelle zu erstellen
			backlogManagementService.userStoryTabelleErstellen(); 
			return ResponseEntity.ok("Tabelle erfolgreich erstellt oder bereits vorhanden");
		} catch (Exception e) {
			// Fehlerbehandlung, falls beim Erstellen der Tabelle ein Fehler auftritt
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim Anlegen der UserStoryTabelle");
		}
	}
	
	//curl -X POST http://localhost:8100/backlogManagement/fillUpUserStoryTable

	// Methode zum Auffüllen der UserStory-Tabelle
	@PostMapping("/fillUpUserStoryTable")
	public ResponseEntity<String> fillUpUserStoryTable() {
		try {
			// Ruft den Service auf, um die Tabelle zu füllen
			backlogManagementService.populateUserStoryTable(); 
			return ResponseEntity.ok("UserStoryTabelle erfolgreich aufgefuellt");
		} catch (Exception e) {
			// Fehlerbehandlung, falls beim Auffüllen der Tabelle ein Fehler auftritt
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim Anlegen der UserStoryTabelle");
		}
	}
	
	//curl -X PUT -H "Content-Type: application/json" -d "{\"userStoryId\": 1, \"description\": \"WURDEGAENDET\", \"title\": \" TitelGEAENDERT\", \"estimation\": 6}" http://localhost:8100/backlogManagement/updateUserStory

	// Methode zum Aktualisieren einer UserStory
	@PutMapping("/updateUserStory")
	public ResponseEntity<String> updateUserStory(@RequestBody UserStoryDTO updatedUserStory) {
		boolean isUpdated = backlogManagementService.userStoryUpdaten(updatedUserStory.fromDTOtoNormal()); // Ruft den Service auf, um die UserStory zu aktualisieren
		if (isUpdated) {
		// Sendet eine Erfolgsmeldung, wenn das Update erfolgreich war
		return ResponseEntity.ok("Update von UserStory mit ID " + updatedUserStory.getUserStoryId().getId() + " erfolgreich!");
		} else {
		// Sendet eine Fehlermeldung, wenn das Update nicht erfolgreich war
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update von UserStory mit ID " + updatedUserStory.getUserStoryId().getId() + " nicht erfolgreich!");
		}
	}
	
	//curl -X POST "http://localhost:8100/backlogManagement/createUserStory?description=IchwurdeERstellt&title=Titel&estimation=7"
	
	// Methode zum Erstellen einer neuen UserStory
	@PostMapping("/createUserStory")
	public ResponseEntity<String> createUserStory(
	    @RequestParam("description") String description, // Nimmt Beschreibung als Request-Parameter entgegen
	    @RequestParam("title") String title, // Nimmt Titel als Request-Parameter entgegen
	    @RequestParam("estimation") int estimation) { // Nimmt Schätzung als Request-Parameter entgegen
	    
		// Erstellt ein neues UserStory-Objekt
	    UserStory newUserStory = new UserStory(description, title, estimation); 
	    try {
	    	// Ruft den Service auf, um die UserStory zu erstellen
	        backlogManagementService.userStoryErstellen(newUserStory); 
	        return ResponseEntity.ok("Erstellung der neuen UserStory erfolgreich!");
	    } catch (DataAccessException e) {
	        // Fehlerbehandlung, falls bei der Erstellung der UserStory ein Fehler auftritt
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Fehler beim Erstellen der neuen UserStory: " + e.getMessage());
	    }
	}

	//curl -X GET http://localhost:8100/backlogManagement/getUserStoryById/1
	// Methode zum Abrufen einer UserStory anhand ihrer ID
	@GetMapping("/getUserStoryById/{id}")
	// Nimmt die ID als Pfadvariable entgegen
	public ResponseEntity<String> getUserStoryById(@PathVariable int id) { 
	    try {
	    	// Ruft den Service auf, um die UserStory zu erhalten
	        UserStory userStory = backlogManagementService.getUserStoryById(id); 
	        if (userStory != null) {
	            // Sendet die Daten der UserStory, wenn sie gefunden wurde
	            return ResponseEntity.ok("UserStory mit Id= "+ id + " heißt " + userStory.getTitle() + " und wurde mit " + userStory.getEstimation() + " geschätzt");
	        } else {
	            // Sendet eine Nachricht, wenn die UserStory nicht gefunden wurde
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserStory mit ID: " + id + " existiert nicht");
	        }
	    } catch (DataAccessException e) {
	        // Fehlerbehandlung, falls beim Abrufen der UserStory ein Fehler auftritt
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Fehler beim Abrufen der UserStory mit ID: " + id);
	    }
	}
	//curl -X DELETE "http://localhost:8100/backlogManagement/deleteUserStoryById/3"

	// Methode zum Löschen einer UserStory anhand ihrer ID
	@DeleteMapping("/deleteUserStoryById/{id}")
	public ResponseEntity<String> deleteUserStoryById(@PathVariable int id) { // Nimmt die ID als Pfadvariable entgegen
	    try {
	        boolean isDeleted = backlogManagementService.deleteUserStoryById(id); // Ruft den Service auf, um die UserStory zu löschen
	        if (isDeleted) {
	            // Sendet eine Erfolgsmeldung, wenn das Löschen erfolgreich war
	            return ResponseEntity.ok("UserStory mit ID " + id + " wurde erfolgreich gelöscht.");
	        } else {
	            // Sendet eine Nachricht, wenn die UserStory nicht gefunden oder nicht gelöscht werden konnte
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Keine UserStory mit der ID " + id + " gefunden oder konnte nicht gelöscht werden.");
	        }
	    } catch (DataAccessException e) {
	        // Fehlerbehandlung, falls beim Löschen der UserStory ein Fehler auftritt
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Fehler beim Löschen der UserStory mit ID: " + id);
	    }
	}
}