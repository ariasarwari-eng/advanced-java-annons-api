# Annons-API

Java-projekt med en REST-server och klient för hantering av annonser.

## Funktioner

- Hämta annonser
- Skapa annonser
- Uppdatera annonser
- Ta bort annonser
- Kommunikation mellan klient och server via REST API

## Teknik

- Java
- Spring Boot
- REST API
- Maven
- Jackson / ObjectMapper

## Starta projektet

1. Starta `ServerApplication`.
2. Starta därefter `App`.
3. Välj önskad funktion genom att ange ett menyval i konsolen.

## API

Exempel på anrop:

```text
GET    /api/annons
GET    /api/annons/{id}
POST   /api/annons
PUT    /api/annons/{id}
DELETE /api/annons/{id}
