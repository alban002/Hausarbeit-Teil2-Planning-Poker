package com.example.BacklogManagement_;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/backlogManagement")
public class BacklogManagementController {
	
	private BacklogManagementService backlogManagementService;
	
	public BacklogManagementController (BacklogManagementService backlogManagementService) {
		this.backlogManagementService = backlogManagementService;
	}
	
	//curl -X GET http://localhost:8090/backlogManagement/userStory/1000
	@GetMapping("/userStory/{id}")
	public String test(@PathVariable int id) {
		return id + "TEST";
	    //	return lagerService.bestandErmitteln(id)+"";
	}
	
	//curl -X GET http://localhost:8090/lager/artikel/1000
	
	/*@GetMapping("/artikel/{id}")
	public String bestandErmitteln(@PathVariable int id) {	
	    	return lagerService.bestandErmitteln(id)+"";
	}
	
	
	@PutMapping("/artikel/{id}/{menge}")
	public String einlagern(@PathVariable int id, @PathVariable int menge) {
		if (lagerService.artikelEinlagern( id, menge))
				return "Einlagerung erfolgreich!";
			else
				return "Einlagerung nicht erfolgreich!";
	}*/
	    

}
