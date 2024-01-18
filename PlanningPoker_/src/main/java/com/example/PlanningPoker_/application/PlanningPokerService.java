package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.adapter.FestlegungsversuchResult;

public interface PlanningPokerService {

    // Methode zur Festlegung einer endgültigen Schätzung über Kafka.
    // Es wird die Userstory-ID, die endgültige Schätzung und die Rolle des Teilnehmers als Parameter erwartet.
    // Die Methode gibt ein Ergebnis zurück, das den Status der Aktion angibt (z.B., Erfolg, Fehler usw.).
    public FestlegungsversuchResult endgueltigeEstimationFestlegenKafka(int userStoryId, int finalEstimation, String Rolle);

    // Methode zur Festlegung einer endgültigen Schätzung über RabbitMQ.
    // Es wird die Userstory-ID, die endgültige Schätzung und die Rolle des Teilnehmers als Parameter erwartet.
    // Die Methode gibt ein Ergebnis zurück, das den Status der Aktion angibt (z.B., Erfolg, Fehler usw.).
    public FestlegungsversuchResult endgueltigeEstimationFestlegenRabbitMQ(int userStoryId, int finalEstimation, String Rolle);

    // Methode zum Erstellen der UserStory-Tabelle in der Datenbank.
    // Gibt `true` zurück, wenn die Tabelle erfolgreich erstellt wurde, ansonsten `false`.
    public boolean userStoryTabelleErstellen();

    // Methode zum Befüllen der UserStory-Tabelle mit Beispieldaten.
    public void populateUserStoryTable();

    // Methode zum Löschen einer Benutzerstory anhand ihrer ID.
    // Gibt `true` zurück, wenn die Benutzerstory erfolgreich gelöscht wurde, ansonsten `false`.
    public boolean deleteUserStoryById(int id);
}