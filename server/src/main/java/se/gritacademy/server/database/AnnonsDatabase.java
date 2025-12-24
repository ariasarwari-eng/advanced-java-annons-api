package se.gritacademy.server.database;

import se.gritacademy.server.model.Annons;

import java.util.ArrayList;
import java.util.List;

public class AnnonsDatabase {
    // ska skrivas efter singelton mönstret

    private static AnnonsDatabase instance; // variabelnamnet instance enligt singelton? kan endast användas i denna klass? Vi vill att endast ett objekt av denna typ existerar.

    private List<Annons> annonser = new ArrayList<>(); // lista för att spara annonserna i när programmet körs.

    // privat konstruktor
    private AnnonsDatabase (){}

    //metod som skapar ett enda objekt, samma objekt returneras varje gågn denna metod anropas.
    //´nu måste den vara public för att kunna anropas fårn andra klasser, static funktion, anropa utan att skapa obj??
   // syfte att ha endast en intsans i hel mitt programm.
    public static AnnonsDatabase getInstance(){ // metod namnet följer singelton mönstret
        if (instance==null){ // kontrollera om det redan finns en instans?
            instance = new AnnonsDatabase(); // om inte, skapa en
        }
        return instance;
    }

    // metod för att lägga till annons obj i listan.
    public void addAnnons (Annons annons) {
        annonser.add(annons);
    }

    //metod som returnerar hela annons listan
    public List<Annons> getAnnons(){
        return annonser;
    }

    // kunna ta bort en annons, ska metoden definieras i denna klass? identifierra med id vilken som ska tas bort?
}
