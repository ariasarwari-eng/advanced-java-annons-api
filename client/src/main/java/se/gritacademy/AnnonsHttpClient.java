package se.gritacademy;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class AnnonsHttpClient {

    public static void AnnonsClient(){

        try{
            // 1. skapa Http Client
            HttpClient client = HttpClient.newBuilder() // kan använda samma client (instans) i de olika metoderna för valen.
                    .version(HttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            //2. skapa en Http request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("länken här")) // koda en request för varje metod/ anrop? länken lär se annorlunda ur oxå
                    .GET()
                    .build();

            //3. Skicka request,

            //4. Använd svaret från request.

            //5. Skappa Jackson Object mapper

            //6. ?

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
