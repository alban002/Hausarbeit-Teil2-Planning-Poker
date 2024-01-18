package com.example.BacklogManagement_.application;

import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

public interface UserStoryRepository {

    // Eine Methode zum Suchen einer User Story anhand ihrer ID.
    public UserStory findById(UserStoryId userStoryId);

    // Eine Methode zum Speichern einer User Story in der Datenbank.
    public void save(UserStory userStory);

    // Eine Methode zum Löschen einer User Story aus der Datenbank anhand ihrer ID.
    public void deleteById(int userStoryDBId);

    // Eine Methode zum Erstellen einer User Story-Tabelle in der Datenbank.
    public boolean createUserStoryTable();
}