package com.example.PlanningPoker_.adapter;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserStoryEntityRepository 
	extends CrudRepository<UserStoryEntity, Integer> {

	// Diese Schnittstelle stellt eine Repository-Klasse dar, die für den Zugriff auf User Story-Entitäten in der Datenbank verwendet wird.

	@Modifying
    @Query("CREATE TABLE IF NOT EXISTS USERSTORYPP (USER_STORY_ID INT AUTO_INCREMENT PRIMARY KEY,DESCRIPTION VARCHAR(255),TITLE VARCHAR(255),FINAL_ESTIMATION INT);")
    boolean createTable();
	// Diese Methode verwendet die Spring Data JDBC-Annotationen, um eine benutzerdefinierte SQL-Abfrage auszuführen, die eine Datenbanktabelle erstellt.

    // @Modifying: Diese Annotation gibt an, dass die Abfrage eine Änderung in der Datenbank bewirken kann, wie das Erstellen einer Tabelle.

    // @Query: Diese Annotation gibt die SQL-Abfrage an, die ausgeführt werden soll, um die Tabelle zu erstellen. Die Abfrage erstellt eine Tabelle namens "USERSTORYPP" mit bestimmten Spalten.

    // "CREATE TABLE IF NOT EXISTS USERSTORYPP (USER_STORY_ID INT AUTO_INCREMENT PRIMARY KEY,DESCRIPTION VARCHAR(255),TITLE VARCHAR(255),FINAL_ESTIMATION INT);":
    // Dies ist die SQL-Abfrage, die die Tabelle erstellt. Sie prüft zuerst, ob die Tabelle bereits existiert (IF NOT EXISTS), und erstellt sie, wenn sie nicht vorhanden ist. Die Tabelle enthält Spalten für "USER_STORY_ID" (als Primärschlüssel), "DESCRIPTION", "TITLE" und "FINAL_ESTIMATION".

    // boolean: Diese Methode gibt einen booleschen Wert zurück, der anzeigt, ob die Tabellenerstellung erfolgreich war oder nicht. Dies wird normalerweise verwendet, um zu überprüfen, ob die Tabelle bereits existiert und keine erneute Erstellung erforderlich ist.
}