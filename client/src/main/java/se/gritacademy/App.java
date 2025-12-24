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

        switch (val){
            case 1.

                break;
            case 2.
                break;
            case 3.
                break;
            case 4.
                break;
            case 5.
                break;
            default -> System.out.println("Ogilitgt val");
        }


    }
}
