package com.example.BacklogManagement_;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BMEventListener {

	private static final String MY_QUEUE_NAME = "myQueue";

	private BacklogManagementService backlogManagementService;

	public BMEventListener (BacklogManagementService backlogManagementService) {
		this.backlogManagementService = backlogManagementService;
	}
	
	/*@RabbitListener(queues = MY_QUEUE_NAME)
	 public void listen(String message) {
		 System.out.println("DEBUGINFO Nachricht: "+ message);
		 
		 String parts[] = message.split(Pattern.quote("/"));
		 
		 String event = parts[0];
		 String payload = parts[1];
		 
		 System.out.println(event);
		 System.out.println(payload);
		 
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 if (event.equals("bestellungAbgeschlossen")) {

			 ObjectMapper mapper = new ObjectMapper();
			 BestellItemTO[] bestelllisteTOArray = null;
			try {
				bestelllisteTOArray = mapper.readValue(payload, BestellItemTO[].class);
			} catch (JsonProcessingException e) {
				// die folgende Meldung gehört eigentlich in ein Log.
				System.out.println("Interner Fehler bei der Eventverarbeitung");
				e.printStackTrace();
			}
			 Collection<BestellItemTO> bestelllisteTO = Arrays.asList(bestelllisteTOArray);
			
			 if (! lagerService.bestellungVerarbeiten(bestelllisteTO))
				// die folgende Meldung gehört eigentlich in ein Log
				System.out.println("Verarbeitung der Bestellung fehlgeschlagen!"); 
				
		 }
	 }*/
}