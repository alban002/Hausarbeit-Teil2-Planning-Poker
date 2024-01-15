package com.example.BacklogManagement_.application;

import com.example.BacklogManagement_.domain.UserStory;

public interface BacklogManagementService {

    // Eine Methode zum Aktualisieren einer User Story.
    public boolean userStoryUpdaten(UserStory updatedUserStory);

    // Eine Methode zum Erstellen einer neuen User Story.
    public boolean userStoryErstellen(UserStory newUserStory);

    // Eine Methode zum Abrufen einer User Story anhand ihrer ID.
    UserStory getUserStoryById(int id);

    // Eine Methode zum Löschen einer User Story anhand ihrer ID.
    public boolean deleteUserStoryById(int id);

    // Eine Methode zum Erstellen einer User Story-Tabelle in der Datenbank.
    public boolean userStoryTabelleErstellen();

    // Eine Methode zum Befüllen der User Story-Tabelle mit Beispieldaten.
    public void populateUserStoryTable();
}