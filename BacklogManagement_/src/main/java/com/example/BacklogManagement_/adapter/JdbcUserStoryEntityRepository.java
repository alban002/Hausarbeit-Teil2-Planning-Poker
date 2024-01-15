package com.example.BacklogManagement_.adapter;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserStoryEntityRepository extends CrudRepository<UserStoryEntity, Integer> {
    
    // Dieses Interface definiert eine Spring Data JPA-Repository-Schnittstelle für die UserStoryEntity-Klasse.

	// @Modifying und @Query markieren die Methode createTable als eine, die eine Änderung an der Datenbank durchführt.
    // Hier wird SQL verwendet, um eine Tabelle namens USERSTORY in der Datenbank zu erstellen, falls sie nicht bereits existiert.
    @Modifying
    @Query("CREATE TABLE IF NOT EXISTS USERSTORY (USER_STORY_ID INT AUTO_INCREMENT PRIMARY KEY,DESCRIPTION VARCHAR(255),TITLE VARCHAR(255),ESTIMATION INT);")
    boolean createTable();
    // Die Methode gibt true zurück, wenn die Tabelle erfolgreich erstellt wurde, andernfalls false.
}
