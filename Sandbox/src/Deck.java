import nl.saxion.app.SaxionApp;

import java.util.ArrayList;

public class Deck {
    ArrayList<Cards> cards = createDeckOfCards();


    public Cards removeCards(){
        Cards cards1 = cards.get(SaxionApp.getRandomValueBetween(0, 52));
        int remove = (cards1.rank+(cards1.suit*13));
        System.out.println(cards1.rank);
        System.out.println(cards1.intToString(cards1.suit));
        System.out.println(remove);
        cards.remove(remove-1);
        return cards1;
    }

    @Override
    public String toString() {
        return
                "Sandbox/PNG-cards-1.3/"+cards+".png";
    }

    public ArrayList<Cards> createDeckOfCards() {
        ArrayList<Cards> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int cardValue = 1;
            while (cardValue < 14) {
                Cards newcards = new Cards(cardValue, i);
                result.add(newcards);
                cardValue++;
            }
        }
        return result;
    }
}