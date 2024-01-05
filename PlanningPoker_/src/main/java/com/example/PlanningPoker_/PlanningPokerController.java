package com.example.PlanningPoker_;

import java.util.Collection;

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
	
	@PostMapping("/endgueltigeEstimationById/{userStoryId}")
	public String endgueltigeEstimationById(
			//Kombination von Path- und Query-Variable
	    @PathVariable int userStoryId,
	    @RequestParam("finalEstimationValue") int finalEstimationValue) {
	    
	    
	    if (planningPokerService.endgueltigeEstimationFestlegen(userStoryId, finalEstimationValue))
	        return "Endueltige Estimation wurde auf " + finalEstimationValue + " festgelegt";
	    else
	        return "Keine Berechtigung zum Festlegen einer endgueltigen Estimation";
	}

	
/*
	@PostMapping(value = "/bestellung", consumes = {"application/json"})
	public String bestellen(@RequestBody Collection<BestellItemTO> itemliste) {
		
		if (shopService.bestellen(itemliste))
			return "Bestellung erfolgreich";
		else
			return "Bestellung nicht erfolgreich";  	
	 }*/

}
