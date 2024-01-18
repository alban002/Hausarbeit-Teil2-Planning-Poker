package com.example.PlanningPoker_.application;

public interface MessageQueue {
	
    // Diese Methode dient dazu, eine Nachricht an eine Message Queue (in diesem Fall RabbitMQ) zu senden.
    // Sie akzeptiert ein Objekt `messageObject`, das die Nachricht enthält, die gesendet werden soll.
    // Die Methode gibt `true` zurück, wenn die Nachricht erfolgreich gesendet wurde, andernfalls `false`.
	public boolean sendenRabbitMQ(Object messageObject);
}