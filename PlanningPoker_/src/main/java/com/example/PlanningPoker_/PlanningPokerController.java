package com.example.PlanningPoker_;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/planningPoker")
public class PlanningPokerController {

	//Zugewiesene Implementation ist PlanningPokerApplicationService
	private PlanningPokerService planningPokerService;
	
	public PlanningPokerController (PlanningPokerService planningPokerService) {
		this.planningPokerService = planningPokerService;
	}
	
	//curl -X POST "http://localhost:8090/planningPoker/endgueltigeEstimationById/1?finalEstimationValue=3"
	
	@PostMapping("/endgueltigeEstimationById/{userStoryId}")
	public ResponseEntity<String> endgueltigeEstimationById(
	        @PathVariable int userStoryId,
	        @RequestParam("finalEstimationValue") int finalEstimationValue) {
	    
	    FestlegungsversuchResult result = planningPokerService.endgueltigeEstimationFestlegen(userStoryId, finalEstimationValue);
	    
	    switch (result) {
	        case SUCCESS:
	            return ResponseEntity.ok("Endgültige Estimation wurde auf " + finalEstimationValue + " festgelegt");
	        case PERMISSION_DENIED:
	            return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                                 .body("Keine Berechtigung zum Festlegen einer endgueltigen Estimation");
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
}
