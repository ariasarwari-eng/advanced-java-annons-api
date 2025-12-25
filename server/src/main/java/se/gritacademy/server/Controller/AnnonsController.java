package se.gritacademy.server.Controller;

import org.springframework.web.bind.annotation.*; // ändrade till * från restcontroller
import se.gritacademy.server.database.AnnonsDatabase;
import se.gritacademy.server.model.Annons;

import java.util.List;

@RestController // klassen är en SpringBoot Restcontroller. Klassen ska ta emot HTTP anrop (GET osv) sedan kunna skicka tbx data som JSON?
@RequestMapping("/api/annons") // ska den sluta med /? "grund-URL"
public class AnnonsController {
    // REST-API

    @GetMapping
    // ett HTTP GET anrop - ska returnera alla annonser från databasen.
    public List<Annons> getAnnons(){
        return AnnonsDatabase.getInstance().getAnnons(); // använder den ensa instansen som finns till databasen
    }

    @GetMapping("{id}") // get en specifik annons genom identifiering av id. Skicka in id via URL
    public Annons getAnnons (@PathVariable Integer id){ // (tar värdet från URL här)
        for (Annons annons : AnnonsDatabase.getInstance().getAnnons()){

            if (annons.getId() == id){
                return annons;
            }
        }
        return null; //om inget matchar.
    }

    @PostMapping // metoden ska köras id ett HTTP anrop POST
    public void addAnnons (@RequestBody Annons annons){ //@requestBody tar body oxh gör om till ett annons obj
        AnnonsDatabase.getInstance().addAnnons(annons); // lägger till objektet i listan.
    }

    @PutMapping ("{id}") // identifiera med id för att kunna uppdatera detta objekt
    public void updateAnnons (@RequestBody Annons annons, @PathVariable Integer id) { // req hämtar ny data från body, path hämtar id från url
        List <Annons> annonser = AnnonsDatabase.getInstance().getAnnons(); // hämtar hela listan fårn databasen med samma instans

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
