package se.gritacademy;

import java.util.Scanner;

public class App {

    public static void main( String[] args ) {

        System.out.println("""
                1. Lista alla annonser
                2. Visa annons
                3. Skapa annons
                4. Ändra pris
                5. Radera annons
                """);

        System.out.println("Val: ");

        Scanner input = new Scanner(System.in);
        int val = input.nextInt();

        AnnonsHttpClient client = new AnnonsHttpClient();

        switch (val){
            case 1 :
                String allaAnnonser= client.listaAllaAnnonser();
                System.out.println(allaAnnonser);
                break;
            case 2 :
                System.out.println("Ange annonsens id: ");
                int id = input.nextInt();

                String enAnnons = client.visaAnnons(id);
                System.out.println(enAnnons);
                break;
            case 3:
                System.out.println("Ange id: ");
                int newId = input.nextInt();

                System.out.println("Ämne/kategori: ");
                String topic = input.nextLine();

                System.out.println("Beskrvning av produkt: ");
                String description = input.nextLine();

                System.out.println("Pris: ");
                int price = input.nextInt();

                String skickaData = client.skapaAnnons(newId, topic, description, price);
                System.out.println(skickaData);
                break;
            case 4:
                System.out.println("Ange id på den annons vars pris du vill ändra: ");
                int idPriceChange = input.nextInt();

                System.out.println("Ange det nya priset: ");
                int newPrice = input.nextInt();
                break;
            case 5:
                System.out.println("Ange id på annonsen du vill radera: ");
                int idRadera = input.nextInt();
            default : System.out.println("Ogilitgt val");
        }


    }
}
