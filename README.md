# Spring Boot Kotlin FileServer Exercise

Das Ziel der Aufgabe ist ein simpler File-Server, mit folgenden Kriterien:

- [x] Das Projekt ist mit Spring-Boot umgesetzt
- [x] Der Code (abgesehen von Konfigurationsdateien) ist in Kotlin oder Java geschrieben
- [x] Clients können Dateien hochladen
    - POST Request nach /file/, Datei kann mit
    beispielsweise Postman mittels form-data request mit dem key "file" hochgeladen werden
- [x] Clients können eine Liste aller hochgeladenen Dateien abrufen
    - GET Request nach /files/, Antwort ist JSON Liste aus JSON Objekten die Dateinamen und Tags beinhalten
- [x] Clients können einzelne Dateien herunterladen
    - GET Request nach /file/{filename}, Pfadparameter filename ist der name der herunterzuladenden Datei.
    Kann z.b mit Postman heruntergeladen werden.
- [x] Den Dateien können Metadaten in Form von Tags hinzugefügt werden
    - POST Request nach /file/tags, JSON Body als Beispiel wie folgt:
    ```
    {
    	"fileName" : "test.jpg",
    	"tags" : ["Hallo", "Test"]
    }
    ```
- [x] Bei der Auflistung der Dateien kann anhand der Tags gefiltert werden
    - GET Request nach /files/{tag}, tag ist der Name des Tags für den Dateien angezeigt werden.
- [x] Dateien und Tags sind persistent gespeichert
    - Dateien werden auf lokalem Dateisystem des Servers hinterlegt. Für die Datenbank ist eine H2 In-Memory DB
    mit File Backend konfiguriert, so das das Projekt ohne eine externe Datenbank
    gleich ausprobiert werden kann.
- [x] Alle Endpunkte sind mit einem fixen Authentication-Header-Token (x-api-key) abgesichert
    - Header ist "x-api-key" und token its "fitness"

## Übersicht Aufbau

Entworfen nach einer einfachen Adaption der "Clean Architecture",
hauptsächlich wird auf Einhaltung der SOLID Prinzipien geachtet.
Teilung der Verwantwortungen in klar definierte Schichten, lose gekoppelt nach dem Prinzip der Dependcy Inversion, funktioniert hervorragend
in Spring Boot da vieles über Dependency Injection zusammengeseteckt wird und
der Code sollte einfach testbar und Komponenten austauschabr sein, da nur gegen Schnittstellen implementiert wird.



#### Controller (FileController)

einfach Spring Rest Controller, der die Konfiguration der Rest Endpunkte beinhaltet und den Inhalt der Requests
and die entsprechende Service Methode weiterleitet

#### Service/Domain (FileService / DefaultFileService)

Service Schicht die die Domain Use Cases und Interaktion zwischen Domain Objekten implementiert
und mit Repositories interagiert

#### Repository / Storage

Schnittstellen und Implementierungen um mit Datenhaltungssystemen zu interagieren in diesem Fall
mit der Datenbanken und dem lokalen Dateinsystem des Servers.



## TODO

- [ ] Filtern nach mehreren Tags
- [ ] Wenn der api key header nicht gesetzt ist gibt es eine IllegalStateException, ich hätte lieber ein 405 Not Authorized
- [ ] Momentan können Tags nur angehangen werden, ändere so das -> POST: tags überschreiben, PUT: tags hinzufügen
- [ ] Beim hinzufügen von Tags prüfe ich nicht ob es die angegebene Datei im Dateisystem wirklich gibt..
- [ ] Tests!
- [ ] Nicht ganz sicher ob alle Funktionen zu File Metadaten in den FileController / FileService gehören oder man das evtl. auch trennen sollte.. für die übung lass ich es so, aber ich bin nicht ganz zufrieden.