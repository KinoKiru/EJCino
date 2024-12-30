import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Roulette extends Screen {
    ArrayList<RoundButton> betButtons = new ArrayList<>();
    ArrayList<Button> numberButtons = new ArrayList<>();
    Application application;


    int[] blackTiles = new int[]{
            2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35
    };

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {

    }

    @Override
    public void run() {

    }

    @Override
    public void init(Application application) {
        if (application.runInit) {
            this.application = application;
            SaxionApp.clear();
            System.out.println("Roulette init");
            betButtons.add(new RoundButton(475 , 675,  50, "10", Color.red, Color.red, Color.black, false));
            betButtons.add(new RoundButton(600 , 675,  50, "20", Color.green, Color.green, Color.black, false));
            betButtons.add(new RoundButton(725 , 675,  50, "50", Color.blue, Color.blue, Color.black, false));
            betButtons.add(new RoundButton(850 , 675,  50, "100", Color.magenta, Color.magenta, Color.black, false));
            betButtons.add(new RoundButton(975 , 675,  50, "200", Color.yellow, Color.yellow, Color.black, false));

            SaxionApp.drawImage("resources/RouletteBackground.png", 0, 0); //roulette background

            numberButtons.add(new Button(800, 75, 210, 50, "0", Color.green, Color.green, Color.black, false));

            int buttonX = 550;
            int buttonY = 150;

            // Add buttons for numbers 1-36
            for (int buttons1 = 1; buttons1 <= 36; buttons1++) {
                int finalI = buttons1;
                Color color = Arrays.stream(blackTiles).anyMatch(e -> e == finalI) ? Color.black : Color.red;
                // TODO: look into color issue with the text color not correctly set
                numberButtons.add(new Button(buttonX, buttonY, 60, 35, String.valueOf(buttons1), color, color, Color.white, false));
                buttonX += 80;
                if (buttons1 % 6 == 0) {
                    buttonX = 550;
                    buttonY += 50;
                }
            }

            numberButtons.add(new Button(550, 450, 140, 40, "First 2/1", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(710, 450, 140, 40, "Second 2/1", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(870, 450, 140, 40, "Third 2/1", Color.green, Color.green, Color.black, false));

            numberButtons.add(new Button(550, 510, 140, 40, "1st 12", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(710, 510, 140, 40, "2nd 12", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(870, 510, 140, 40, "3rd 12", Color.green, Color.green, Color.black, false));

            numberButtons.add(new Button(550, 565, 65, 35, "Odd", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(630, 565, 65, 35, "Even", Color.green, Color.green, Color.black, false));

            numberButtons.add(new Button(710, 565, 65, 35, "Red", Color.red, Color.red, Color.black, false));
            numberButtons.add(new Button(790, 565, 65, 35, "Black", Color.black, Color.black, Color.white, false));

            numberButtons.add(new Button(870, 565, 65, 35, "1-18", Color.green, Color.green, Color.black, false));
            numberButtons.add(new Button(950, 565, 65, 35, "19-36", Color.green, Color.green, Color.black, false));

            for (RoundButton button : betButtons) {
                button.draw();
            }

            for (Button button : numberButtons) {
                button.draw();
            }
            SaxionApp.setBorderColor(Color.BLACK);
            SaxionApp.setFill(Color.red);
            SaxionApp.drawRectangle(45, 560, 80, 50);
            SaxionApp.setTextDrawingColor(Color.BLACK);
            SaxionApp.drawText("Reset", 50, 575, 22);

            SaxionApp.setBorderColor(Color.BLACK);
            SaxionApp.setFill(Color.orange);
            SaxionApp.setBorderSize(5);
            SaxionApp.drawCircle(475, 575, 50);
            SaxionApp.drawText("Spin!", 445, 560, 30);

            SaxionApp.setBorderColor(Color.black);
            SaxionApp.setFill(Color.white);
            SaxionApp.drawCircle(250, 355, 105);

            SaxionApp.setBorderColor(Color.BLACK);
            SaxionApp.setFill(Color.WHITE);
            SaxionApp.setBorderSize(3);
            SaxionApp.drawCircle(400, 50, 20);
            SaxionApp.drawText("?", 395, 40, 20);
            application.runInit = false;
        }
    }
}
