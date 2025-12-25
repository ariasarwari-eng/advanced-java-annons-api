package se.gritacademy;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

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
            // skapa body? här och ta in info från tangentbborf i main?

            // 2. request, byt ut Get moy post?

            //3. respons, vanlig som tidigare?

            // var skickar jag med all info till annonsen?
        } catch (Exception e) {
            e.printStackTrace();
            return "Fel vid skapa annons.";
        }
    }

}
