# Spring Boot Kotlin FileServer Exercise

Das Ziel der Aufgabe ist ein simpler File-Server, mit folgenden Kriterien:

- [x] Das Projekt ist mit Spring-Boot umgesetzt
- [x] Der Code (abgesehen von Konfigurationsdateien) ist in Kotlin oder Java geschrieben
- [x] Clients können Dateien hochladen
- [x] Clients können eine Liste aller hochgeladenen Dateien abrufen
- [x] Clients können einzelne Dateien herunterladen
- [x] Den Dateien können Metadaten in Form von Tags hinzugefügt werden
- [x] Bei der Auflistung der Dateien kann anhand der Tags gefiltert werden
- [x] Dateien und Tags sind persistent gespeichert
- [x] Alle Endpunkte sind mit einem fixen Authentication-Header-Token (x-api-key) abgesichert

## TODO

- [ ] Wenn der api key header nicht gesetzt ist gibt es eine IllegalStateException, ich hätte lieber ein 405 Not Authorized
- [ ] Tests