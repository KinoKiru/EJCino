
public class Cards {

    int rank;
    int suit;
    String card;

    public Cards(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
        this.card = ("Sandbox/PNG-cards-1.3/"+rank+intToString(suit)+".png");
    }
    public String getCard(){

        return card;
    }


    public String intToString(int suit) {
        String letter = "";
        if (suit == 0) {
            letter = "C";
        } else if (suit == 1) {
            letter = "D";
        } else if (suit == 2) {
            letter = "H";
        } else if (suit == 3) {
            letter = "S";
        }
        return letter;
    }

}