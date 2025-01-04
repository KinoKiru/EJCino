import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;

public class Application implements GameLoop {
    public static final String COLOR = "#129CFE";
    // Hold the current screen and is updated by the home
    public Screens currentScreen = Screens.HOME;
    // If true, the init method will be called once
    public boolean runInit = true;
    private final boolean dev = false;
    public int money = dev ? 9000 : 1000;

    // Create an array of screens
    public Screen[] screens = new Screen[]{new Home(), new Slots(), new Blackjack(), new Yathzee(), new HorseRacing(), new Roulette(), new Poker()};

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new Application(), 1024, 768, 600);
    }

    @Override
    public void init() {
        SaxionApp.setBackgroundColor(Color.decode(COLOR));
        System.out.println("Application started");
        if (!dev) {
            SaxionApp.playSound("resources/audio/lobby.wav", true);
        }
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_ESCAPE) {
            currentScreen = Screens.HOME;
            runInit = true;
        }
        System.out.println(keyboardEvent.toString());
        if (keyboardEvent.isKeyPressed()) {
            switch (currentScreen) {
                case Screens.HOME:
                    screens[0].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.BLACKJACK:
                    screens[2].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.SLOTS:
                    screens[1].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.YATHZEE:
                    screens[3].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.HORSE_RACING:
                    screens[4].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.ROULETTE:
                    screens[5].keyboardEvent(keyboardEvent, this);
                    break;
                case Screens.POKER:
                    screens[6].keyboardEvent(keyboardEvent, this);
                    break;
            }

        }
    }

    @Override
    public void loop() {
        switch (currentScreen) {
            case Screens.HOME:
                screens[0].init(this);
                screens[0].run();
                break;
            case Screens.BLACKJACK:
                screens[2].init(this);
                screens[2].run();
                break;
            case Screens.SLOTS:
                screens[1].init(this);
                screens[1].run();
                break;
            case Screens.YATHZEE:
                screens[3].init(this);
                screens[3].run();
                break;
            case Screens.HORSE_RACING:
                screens[4].init(this);
                screens[4].run();
                break;
            case Screens.ROULETTE:
                screens[5].init(this);
                screens[5].run();
                break;
            case Screens.POKER:
                screens[6].init(this);
                screens[6].run();
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
                screens[2].mouseEvent(mouseEvent, this);
                break;
            case Screens.SLOTS:
                screens[1].mouseEvent(mouseEvent, this);
                break;
            case Screens.YATHZEE:
                screens[3].mouseEvent(mouseEvent, this);
                break;
            case Screens.HORSE_RACING:
                screens[4].mouseEvent(mouseEvent, this);
                break;
            case Screens.ROULETTE:
                screens[5].mouseEvent(mouseEvent, this);
                break;
            case Screens.POKER:
                screens[6].mouseEvent(mouseEvent, this);
                break;
        }
    }

    /// Draw the basic layout of the application
    public void drawLayout() {
        SaxionApp.setFill(Color.white);
        SaxionApp.setBorderColor(Color.black);
        SaxionApp.drawBorderedText("EJCino", 5, 5, 35);
        SaxionApp.drawBorderedText("Speel onbewust 18-", 5, SaxionApp.getHeight() - 25, 25);
        SaxionApp.drawBorderedText("Money: " + this.money, 850 - (String.valueOf(this.money).length() * 10), 10, 35);
    }
}






