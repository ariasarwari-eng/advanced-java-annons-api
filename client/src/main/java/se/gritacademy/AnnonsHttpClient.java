package se.gritacademy;


import se.gritacademy.server.model.Annons;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AnnonsHttpClient {


        // 1. HttpClient, fick göra den private static, kunde ej använda client i resten av metoderna?
        private static HttpClient client = HttpClient.newBuilder() // återanvänd client i varje metod
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();


    public static String listaAllaAnnonser(){ // metod för menyval 1
        try {

            //2. request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons"))
                    .GET()
                    .build();

            //3. response
            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid hämtning av annonser.";
        }
    }

    public static String visaAnnons (int id){ // måste den även vara integer här? krockar det nåstans?
        try{
            //2. request medn ändrad URI för att kunna hitta en specifik annons
            // i övrigt samma som ovan.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .GET()
                    .build();

            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid hämtning av annonser.";
        }
    }

    public String skapaAnnons(int id, String topic, String description, int price){

        try{
            // skapa body? här och ta in info från tangentbborf i main? måste ha samma format som i postman?
            String nyAnnons = "{"
                    +"\"id\":" + id +","
                    +"\"topic\":" + topic+","
                    +"\"description\":" + description + ","
                    +"\"price\":" + price
                    +"}";

            // 2. request, byt ut Get moy post?
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons"))
                    .POST(HttpRequest.BodyPublishers.ofString(nyAnnons)) // skicka data här, nyAnnon. HttpRequest.BodyPublishers, istället för handlers,klassen skapar innehållet, body
                    .build();


            //3. respons, vanlig som tidigare?
            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // få med status kod? 200 osv..
            return "Annons skapad";
            // var skickar jag med all info till annonsen?
        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid skapa annons.";
        }
    }

    public String changePrice (int id, int price){

        try{
            // vanlig get request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            ObjectMapper mapper = new ObjectMapper();
            Annons annons = mapper.readValue(
                    response.body(),
                    Annons.class
            );

            // ända pris med set?
            annons.setPrice(price);

            // annons -> json
            String uppdateradAnnons = mapper.writeValueAsString(annons);

            // put request, put används för att uppdatera.
            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(uppdateradAnnons))
                    .build();

            HttpResponse <String> putResponse = client.send(
                    putRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            return "Pris uppdaterad";

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid uppdatering av pris.";
        }

    }

    public String deleteAnnons(int id){
        try{

            // request med id, använd delete här, istället för get som tidigare gjorts.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .DELETE()
                    .build();

            // response
            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if(response.statusCode() == 200){
                return "Annons raderad.";
            } else if (response.statusCode()==404) {
                return "Annons hittas ej.";
            } else {
                return "Fel vid radering. Statuskod: "+ response.statusCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid radering av annons.";
        }
    }

}
