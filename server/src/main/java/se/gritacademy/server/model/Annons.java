package se.gritacademy.server.model;

public class Annons {
// POJO ska beskriva hur jag vill att annonsen ser ut

    // definiera alla variabler som en annons kräver, välj lämpliga namn, allt på eng? privata variabler!
    private int id; // eller integer?
    private String topic; //ämnesrad på eng?
    private String description;
    private int price; // integer?

    // en tom konstruktor, krävs av Spring när detta objekt skapas från JSON?
    public Annons (){}

    // kontruktor med parametrar, this- parametrar som skickas in att ska sättas som variabler
    public Annons ( int id, String topic, String description, int price){
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.price = price;
    }

    // generera getters och setters för variablerna
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
