[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/cATxCFEq)
# README.MD

Här kan du dokumentera ditt projekt.

  - Projektets mål och funktionalitet:

    - Bygga en server som hanterar CRUD operationer via Http metoder.
    - Klienten kan hämta, skapa, uppdatera och ta bort annonser från servern med GET, POST, PUT och DELETE.

- Instruktioner för hur man startar servern och klientapplikationen:

    - Starta servern först (ServerApplication).
    - Därefter startar du klienten (App).
    - I konsolen får du upp en meny och kan göra ett val genom att ange en siffra.
    - Vill du göra ett nytt val behöver du trycka på run igen (run i App).

- Exempel på API-anrop:

  - För att hämta alla annonser: GET http://localhost:8080/api/annons
  - För att hämta en specifik annons (byt ut 1 mot rätt id): GET http://localhost:8080/api/annons/1
  - För att skapa en ny annons: POST http://localhost:8080/api/annons

Hade jag haft lite mer tid så hade jag gjort följande ändringar:

- Jag hade tänkt hålla mig till engelska vid namngivning av mina variabler men jag kom av mig flera gånger. 
    Det resulterade i att det har blivit lite blandat. Hade jag varit piggare eller haft mer tid så hade jag velat
    korrigera detta.
- Hade även velat loopa menyvalen så att man slipper trycka på run efter varje val man gjort och lagt in ett sjätte val
    för att kunna avbryta loopen/ avsluta programet.
- Samt ändrat phonenuber till en String eftersom noll i början på numret försvinner ://

*Disclaimer*
Jag började på mitt projekt ganska sent eftersom att jag blev sjuk. Är ganska seg i huvet nu när jag försöker avlsuta
projektet och väljer att lämna in den som den är medans den fortfarande funkar :)