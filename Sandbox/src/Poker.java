import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class Poker extends Screen {
    boolean start = true;
    DeckValue deckValue = new DeckValue();
    DeckSuit deckSuit = new DeckSuit();
    boolean pressed = false;
    boolean raised = false;
    boolean help = false;
    boolean folded = false;
    boolean computerFold = false;
    Deck deck = new Deck();
    int removeStartScreen = 0;
    int betTimes = 0;
    int cardAmount = 0;
    int amount = 0;
    int betAmount = 0;
    int increaseBetAmount = 0;
    int betAmountComputer1 = 0;
    int finish = 0;

    int totalBetAmount = 0;
    int playerPointAmount = 0;
    int computerPointAmount1 = 0;
    ArrayList<Integer> computerCards = new ArrayList<>();
    ArrayList<Integer> playerCardValues = new ArrayList<>();
    ArrayList<Integer> playerStarterCardValues = new ArrayList<>();
    ArrayList<String> playerCardSuits = new ArrayList<>();
    ArrayList<Integer> computerCardValues1 = new ArrayList<>();
    ArrayList<Integer> computerStarterCardValues1 = new ArrayList<>();
    ArrayList<String> computerCardSuits1 = new ArrayList<>();
    Rules rules = new Rules();
    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        int randomCard;

        if (mouseEvent.isLeftMouseButton()) {
            if (mouseEvent.isMouseUp()) {

                Raise(mouseEvent, application);
                if (mouseEvent.getX() > 725 && mouseEvent.getX() < 800 && mouseEvent.getY() > 720 && mouseEvent.getY() < 790 && pressed) {
                    betTimes++;
                    if (betAmount > betAmountComputer1 && !computerFold) {
                        betAmountComputer1 = betAmount;
                    } else if (!computerFold && betAmountComputer1 > betAmount) {
                        betAmount = betAmountComputer1;
                    }

                    if (betTimes == 1) {
                        amount++;
                        drawCard(cardAmount);
                        cardAmount++;

                        drawCard(cardAmount);
                        cardAmount++;

                        drawCard(cardAmount);
                        cardAmount++;
                        totalBetAmount = totalBetAmount + betAmount + betAmountComputer1;
                        betAmount = 0;
                        betAmountComputer1 = 0;

                    }
                    if (betTimes == 2) {
                        amount++;
                        drawCard(cardAmount);
                        cardAmount++;
                        totalBetAmount = totalBetAmount + betAmount + betAmountComputer1;
                        betAmount = 0;
                        betAmountComputer1 = 0;
                    }
                    if (betTimes == 3) {
                        amount++;
                        drawCard(cardAmount);
                        totalBetAmount = totalBetAmount + betAmount + betAmountComputer1;
                        betAmount = 0;
                        betAmountComputer1 = 0;
                    }
                    if (betTimes == 4) {
                        amount++;
                        totalBetAmount = totalBetAmount + betAmount + betAmountComputer1;
                        betAmount = 0;
                        betAmountComputer1 = 0;
                    }

                }
                if ((mouseEvent.getX() > 620 && mouseEvent.getX() < 700 && mouseEvent.getY() > 715 && mouseEvent.getY() < 790 && pressed) ||
                        (mouseEvent.getX() > 100 && mouseEvent.getX() < 325 && mouseEvent.getY() > 715 && mouseEvent.getY() < 765 && pressed && (betTimes == 4 || computerFold))) {

                    SaxionApp.clear();
                    cardAmount = 0;
                    pressed = false;
                    betTimes = 0;
                    clearArray();
                    deck = new Deck();
                    deckValue = new DeckValue();
                    deckSuit = new DeckSuit();
                    betAmount = 0;
                    amount++;
                    folded = true;
                    removeStartScreen = 0;
                    computerFold = false;
                    

                }


                if (mouseEvent.getX() > 150 && mouseEvent.getX() < 750 && mouseEvent.getY() > 250 && mouseEvent.getY() < 350 && !pressed) {
                    for (int i = 0; i < 2; i++) {
                        randomCard = SaxionApp.getRandomValueBetween(0, deck.cards.size() - 1);
                        SaxionApp.drawImage(deck.cards.get(randomCard).getCard(), 350 + (100 * i), 600, 100, 150);
                        deck.cards.remove(randomCard);
                        playerCardSuits.add(deckSuit.cardSuit.get(randomCard));
                        deckSuit.cardSuit.remove(randomCard);
                        playerCardValues.add(deckValue.cardsValue.get(randomCard));
                        playerStarterCardValues.add(deckValue.cardsValue.get(randomCard));
                        deckValue.cardsValue.remove(randomCard);
                        
                    }
                    for (int i = 0; i < 2; i++) {
                        randomCard = SaxionApp.getRandomValueBetween(0, deck.cards.size() - 1);
                        computerCards.add(randomCard);
                        SaxionApp.drawImage("Sandbox/Achterkant-Kaart/download.png", 750 + (100 * i), 350, 100, 150);
                        computerCardSuits1.add(deckSuit.cardSuit.get(randomCard));
                        computerCardValues1.add(deckValue.cardsValue.get(randomCard));
                        computerStarterCardValues1.add(deckValue.cardsValue.get(randomCard));
                        deck.cards.remove(randomCard);
                        deckSuit.cardSuit.remove(randomCard);
                        deckValue.cardsValue.remove(randomCard);

                    }


                    pressed = true;
                    folded = false;
                }
                if (mouseEvent.getX() > 150 && mouseEvent.getX() < 750 && mouseEvent.getY() > 450 && mouseEvent.getY() < 550 && !pressed) {
                    SaxionApp.clear();
                    help = true;

                }
                if (mouseEvent.getX() > 800 && mouseEvent.getX() < 850 && mouseEvent.getY() > 700 && mouseEvent.getY() < 750 && !pressed) {
                    help = false;
                    SaxionApp.clear();
                }

            }
        }
    }

    @Override
    public void run() {
        if(start){
            SaxionApp.clear();
            start = false;
        }
        Application application = new Application();
        computerRound1();
        round2Computer();
        computerRound3();
        computerRound4();
        if ((pressed && betTimes == 4) || computerFold) {
            SaxionApp.setFill(Color.cyan);
            SaxionApp.drawRectangle(100, 715, 225, 50);
            SaxionApp.drawText("play again?", 125, 725, 35);
        }
        if (computerFold) {
            betAmountComputer1 = 0;
        }
        if (pressed) {
            SaxionApp.drawText(String.valueOf(betAmount), 440, 550, 25);
        }
        if (pressed) {
            SaxionApp.drawText(String.valueOf(totalBetAmount), 500, 50, 35);
        }
        if (pressed) {
            SaxionApp.drawText(String.valueOf(betAmountComputer1), 700, 450, 25);
        }
        if (pressed && raised) {
            SaxionApp.drawText(String.valueOf(increaseBetAmount), 740, 550, 25);
        }
        SaxionApp.drawText(String.valueOf(application.money), 900, 50, 35);
        if (betTimes != 3 && pressed) {
            SaxionApp.setFill(Color.RED);
            SaxionApp.drawCircle(760, 750, 37);
            SaxionApp.drawText("Call", 740, 740, 25);
        }
        if (betTimes != 3 && pressed) {
            SaxionApp.setFill(Color.RED);
            SaxionApp.drawCircle(860, 750, 37);
            SaxionApp.drawText("Raise", 830, 740, 25);
        }
        if (betTimes != 3 && pressed && raised) {
            SaxionApp.setFill(Color.RED);
            SaxionApp.drawCircle(860, 675, 25);
            SaxionApp.drawText("25", 845, 665, 25);
            SaxionApp.drawCircle(860, 600, 25);
            SaxionApp.drawText("50", 845, 590, 25);
            SaxionApp.drawCircle(860, 525, 25);
            SaxionApp.drawText("100", 835, 515, 25);


            SaxionApp.drawCircle(950, 675, 25);
            SaxionApp.drawText("-25", 935, 665, 25);
            SaxionApp.drawCircle(950, 600, 25);
            SaxionApp.drawText("-50", 935, 590, 25);
            SaxionApp.drawCircle(950, 525, 25);
            SaxionApp.drawText("-100", 925, 515, 25);
            SaxionApp.drawCircle(950, 750, 37);
            SaxionApp.drawText("Confirm", 917, 740, 20);
        }
        if (betTimes != 3 && pressed) {
            SaxionApp.setFill(Color.RED);
            SaxionApp.drawCircle(660, 750, 37);
            SaxionApp.drawText("Fold", 635, 740, 25);

        }

        if (!pressed && !help) {
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.CYAN);
            SaxionApp.drawRectangle(250, 250, 500, 100);

            SaxionApp.drawText("Start", 450, 285, 35);
        } else {
            if (removeStartScreen == 0) {
                SaxionApp.setFill(Color.getHSBColor(0, 0, 0.137f));
                SaxionApp.turnBorderOff();
                SaxionApp.drawRectangle(250, 250, 500, 100);
            }
        }
        if (!pressed && !help) {
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.CYAN);
            SaxionApp.drawRectangle(250, 450, 500, 100);
            SaxionApp.drawText("Help", 450, 485, 35);

        } else {
            if (removeStartScreen == 0) {
                SaxionApp.setFill(Color.getHSBColor(0, 0, 0.137f));
                SaxionApp.turnBorderOff();
                SaxionApp.drawRectangle(250, 450, 500, 100);
                removeStartScreen++;
            }

        }
        if (help) {

            SaxionApp.drawImage("BasicGame/GameRules/poker-spelregels.png", 0, 0, 1000, 800);
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.CYAN);
            SaxionApp.drawRectangle(800, 700, 50, 50);
            SaxionApp.drawText("quit", 805, 710, 25);
        }
        if (betTimes == 4) {
            computerPointAmount1 = computerScoreCheck(computerCardValues1, computerCardSuits1, computerStarterCardValues1, playerStarterCardValues);
            playerPointAmount = playerScoreCheck(playerCardValues, playerCardSuits, playerStarterCardValues, computerStarterCardValues1);
        }
        if (computerPointAmount1 > playerPointAmount && !computerFold && betTimes == 4) {
            SaxionApp.drawText("You lost!", 150, 300, 150);
            totalBetAmount = 0;
        }
        if ((computerPointAmount1 < playerPointAmount && finish == 0 && betTimes == 4) || computerFold && finish == 0) {
            SaxionApp.drawText("You Won!", 150, 340, 150);
            for (int i = 0; i < computerCards.size(); i++) {
                SaxionApp.drawImage(deck.cards.get(computerCards.get(i)).getCard(), 750 + (100 * i), 350, 100, 150);
            }
            finish++;
        }
        if(finish==1){
            application.money+=totalBetAmount;
            finish++;
        }

        if (amount == 1) {
            SaxionApp.setFill(Color.getHSBColor(0, 0, 0.137f));
            SaxionApp.drawRectangle(440, 550, 60, 20);
            amount = 0;

            SaxionApp.setFill(Color.getHSBColor(0, 0, 0.137f));
            SaxionApp.drawRectangle(740, 550, 75, 20);
            amount = 0;

            SaxionApp.drawRectangle(410, 565, 350, 25);
            SaxionApp.drawRectangle(900, 40, 350, 50);
            SaxionApp.drawRectangle(500, 50, 350, 50);
            SaxionApp.drawRectangle(700, 450, 50, 50);

        }
        if (!raised) {
            SaxionApp.setFill(Color.getHSBColor(0, 0, 0.137f));
            SaxionApp.drawRectangle(835, 500, 150, 200);
        }

    }

    @Override
    public void init(Application application) {
    }

    private void Raise(MouseEvent mouseEvent,Application application) {
        if (mouseEvent.getX() > 820 && mouseEvent.getX() < 900 && mouseEvent.getY() > 720 && mouseEvent.getY() < 790 && pressed) {
            raised = true;

        }
        if (mouseEvent.getX() > 835 && mouseEvent.getX() < 885 && mouseEvent.getY() > 650 && mouseEvent.getY() < 700 && pressed && raised) {
            increaseBetAmount = increaseBetAmount + 25;
            amount++;

        }
        if (mouseEvent.getX() > 835 && mouseEvent.getX() < 885 && mouseEvent.getY() > 575 && mouseEvent.getY() < 625 && pressed && raised) {
            increaseBetAmount = increaseBetAmount + 50;
            amount++;
        }
        if (mouseEvent.getX() > 835 && mouseEvent.getX() < 885 && mouseEvent.getY() > 500 && mouseEvent.getY() < 550 && pressed && raised) {
            increaseBetAmount = increaseBetAmount + 100;
            amount++;
        }
        if (mouseEvent.getX() > 925 && mouseEvent.getX() < 975 && mouseEvent.getY() > 650 && mouseEvent.getY() < 700 && pressed && raised) {
            if (increaseBetAmount > 0) {
                increaseBetAmount = increaseBetAmount - 25;
                amount++;
            }
        }
        if (mouseEvent.getX() > 925 && mouseEvent.getX() < 975 && mouseEvent.getY() > 575 && mouseEvent.getY() < 625 && pressed && raised) {
            if (increaseBetAmount > 0) {
                increaseBetAmount = increaseBetAmount - 50;
                amount++;
            }
        }
        if (mouseEvent.getX() > 925 && mouseEvent.getX() < 975 && mouseEvent.getY() > 500 && mouseEvent.getY() < 550 && pressed && raised) {
            if (increaseBetAmount > 0) {
                increaseBetAmount = increaseBetAmount - 100;
                amount++;
            }
        }
        if (mouseEvent.getX() > 913 && mouseEvent.getX() < 987 && mouseEvent.getY() > 713 && mouseEvent.getY() < 787 && pressed && raised) {
            if (increaseBetAmount <= application.money) {
                betAmount = increaseBetAmount + betAmount;
                application.money = application.money - betAmount;
                increaseBetAmount = 0;
                raised = false;
                amount++;
            } else {
                SaxionApp.setFill(Color.red);
                SaxionApp.drawText("you dont have enough money", 410, 565, 25);
            }
        }
    }


    private void clearArray() {
        if (!playerCardValues.isEmpty()) {
            playerCardValues.subList(0, playerCardValues.size()).clear();
        }
        if (!playerCardSuits.isEmpty()) {
            playerCardSuits.subList(0, playerCardSuits.size()).clear();
        }
        if (!playerStarterCardValues.isEmpty()) {
            playerStarterCardValues.subList(0, playerStarterCardValues.size()).clear();
        }
        if (!computerStarterCardValues1.isEmpty()) {
            computerStarterCardValues1.subList(0, computerStarterCardValues1.size()).clear();
        }
        if (!computerCardSuits1.isEmpty()) {
            computerCardSuits1.subList(0, computerCardSuits1.size()).clear();
        }
        if (!computerCardValues1.isEmpty()) {
            computerCardValues1.subList(0, computerCardValues1.size()).clear();
        }
        if (!computerCards.isEmpty()) {
            computerCards.subList(0, computerCards.size()).clear();
        }
    }

    public Integer playerScoreCheck(ArrayList<Integer> playerCardValues, ArrayList<String> playerCardSuits, ArrayList<Integer> playerStarterCardValues, ArrayList<Integer> computerStarterCardValues) {
        Rules rules = new Rules();
        int playerScore = 0;

        if (rules.playerHighCard(playerStarterCardValues, computerStarterCardValues)) {
            playerScore++;
        }
        if (rules.onePair(playerCardValues)) {
            playerScore = playerScore + 2;
        }
        if (rules.twoPair(playerCardValues)) {
            playerScore = playerScore + 3;
        }
        if (rules.threeOfaKind(playerCardValues)) {
            playerScore = playerScore + 4;
        }

        if (rules.straight(playerCardValues)) {
            playerScore = playerScore + 5;
        }
        if (rules.flush(playerCardSuits)) {
            playerScore = playerScore + 6;
        }
        if (rules.fullHouse(playerStarterCardValues)) {
            playerScore = playerScore + 7;
        }

        if (rules.fourOfaKind(playerStarterCardValues)) {
            playerScore = playerScore + 8;
        }
        if (rules.straightFlush(playerCardValues, playerCardSuits)) {
            playerScore = playerScore + 9;
        }

        if (rules.royalFlush(playerCardValues, playerCardSuits)) {
            playerScore = playerScore + 10;
        }
        return playerScore;
    }

    public Integer computerScoreCheck(ArrayList<Integer> computerCardValues, ArrayList<String> computerCardSuits, ArrayList<Integer> computerStarterCardValues, ArrayList<Integer> playerStarterCardValues) {
        Rules rules = new Rules();
        int computerScore = 0;
        if (rules.computerHighCard(playerStarterCardValues, computerStarterCardValues)) {
            computerScore++;
        }
        if (rules.onePair(computerCardValues) && !rules.twoPair(computerCardValues) && !rules.threeOfaKind(computerCardValues) && !rules.fourOfaKind(computerCardValues)) {
            computerScore = computerScore + 2;
        }
        if (rules.twoPair(computerCardValues)) {
            computerScore = computerScore + 3;
        }
        if (rules.threeOfaKind(computerCardValues)) {
            computerScore = computerScore + 4;
        }
        if (rules.straight(computerCardValues)) {
            computerScore = computerScore + 5;
        }
        if (rules.flush(computerCardSuits)) {
            computerScore = computerScore + 6;
        }
        if (rules.fullHouse(computerCardValues)) {
            computerScore = computerScore + 7;
        }
        if (rules.fourOfaKind(computerCardValues)) {
            computerScore = computerScore + 8;
        }
        if (rules.straightFlush(computerCardValues, computerCardSuits)) {
            computerScore = computerScore + 9;
        }
        if (rules.royalFlush(computerCardValues, computerCardSuits)) {
            computerScore = computerScore + 10;
        }
        return computerScore;

    }

    private void drawCard(int cardAmount) {
        int randomCard;
        randomCard = SaxionApp.getRandomValueBetween(0, deck.cards.size() - 1);
        SaxionApp.drawImage(deck.cards.get(randomCard).getCard(), 200 + (125 * cardAmount), 100, 100, 150);
        deck.cards.remove(randomCard);
        playerCardValues.add(deckValue.cardsValue.get(randomCard));
        playerCardSuits.add(deckSuit.cardSuit.get(randomCard));
        computerCardValues1.add(deckValue.cardsValue.get(randomCard));
        computerCardSuits1.add(deckSuit.cardSuit.get(randomCard));
        deckValue.cardsValue.remove(randomCard);
        deckSuit.cardSuit.remove(randomCard);
    }

    public void computerRound1() {
        if (betTimes == 0) {
            if (computerPointAmount1 == 0 || computerPointAmount1 == 1) {
                if (betAmount < 100) {

                    betAmountComputer1 = betAmount;
                }
            } else if (computerPointAmount1 > 1) {
                betAmountComputer1 = 150;
            } else {
                if (betAmount != 0) {
                    computerFold = true;
                }
            }
        }
        if (computerFold) {
            SaxionApp.drawText("folded", 750, 500, 30);
        }
    }

    public void round2Computer() {
        if (betTimes == 1 && !computerFold) {
            if (computerPointAmount1 == 1) {
                if (betAmount < 150) {
                    betAmountComputer1 = betAmount;
                }
            } else if (computerPointAmount1 > playerPointAmount) {
                betAmountComputer1 = betAmountComputer1 + 50;

            } else {
                if (betAmount != 0) {
                    computerFold = true;
                }
            }
        }
        if (computerFold) {
            SaxionApp.drawText("folded", 750, 500, 30);
        }
    }

    public void computerRound3() {
        if (betTimes == 2 && !computerFold && amount == 2) {
            if (computerPointAmount1 == 1) {
                betAmountComputer1 = betAmount;
            } else if (computerPointAmount1 > playerPointAmount) {
                betAmountComputer1 = betAmountComputer1 + 50;
            } else {
                if (betAmount != 0) {
                    computerFold = true;
                }
            }
        }
        if (computerFold) {
            SaxionApp.drawText("folded", 750, 500, 30);
        }
    }

    public void computerRound4() {
        if (betTimes == 3 && !computerFold && amount == 3) {
            if (computerPointAmount1 < 2) {
                if (betAmount != 0) {
                    computerFold = true;
                }
            } else if (computerPointAmount1 > 2) {
                betAmountComputer1 = 200;

            } else {
                if (betAmount != 0) {
                    computerFold = true;
                }
            }
            if (computerFold) {
                SaxionApp.drawText("folded", 750, 500, 30);
            }
        }
    }
}

