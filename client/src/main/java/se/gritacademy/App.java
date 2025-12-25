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

            default : System.out.println("Ogilitgt val");
        }


    }
}
