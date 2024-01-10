# Hausarbeit-Teil2-Planning-Poker
Das Repository enthaelt die Codebasis sowie andere relevante Daten bezüglich Teil 2 der Hausarbeit
Das Strategic Design ergab zwei Bounded Contexts: BacklogManagement und PlanningPoker, die  in UML modelliert sind.

Tactical-Design:
Dem Backlogmanagement schreiben wir verwaltende Funktionalitaeten zu:
- Das Hinzufuegen und Loeschen von UserStorys
- Das Bearbeiten von UserStorys hinsichtlich Titel, Beschreibung, Akzeptanzkriterien usw.
Fernere Funktionalitäten, die so nicht in der Aufgabenstellung definiert sind koennten sein:
- Das Zuweisen zu einem für die Implementierung zustaendigen Mitarbeiters
- Das Zuweisen zu einem für das Review zuständigen Mitarbeiters
- Das Tracking des Bearbeitungsstatus (ToDo, In-Doing, Review, Finished)
- Etc.

PlanningPoker soll die Funktionalitäten des Schaetzungsprozesses von UserStorys abdecken:
- Verschiedene Rollen wie "product owner" und "developer" sollen unterschiedliche Rechte haben
- Product Owner soll UserStorys zu einer Estimationround hinzufuegen koennen
- Product Owner soll eine Estimationround starten und stoppen koennen
- Alle Teilnehmer einer Runde sollen eine Schaetzung für eine bestimmte Userstory abgeben koennen
- Der Product Owner soll die endgeultige Schaetzung einer Userstory festlegen koennen (In der Realitaet im Anschluss auf eine Diskussionsrunde)

Immer dann, wenn eine finale Schaetzung durch den Product Owner festgelegt wurde soll der PlanningPoker Microservice mit dem Backlogmanagement
asyncron kommunizieren, um dieses ueber die Aenderung zu informieren, damit dieses diese uebernehmen kann.

Anleitung zum Starten der beiden Microservices:
**TODO**
