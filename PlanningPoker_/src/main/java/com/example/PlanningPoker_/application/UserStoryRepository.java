package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;

public interface UserStoryRepository {

    // Methode zum Suchen einer UserStory anhand ihrer eindeutigen ID.
    // Sie erwartet eine Instanz von UserStoryId als Parameter und gibt die gefundene Benutzerstory zurück oder null, wenn sie nicht gefunden wurde.
    public UserStory findById(UserStoryId userStoryID);

    // Methode zum Speichern einer UserStory in der Datenbank.
    // Sie erwartet eine Instanz von UserStory als Parameter und speichert sie in der Datenbank.
    public void save(UserStory userStory);

    // Methode zum Erstellen der UserStory-Tabelle in der Datenbank.
    // Gibt `true` zurück, wenn die Tabelle erfolgreich erstellt wurde, ansonsten `false`.
    public boolean createUserStoryTable();

    // Methode zum Löschen einer UserStory anhand ihrer ID.
    // Sie erwartet die ID der UserStory, die gelöscht werden soll, als Parameter.
    public void deleteById(int userStoryDBid);
}