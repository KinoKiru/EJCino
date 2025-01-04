import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Roulette extends Screen {
    ArrayList<RoundButton> betButtons = new ArrayList<>();
    ArrayList<Button> numberButtons = new ArrayList<>();
    RoundButton questionButton;
    Button resetButton;
    RoundButton spinButton;
    RoundButton resultButton;
    Application application;

    Color backgroundBlue = Color.decode("#119CFD");
    int betAmount = 0;

    int[] blackTiles = new int[]{
            2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35
    };
    int[] firstThirds = new int[] {
            1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34
    };
    int[] secondThirds = new int[] {
            2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35
    };
    int[] thirdThirds = new int[] {
            3, 6, 9, 12, 15, 18, 21, 244, 27, 30, 33, 36
    };

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {
        // NOOP
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        if (mouseEvent.isLeftMouseButton() && mouseEvent.isMouseDown()) {
            int mouseX = mouseEvent.getX();
            int mouseY = mouseEvent.getY();

            // Handle question mark button
            if (mouseX >= 400 - 50 && mouseX <= 400 + 50 &&
                    mouseY >= 0 && mouseY <= 50 + 50) {
                if (!questionButton.isButtonClicked()) {
                    SaxionApp.setBorderColor(Color.BLACK);
                    SaxionApp.setFill(Color.BLACK);
                    SaxionApp.drawRectangle(450, 20, 330, 120);

                    SaxionApp.setTextDrawingColor(Color.WHITE);
                    SaxionApp.drawText("Possible bets:", 460, 30, 14);
                    SaxionApp.drawText("1. Dozen Bet (1st 12, 2nd 2, etc) – Pays 2 to 1", 460, 45, 14);
                    SaxionApp.drawText("2. Odd or Even – Pays 1 to 1", 460, 60, 14);
                    SaxionApp.drawText("3. Red or Black – Pays 1 to 1", 460, 75, 14);
                    SaxionApp.drawText("4. High or Low (1-18 and 19-36) – Pays 1 to 1", 460, 90, 14);
                    SaxionApp.drawText("5. Column Bet on 12 Numbers (2/1) – Pays 2 to 1 ", 460, 105, 14);
                    SaxionApp.drawText("6. Straight Bet on One Number – Pays 35 to 1", 460, 120, 14);
                    questionButton.setButtonClicked(true);
                } else {
                    SaxionApp.setBorderColor(backgroundBlue);
                    SaxionApp.setFill(backgroundBlue);
                    SaxionApp.drawRectangle(450, 20, 330, 120);
                    SaxionApp.setFill(Color.white);
                    SaxionApp.setBorderColor(Color.black);
                    questionButton.setButtonClicked(false);
                }
            }

            // Handle spin button
            for (RoundButton button : betButtons) {
                if (mouseX >= button.getX() - button.getRadius() && mouseX <= button.getX() + button.getRadius() &&
                        mouseY >= button.getY() - button.getRadius() && mouseY <= button.getY() + button.getRadius()) {
                    button.setButtonClicked(!button.isButtonClicked());
                    if (button.isButtonClicked()) {
                        button.draw(false);
                        betAmount += Integer.parseInt(button.getText());
                    } else {
                        button.draw(false);
                        betAmount -= Integer.parseInt(button.getText());
                    }
                }
            }

            // Handle number buttons
            for (Button button : numberButtons) {
                if (mouseX >= button.getX() && mouseX <= button.getX() + button.getWidth() &&
                        mouseY >= button.getY() && mouseY <= button.getY() + button.getHeight()) {
                    button.setButtonClicked(!button.isButtonClicked());
                    button.draw();
                }
            }

            // Handle reset button
            if (mouseX >= resetButton.getX() && mouseX <= resetButton.getX() + resetButton.getWidth() &&
                    mouseY >= resetButton.getY() && mouseY <= resetButton.getY() + resetButton.getHeight()) {
                for (RoundButton button : betButtons) {
                    if (button.isButtonClicked()) {
                        button.setButtonClicked(false);
                        betAmount -= Integer.parseInt(button.getText());
                        button.draw(false);
                    }
                }

                for (Button button : numberButtons) {
                    if (button.isButtonClicked()) {
                        button.setButtonClicked(false);
                        button.draw();
                    }
                }
            }

            ArrayList<Button> clickedButtons = numberButtons.stream().filter(Button::isButtonClicked).collect(Collectors.toCollection(ArrayList::new));
            // Handle spin button
            if (mouseX >= spinButton.getX() - spinButton.getRadius() && mouseX <= spinButton.getX() + spinButton.getRadius() &&
                    mouseY >= spinButton.getY() - spinButton.getRadius() && mouseY <= spinButton.getY() + spinButton.getRadius() && betAmount > 0 && !clickedButtons.isEmpty()) {
                application.money -= (betAmount * clickedButtons.size());
                int winningNumber = (int) (Math.random() * 36);
                resultButton.setText(String.valueOf(winningNumber));
                resultButton.draw(true);
                boolean hasWon = false;
                // Check if the player has selected any buttons
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals(String.valueOf(winningNumber)))) {
                    application.money += betAmount * 35;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Red") && Arrays.stream(blackTiles).noneMatch(e -> e == winningNumber))) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Black") && Arrays.stream(blackTiles).anyMatch(e -> e == winningNumber))) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Odd") && winningNumber % 2 != 0)) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Even") && winningNumber % 2 == 0)) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("1-18") && winningNumber >= 1 && winningNumber <= 18)) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("19-36") && winningNumber >= 19 && winningNumber <= 36)) {
                    application.money += betAmount * 2;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("1st 12") && winningNumber >= 1 && winningNumber <= 12)) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("2nd 12") && winningNumber >= 13 && winningNumber <= 24)) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("3rd 12") && winningNumber >= 25 && winningNumber <= 36)) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("First 2/1") && Arrays.stream(firstThirds).anyMatch(e -> e == winningNumber))) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Second 2/1") && Arrays.stream(secondThirds).anyMatch(e -> e == winningNumber))) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }
                if (clickedButtons.stream().anyMatch(button -> button.getText().equals("Third 2/1") && Arrays.stream(thirdThirds).anyMatch(e -> e == winningNumber))) {
                    application.money += betAmount * 3;
                    hasWon = true;
                }

                if (hasWon) {
                    SaxionApp.drawImage("resources/Winner-19-12-2024.png", 450, 20, 330, 120);
                } else {
                    SaxionApp.drawText("You lost!", 250, 400, 30);
                }
            }
        }
    }

    @Override
    public void run() {
        if (!questionButton.isButtonClicked()) {
            SaxionApp.clear();
            application.drawLayout();
            drawButtons();
        }
    }

    private void drawButtons() {
        for (RoundButton button : betButtons) {
            button.draw(false);
        }

        for (Button button : numberButtons) {
            button.draw();
        }

        resetButton.draw();
        questionButton.draw(true);
        spinButton.draw(true);
        resultButton.draw(true);
    }

    @Override
    public void init(Application application) {
        if (application.runInit) {
            this.application = application;
            SaxionApp.clear();
            System.out.println("Roulette init");
            betButtons.add(new RoundButton(475, 675, 50, "10", Color.red, Color.red, Color.black, false));
            betButtons.add(new RoundButton(600, 675, 50, "20", Color.green, Color.green, Color.black, false));
            betButtons.add(new RoundButton(725, 675, 50, "50", Color.blue, Color.blue, Color.black, false));
            betButtons.add(new RoundButton(850, 675, 50, "100", Color.magenta, Color.magenta, Color.black, false));
            betButtons.add(new RoundButton(975, 675, 50, "200", Color.yellow, Color.yellow, Color.black, false));

            //SaxionApp.drawImage("resources/RouletteBackground.png", 0, 0); //roulette background

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

            resetButton = new Button(45, 560, 110, 50, "Reset", Color.red, Color.red, Color.black, false);
            questionButton = new RoundButton(400, 50, 30, " ?", false);
            spinButton = new RoundButton(475, 550, 50, "Spin", Color.orange, Color.orange, Color.black, false);
            resultButton = new RoundButton(250, 355, 100, "", false);

            drawButtons();

            application.runInit = false;
        }
    }
}
