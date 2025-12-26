package se.gritacademy.server.database;

import se.gritacademy.server.model.Annons;

import java.util.ArrayList;
import java.util.List;

public class AnnonsDatabase {
    // ska skrivas efter singelton mönstret

    // Variabelnamnet instance enligt singelton. Vi vill att endast ett objekt av denna typ existerar.
    private static AnnonsDatabase instance;

    // Lista för att spara annonserna som objekt när programmet körs.
    private List<Annons> annonser = new ArrayList<>();

    // privat konstruktor
    private AnnonsDatabase (){}

    //Metod som skapar ett enda objekt, samma objekt returneras varje gång denna metod anropas.
    //Måste vara public för att kunna anropas från andra klasser. static funktion, anropa utan att skapa obj?
   // Syfte att ha endast en instans i hel mitt program.
    public static AnnonsDatabase getInstance(){ // metod namnet följer singelton mönstret
        if (instance==null){ // Kontrollerar om det redan finns en instans.
            instance = new AnnonsDatabase(); // Om inte, skapas en.
        }
        return instance;
    }

    //Metod för att lägga till annons obj i listan.
    public void addAnnons (Annons annons) {
        annonser.add(annons);
    }

    //metod som returnerar hela annons listan
    public List<Annons> getAnnons(){
        return annonser;
    }

    // kunna ta bort en annons, ska metoden definieras i denna klass? identifierra med id vilken som ska tas bort?
}
