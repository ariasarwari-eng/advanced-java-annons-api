package se.gritacademy.server.Controller;

import org.springframework.web.bind.annotation.*; // Ändrade till * från restcontroller.
import se.gritacademy.server.database.AnnonsDatabase;
import se.gritacademy.server.model.Annons;

import java.util.List;

// Markerar klassen som en SpringBoot Restcontroller. Klassen ska ta emot HTTP anrop (GET osv) sedan kunna skicka tbx data som JSON.
@RestController

@RequestMapping("/api/annons") //grund-URL
public class AnnonsController {
    // REST-API

    @GetMapping
    // Ett HTTP GET anrop - ska returnera alla annonser från databasen.
    public List<Annons> getAnnons(){ // En lista bestående av Annons objekt.
        return AnnonsDatabase.getInstance().getAnnons(); // Använder den enda instansen som finns till databasen.
    }

    @GetMapping("{id}") // GET en specifik annons genom identifiering av id. Skickar in id via URL.
    public Annons getAnnons (@PathVariable Integer id){ // (tar värdet från URL här)
        for (Annons annons : AnnonsDatabase.getInstance().getAnnons()){ // Loopar igenom objekten i listan.

            if (annons.getId() == id){ // jämför id på objektet med det angivna id i URL.
                return annons; // Returnerar det objekt som matchar.
            }
        }
        return null; //Om inget matchar.
    }

    @PostMapping // Metoden ska köras vid ett Post anrop.
    public void addAnnons (@RequestBody Annons annons){ //@requestBody tar body och gör om till ett annons objekt.
        AnnonsDatabase.getInstance().addAnnons(annons); // lägger till objektet i listan.
    }

    @PutMapping ("{id}") // Identifiera med id för att kunna uppdatera ett objekt.
    // requestBody hämtar ny data från body, path hämtar id från url
    public void updateAnnons (@RequestBody Annons annons, @PathVariable Integer id) {
        List <Annons> annonser = AnnonsDatabase.getInstance().getAnnons(); // hämtar hela listan från databasen med samma instans

        for (int i = 0 ; i< annonser.size() ; i++){ // loopar igenom listan mha index
            Annons a = annonser.get(i); // hämtar annonens med index i
            if (a.getId() == id){ // jämför id för denna annons (i) med id vi letar efter. Matchar eller ej?
                annonser.set(i, annons); // om det matcher ersätts annonsen med den som hämtats från body.
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteAnnons (@PathVariable Integer id){ // tar emot id via URL
        List<Annons> annonser = AnnonsDatabase.getInstance().getAnnons(); // hämtar listan

        for (Annons a : annonser) { // loopar igenom listan
            if (a.getId()== id){ // jämför det hämtade id via url med listan
                annonser.remove(a); // om det matchar tas den bort
                return; // tar endast bort vid första matchning
            }
        }
    }
}
