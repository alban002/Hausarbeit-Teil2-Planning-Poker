# Hausarbeit-Teil2-Planning-Poker
Das Repository enthaelt die Codebasis sowie andere relevante Daten bezüglich Teil 2 der Hausarbeit. <br>
**Das Strategic Design ergab zwei Bounded Contexts**: BacklogManagement und PlanningPoker, die  in UML modelliert sind.

**Tactical-Design**:
Dem Backlogmanagement schreiben wir verwaltende Funktionalitaeten zu:
- Das Hinzufuegen und Loeschen von UserStorys
- Das Bearbeiten von UserStorys hinsichtlich Titel, Beschreibung, Akzeptanzkriterien usw. <br>
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

Immer dann, wenn eine finale Schaetzung durch den Product Owner festgelegt wurde soll der PlanningPoker Microservice mit dem BacklogManagement
asyncron kommunizieren, um dieses ueber die Aenderung zu informieren, damit dieses diese uebernehmen kann.

## **Anleitung zum Starten der beiden Microservices**:

### 1. Alle benötigten Docker Container müssen hochgefahren werden 
Falls diese noch nicht auf dem zu testenden Gerät gepulled wurden, geht dies mit Hilfe der Befehle:
	
**RabbitMQ:**
- docker run -d --hostname rabbit-host  --name rabbitmq-container -p 15672:15672 -p 5672:5672 rabbitmq:3-management
	
**Hinweis:**
Bei RabbitMQ muss initial auch eine Exchange mit dem Namen "response.exchange" des Typs "topic" angelegt werden und mit dem Key "routing.key" an die Queue "ppQueue" gebindet werden
	
**Kafka:**
- docker run -d --hostname zookeeper-host --name zookeeper-container -p 2181:2181 zookeeper
- docker run -d --hostname kafka-host --name kafka-container -p 9093:9093 --env KAFKA_LISTENERS=PLAINTEXT://:9093 --env KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9093 --env KAFKA_ZOOKEEPER_CONNECT=zookeeper-host:2181 --link zookeeper-container:zookeeper-host wurstmeister/kafka
	
### 2. H2 Datenbank starten
	
**Hinweis:**
Benutzer, die das Mircoservices Initial ausführen, müssen vorher 2 neue Datenbanken anlegen. 
Das bedeutet, es muss eine 
**"jdbc:h2:~/database/BacklogManagement"**
sowie eine 
**"jdbc:h2:~/database/PlanningPoker"**
Datenbank erstellt werden. 
Die .mv Datein sind in unserem Fall somit in "C:\Benutzer\Benutzername\database" abgelegt.
	
### 3. Microservices starten
	
Die mit Springboot annotierten Klassen sind zu finden in **com.example.BacklogManagement_** sowie **com.example.PlanningPoker_**.
Diese können zum Beispiel über "Debug as --> Java Application" gestartet werden.
	
### 4. (Bei erstmaligem Start) Befüllen der Datenbank
	
Mit Hilfe der folgenden curl Befehle können die Tabellen erstellt und mit Testdaten aufgefüllt werden:
	
- curl -X POST http://localhost:8100/backlogManagement/createUserStoryTable
- curl -X POST http://localhost:8100/backlogManagement/fillUpUserStoryTable
- curl -X POST http://localhost:8090/planningPoker/createUserStoryTable
- curl -X POST http://localhost:8090/planningPoker/fillUpUserStoryTable
	
Die initialen UserStoryTabellen sehen wie folgt aus
	
| User_Story_ID | Description          | Titel        | Estimation |
|----|-----------------------|--------------|------|
| 1  | ERSTEBeschreibung     | ZWEITERTitel | 2    |
| 2  | ZWEITEBeschreibung    | ZWEITERTitel | 8    |
| 3  | DRITTEBeschreibung    | DRITTERTitel | 9    |
	
	
### 5. Asynchrone Kommunikation der beiden Microservices testen
	
Die asynchrone Kommunikation der beiden Microservices kann mit Hilfe der folgenden curl Befehle getestet werden:

RabbitMQ:	
- curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdRabbitMQ/USERSTORYID?finalEstimationValue=NEWESTIMATIONVALUE"

Kafka:
- curl -X POST "http://localhost:8090/planningPoker/finalEstimationByIdKafka/USERSTORYID?finalEstimationValue=NEWESTIMATIONVALUE"
	
**Hinweis:**
Bei den im Beispiel angegebene curl-Befehlen muessen an den Stellen "USERSTROYID" und "NEWESTIMATIONVALUE"  eigene Werten eingesetzt werden.
Die Berechtigung zum aendern der FinalEstimation wird in unserem Ausschnitt nicht durch eine tatsaechliche Ueberpruefung der Teilnehmerrolle erhoben.
Stattdessen wird sie mit hilfe einer 50% Wahrscheinlichkeit fuer jeden Fall (Berechtigung liegt vor/ Keine Berechtigung) bestimmt.	
	
### 6. Erwartete Ergebnisse
	
Der Microservice PlanningPoker sollte eine Message mit den Informationen einer Aenderung der finalen Estimation Value an einen der beiden Messagebroker übertragen.
Die BacklogManagement listener hören die entsprechende queue ab. Sobald eine Message vorhanden ist, wird diese ausgelesen und durch den Microservice in der zugehörigen Datenbank aktualisiert.
	
Mit Hilfe des curl Befehls "curl -X GET http://localhost:8100/backlogManagement/getUserStoryById/USERSTORYID" kann getestet werden, ob die "EstimationValue" in der Datenbank geändert wurde.
Auch hier muss der curl Befehl an der Stelle "USERSTROYID" mit eigenen Werten versehen werden.