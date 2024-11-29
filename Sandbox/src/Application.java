import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;

public class Application implements GameLoop {
    public static final String COLOR = "#129CFE";
    public Screens currentScreen = Screens.HOME;
    // Create an array of screens
    public Screen[] screens = new Screen[]{new Menu()};

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new Application(), 1024, 768, 600);
    }

    @Override
    public void init() {
        SaxionApp.setBackgroundColor(Color.decode(COLOR));
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        System.out.println(keyboardEvent.toString());
        if (keyboardEvent.isKeyPressed()) {
            if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_ESCAPE) {
                currentScreen = Screens.HOME;
            }
            // TODO: add switch statement to handle keyboard events for each game
        }
    }

    @Override
    public void loop() {
        System.out.println(currentScreen);
        switch (currentScreen) {
            case Screens.HOME:
                SaxionApp.clear();
                screens[0].run();
                break;
            case Screens.BLACKJACK:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
            case Screens.SLOTS:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
            case Screens.YATHZEE:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
            case Screens.HORSE_RACING:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
            case Screens.ROULETTE:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
            case Screens.POKER:
                SaxionApp.clear();
                SaxionApp.drawBorderedText(currentScreen.getName(), 1, 1, 20);
                break;
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        switch (currentScreen) {
            case Screens.HOME:
                screens[0].mouseEvent(mouseEvent, this);
                break;
            case Screens.BLACKJACK:
                SaxionApp.drawBorderedText("Game mouse event?", 1, 1, 20);
                break;
            case Screens.SLOTS:
                //SaxionApp.printLine("Game Over");
                break;
            case Screens.YATHZEE:
                //SaxionApp.printLine("Game Over");
                break;
            case Screens.HORSE_RACING:
                //SaxionApp.printLine("Game Over");
                break;
            case Screens.ROULETTE:
                //SaxionApp.printLine("Game Over");
                break;
            case Screens.POKER:
                //SaxionApp.printLine("Game Over");
                break;
        }
    }
}






