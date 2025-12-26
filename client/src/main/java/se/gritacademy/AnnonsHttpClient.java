package se.gritacademy;


import se.gritacademy.server.model.Annons;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AnnonsHttpClient {


        // 1. HttpClient, fick göra den private static då jag ej kunde använda client i resten av metoderna.
        private static HttpClient client = HttpClient.newBuilder() // Återanvänder client i varje metod.
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10)) // max 10sek timeout
                .build();

    //Menyval 1.
    public String listaAllaAnnonser(){
        try {

            //2. request
            // Skapar en förfrågan på alla annonser som finns.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons"))
                    .GET()
                    .build();

            //3. response
            //Skickar förfrågan, omvandlar sedan svaret från json till String
            HttpResponse <String> response = client.send( // viktigt att <String> finns med då länken är en String.
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            //
            if (response.statusCode()==200){
                return response.body();
            } else if (response.statusCode()==404) {
                return "Kunde ej hitta listan";
            }else {
                return "Ett fel inträffade. Statuskod: "+ response.statusCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid hämtning av annonser.";
        }
    }

    //Menyval 2.
    public  String visaAnnons (int id){
        try{
            //2. Request som tidigare men med ändrad URI för att kunna hitta en specifik annons.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .GET()
                    .build();

            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            if(response.statusCode()==200){
                return response.body();
            } else if (response.statusCode()==404) {
                return "Annons kunde ej hittas";
            }else {
                return "Ett fel inträffde. Statuskod: "+response.statusCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid hämtning av annonser.";
        }
    }

    //Menyval 3.
    public String skapaAnnons(int id, String topic, int phonenumber, String description, int price){

        try{
            Annons annons = new Annons();
            annons.setId(id);
            annons.setTopic(topic);
            annons.setPhonenumber(phonenumber);
            annons.setDescription(description);
            annons.setPrice(price);

            //omvandla obj till json
            ObjectMapper mapper= new ObjectMapper();
            String nyAnnons = mapper.writeValueAsString(annons);

            // 2. request, bytt ut GET mot POST för att skicka data och skapa en ny annons.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(nyAnnons)) // Skickar data här->annons.
                    //HttpRequest.BodyPublishers -> istället för handlers, klassen skapar innehållet,body.
                    .build();


            //3. Respons
            HttpResponse <String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
                if(response.statusCode()==200){
                    return "Annons skapad!";
                } else {
                    return "Ett fel inträffade. Statuskod: "+response.statusCode();
                }

        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid skapa annons.";
        }
    }

    //Menyval 4.
    public String changePrice (int id, int price){

        try{
            // Request med GET för att hämta info för den specifika annonsen.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .GET()
                    .build();

            // Respons
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Skapar Jackon Object mapper
            ObjectMapper mapper = new ObjectMapper();
            // Skapar Annons objekt
            Annons annons = mapper.readValue(
                    response.body(), // json datan läses
                    Annons.class // mappar json fält till klassens attribut.
            );
            // Jag sparar respons/svaret i ett objekt.

            // Ändrar priset på objektet med set.
            annons.setPrice(price);

            // annons -> json
            // Omvandlar objektet annons till json
            String uppdateradAnnons = mapper.writeValueAsString(annons);

            // put request, put används för att uppdatera.
            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/annons/"+id))
                    .header("Content-Type", "application/json") // tala om för server att innhållet är i json format.
                    .PUT(HttpRequest.BodyPublishers.ofString(uppdateradAnnons))// skickar med data i rätt format med publisher metoden.
                    .build();

            HttpResponse <String> putResponse = client.send(
                    putRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode()==200){
                return "Pris uppdaterad";
            } else if (response.statusCode()==404) {
                return "Annons med angivet id kan ej hittas.";
            }else {
                return "Ett fel inträffade. Statuskod: "+response.statusCode();
            }

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
