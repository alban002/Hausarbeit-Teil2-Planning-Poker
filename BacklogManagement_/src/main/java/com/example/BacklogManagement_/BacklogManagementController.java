package com.example.BacklogManagement_;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/backlogManagement") //TODO RETRUNWERTE RESPONSEENTITY
public class BacklogManagementController {
	
	//Implementation ist BacklogManagementApplicationService
	private BacklogManagementService backlogManagementService;
	
	public BacklogManagementController (BacklogManagementService backlogManagementService) {
		this.backlogManagementService = backlogManagementService;
	}
	
	//curl -X POST http://localhost:8100/backlogManagement/createUserStoryTable

	@PostMapping("/createUserStoryTable")
	public ResponseEntity<String> createUserStoryTable() {
		try{
			backlogManagementService.userStoryTabelleErstellen();
			return ResponseEntity.ok("Tabelle erfolgreich erstellt oder bereits vorhanden");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim anlegen der UserStoryTabelle");
		}
	}
	
	//curl -X POST http://localhost:8100/backlogManagement/fillUpUserStoryTable
	@PostMapping("/fillUpUserStoryTable")
	public ResponseEntity<String> fillUpUserStoryTable() {
		try{

	
		backlogManagementService.populateUserStoryTable();

			return ResponseEntity.ok("UserStoryTabelle erfolgreich aufgefuellt");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error beim anlegen der UserStoryTabelle");
		}
	}

	
	//curl -X PUT -H "Content-Type: application/json" -d "{\"userStoryId\": 1, \"description\": \"WURDEGAENDET\", \"title\": \" TitelGEAENDERT\", \"estimation\": 6}" http://localhost:8100/backlogManagement/updateUserStory
	
	@PutMapping("/updateUserStory")
	public ResponseEntity<String> updateUserStory(@RequestBody UserStory updatedUserStory) {
	    boolean isUpdated = backlogManagementService.userStoryUpdaten(updatedUserStory);
	    if (isUpdated) {
	        String successMessage = "Update von UserStory mit ID " + updatedUserStory.getUserStoryId().getId() + " erfolgreich!";
	        return ResponseEntity.ok(successMessage);
	    } else {
	        String errorMessage = "Update von UserStory mit ID " + updatedUserStory.getUserStoryId().getId() + " nicht erfolgreich!";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}

	
	//curl -X POST "http://localhost:8100/backlogManagement/createUserStory?description=IchwurdeERstellt&title=Titel&estimation=7"

	
	@PostMapping("/createUserStory")
	public ResponseEntity<String> createUserStory(
	    @RequestParam("description") String description,
	    @RequestParam("title") String title,
	    @RequestParam("estimation") int estimation) {
	    
	    UserStory newUserStory = new UserStory(description, title, estimation);
	    
	    try {
	        backlogManagementService.userStoryErstellen(newUserStory);
	        return ResponseEntity.ok("Erstellung der neuen UserStory erfolgreich!");
	    } catch (DataAccessException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Fehler beim Erstellen der neuen UserStory: " + e.getMessage());
	    }
	}
	
	//curl -X GET http://localhost:8100/backlogManagement/getUserStoryById/1
	@GetMapping("/getUserStoryById/{id}")
	public ResponseEntity<String> getUserStoryById(@PathVariable int id) {
	    try {
	        UserStory userStory = backlogManagementService.getUserStoryById(id);
	        if (userStory != null) {
	            return ResponseEntity.ok("UserStory mit Id= "+ id + " heisst " + userStory.getTitle() + " und wurde mit " + userStory.getEstimation() +" geschaetzt");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserStory mit ID: " + id + " exisitiert nicht");
	        }
	    } catch (DataAccessException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Fehler beim Abrufen der UserStory mit ID: " + id);
	    }
	}
	
	//curl -X DELETE "http://localhost:8100/backlogManagement/deleteUserStoryById/3"
	@DeleteMapping("/deleteUserStoryById/{id}")
	public ResponseEntity<String> deleteUserStoryById(@PathVariable int id) {
	    try {
	        boolean isDeleted = backlogManagementService.deleteUserStoryById(id);
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