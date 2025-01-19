import java.util.ArrayList;

public class Rules {
    DeckValue deck = new DeckValue();
    int highValue = 0;

    public boolean playerHighCard(ArrayList<Integer> playerValues, ArrayList<Integer> computerValues) {
        int highestValuePlayer = 0;
        int highestValue = 0;
        boolean highCard = false;
        for (int i = 0; i < playerValues.size(); i++) {
            if(playerValues.get(i) == 1){
                playerValues.remove(i);
                playerValues.add(14);
            }

        }for (int i = 0; i < computerValues.size(); i++) {
            if(computerValues.get(i) == 1){
                computerValues.remove(i);
                computerValues.add(14);
            }

        }
        for (int i = 0; i < playerValues.size(); i++) {
            if (highestValuePlayer < playerValues.get(i)) {
                highestValuePlayer = playerValues.get(i);
            }
        }
        for (int i = 0; i < computerValues.size(); i++) {
            if (highestValue < computerValues.get(i)) {
                highestValue = computerValues.get(i);
            }
        }
        if (highestValuePlayer > highestValue) {
            highCard = true;
        }

        return highCard;
    }

    public boolean computerHighCard(ArrayList<Integer> playerValues, ArrayList<Integer> computerValues) {
        int highestValuePlayer = 0;
        int highestValue = 0;
        boolean highCard = false;
        for (int i = 0; i < playerValues.size(); i++) {
            if(playerValues.get(i) == 1){
                playerValues.remove(i);
                playerValues.add(14);
            }

        }for (int i = 0; i < computerValues.size(); i++) {
            if(computerValues.get(i) == 1){
                computerValues.remove(i);
                computerValues.add(14);
            }

        }
        for (int i = 0; i < playerValues.size(); i++) {
            if (highestValuePlayer < playerValues.get(i)) {
                highestValuePlayer = playerValues.get(i);
            }
        }
        for (int i = 0; i < computerValues.size(); i++) {
            if (highestValue < computerValues.get(i)) {
                highestValue = computerValues.get(i);
            }
        }
        if (highestValuePlayer < highestValue) {
            highCard = true;
        }

        return highCard;
    }

    public boolean onePair(ArrayList<Integer> playerValues) {
        boolean pair = false;
        for (int i = 1; i < playerValues.size(); i++) {

            if (playerValues.get(0) == playerValues.get(i)) {
                pair = true;
                break;
            }
        }
        for (int i = 2; i < playerValues.size(); i++) {
            if (playerValues.get(1) == playerValues.get(i)) {
                pair = true;
                break;
            }
        }


        return pair;
    }

    public boolean twoPair(ArrayList<Integer> playerValues) {
        boolean twoPair = false;
        boolean onePair = false;
        for (int i = 2; i < playerValues.size(); i++) {
            if (playerValues.get(0) == playerValues.get(i)) {
                onePair = true;
                break;
            }
        }
        if (onePair) {
            for (int i = 2; i < playerValues.size(); i++) {
                if (playerValues.get(1) == playerValues.get(i)) {
                    twoPair = true;
                }
            }
        }
        return twoPair;
    }

    public boolean threeOfaKind(ArrayList<Integer> playerValues) {
        boolean threeOfAKind = false;
        int amount = 0;
        for (int i = 0 ; i < 2; i++) {
            for (int j = 1+i; j < playerValues.size() - i; j++) {
                if (playerValues.get(i) == playerValues.get(j)) {
                    amount++;
                }
            }
        }
        if(amount == 3){
            threeOfAKind = true;
        }
        return threeOfAKind;
    }

    public boolean straight(ArrayList<Integer> playerValues) {
        boolean straight = false;
        int straights = 0;
        int amount = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < playerValues.size(); j++) {
                if (playerValues.get(amount) == playerValues.get(j) + 1) {
                    amount = j;
                    straights++;
                }
            }
        }
        amount = 0;
        if(straights!= 5){
            for (int j = 0; j < playerValues.size(); j++) {
                if( playerValues.get(amount) == playerValues.get(j)-1){
                    straights++;
                }
            }
        }
        if(straights>=5){
            straight = true;
        }
        return straight;
    }

    public boolean fourOfaKind(ArrayList<Integer> playerValues) {

        int amount = 1;
        boolean fourOfaKind = false;
        for (int i = 1; i < playerValues.size(); i++) {
            if (playerValues.get(0) == playerValues.get(i)) {
                amount++;
            }
        }
        if (amount < 4) {
            amount = 0;
            for (int i = 1; i < playerValues.size() - 1; i++) {
                if (playerValues.get(1) == playerValues.get(i + 1)) {
                    amount++;
                }
            }
        }
        if (amount == 4) {
            fourOfaKind = true;
        }
        return fourOfaKind;
    }

    public boolean flush(ArrayList<String> playerSuit) {
        int amount = 0;
        boolean flush = false;
        for (int i = 1; i < playerSuit.size(); i++) {
            if (playerSuit.get(0).equalsIgnoreCase(playerSuit.get(i))) {
                amount++;
            }
        }
        if (amount <5   ) {
            amount = 0;
            for (int i = 2; i < playerSuit.size(); i++) {
                if (playerSuit.get(1).equalsIgnoreCase(playerSuit.get(i))) {
                    amount++;
                }
            }
        }
        if (amount >= 5) {
            flush = true;
        }
        return flush;
    }

    public boolean fullHouse(ArrayList<Integer> playerValues) {

        int amount = 0;
        int amount2 = 0;
        boolean fullHouse = false;
        boolean twopair = false;
        boolean threeofakind = false;

        for (int i = 2; i < playerValues.size(); i++) {
            if (playerValues.get(0) == playerValues.get(i)) {
                amount++;
            }
            if(amount == 2){
                twopair = true;
            }
            else if(amount == 3){
                threeofakind = true;
            }
        }
        if (threeofakind || twopair) {
            for (int i = 2; i < playerValues.size(); i++) {
                if (playerValues.get(1) == playerValues.get(i)) {
                    amount2++;
                }
                if ( amount2 == 2){
                    twopair = true;
                }
                else if (amount2 == 3){
                    threeofakind = true;
                }
            }
        }

        if (threeofakind && twopair) {
            fullHouse = true;
        }

        return fullHouse;
    }

    public boolean straightFlush(ArrayList<Integer> playerValues, ArrayList<String> playerSuit) {
        boolean straightFlush = false;
        boolean flush = false;
        int amount = 0;
        for (int i = 1; i < playerSuit.size(); i++) {
            if (playerSuit.get(0).equalsIgnoreCase(playerSuit.get(i))) {
                amount++;
            }
        }
        for (int i = 2; i < playerSuit.size(); i++) {
            if (playerSuit.get(1).equalsIgnoreCase(playerSuit.get(i))) {
                amount++;
            }
        }
        if (amount == 5) {
            flush = true;

        }
        boolean straight = true;


        for (int i = 1; i < playerValues.size(); i++) {
            if (playerValues.get(i) != playerValues.get(i - 1) + 1) {
                straight = false;
                break;
            }
        }

        if (flush && straight) {
            straightFlush = true;
        }
        return straightFlush;

    }

    public boolean royalFlush(ArrayList<Integer> playerValues, ArrayList<String> playerSuit) {
        boolean flush = false;
        boolean royal = false;
        boolean royalFlush = false;
        int amount = 0;
        int royalAmount = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < playerValues.size(); j++) {
                if (playerValues.get(j) == 10 + amount) {
                    royalAmount++;
                }
                if(royalAmount<amount){
                    amount++;

                }
            }
        }
        for (int i = 0; i < playerValues.size(); i++) {
            if(playerValues.get(i) == 1 && royalAmount !=1){
                royalAmount++;
            }

        }
        int amount1 = 0;
        for (int i = 1; i < playerSuit.size(); i++) {
            if (playerSuit.get(0).equalsIgnoreCase(playerSuit.get(i))) {
                amount1++;
            }
        }
        for (int i = 2; i < playerSuit.size(); i++) {
            if (playerSuit.get(1).equalsIgnoreCase(playerSuit.get(i))) {
                amount1++;
            }
        }
        if (amount1 >= 5) {
            flush = true;
        }
        if (flush && royal){
            royalFlush = true;
        }
        return royalFlush;
    }

}