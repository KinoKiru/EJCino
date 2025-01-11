import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class HorseRacing extends Screen {
    Application application;
    ArrayList<RoundButton> betButtons = new ArrayList<>();
    ArrayList<Horse> horses = new ArrayList<>();
    ArrayList<RoundButton> numberButtons = new ArrayList<>();
    RoundButton replay;
    RoundButton reset;
    RoundButton start;
    Timer timer = new Timer();

    int betAmount = 0;
    int movementCounter = 0;
    int gridLength = 50;
    boolean replayShown = false;

    // COLORS
    Color ownBlue = Color.decode("#00A2E8");
    Color ownPurple = Color.decode("#3F48CC");
    Color ownOrange = Color.decode("#FF7F27");
    Color ownYellow = Color.decode("#FFF200");
    Color ownGreen = Color.decode("#47B733");
    Color ownPink = Color.decode("#FFAEC9");
    Color backgroundBlue = Color.decode("#119DFB");
    Color darkGreenGround = Color.decode("#1F6212");


    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {
        // NOOP
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        if (mouseEvent.isMouseDown() && mouseEvent.isLeftMouseButton()) {
            int mouseX = mouseEvent.getX();
            int mouseY = mouseEvent.getY();

            if (mouseX >= replay.getX() - 30 && mouseX <= replay.getX() + 30 &&
                    mouseY >= replay.getY() - 30 && mouseY <= replay.getY() + 30 && replayShown) {
                application.runInit = true;
                application.currentScreen = Screens.HORSE_RACING;
                replayShown = false;
            }

            for (RoundButton button : betButtons) {
                if (mouseX >= button.getX() - 50 && mouseX <= button.getX() + 50 &&
                        mouseY >= button.getY() - 50 && mouseY <= button.getY() + 50 && !replayShown) {
                    if (button.isButtonClicked()) {
                        betAmount -= Integer.parseInt(button.getText());
                    } else {
                        betAmount += Integer.parseInt(button.getText());
                    }
                    button.setButtonClicked(!button.isButtonClicked());
                    button.draw(false);
                    drawInformationBox();
                }
            }

            for (RoundButton button : numberButtons) {
                if (mouseX >= button.getX() - 50 && mouseX <= button.getX() + 50 &&
                        mouseY >= button.getY() - 50 && mouseY <= button.getY() + 50) {
                    button.setButtonClicked(!button.isButtonClicked());
                    button.draw(false);
                    drawInformationBox();
                }
            }

            if (mouseX >= reset.getX() - 30 && mouseX <= reset.getX() + 30 &&
                    mouseY >= reset.getY() - 30 && mouseY <= reset.getY() + 30) {
                betAmount = 0;
                for (RoundButton button : betButtons.stream().filter(Button::isButtonClicked).toList()) {
                    button.setButtonClicked(false);
                    button.draw(false);
                }

                for (RoundButton button : numberButtons.stream().filter(Button::isButtonClicked).toList()) {
                    button.setButtonClicked(false);
                    button.draw(false);
                }
                drawInformationBox();
            }

            if (mouseX >= start.getX() - 50 && mouseX <= start.getX() + 50 &&
                    mouseY >= start.getY() - 50 && mouseY <= start.getY() + 50) {
                application.money -= betAmount;
                start.setButtonClicked(!start.isButtonClicked());
                timer.scheduleAtFixedRate(new TimerTask() {

                    public void run() {
                        SaxionApp.clear();
                        SaxionApp.drawImage("resources/horse/background.png", 0, 0, 1024, 768);
                        drawInformationBox();

                        if (movementCounter < 2) {
                            movementCounter++;

                            SaxionApp.setBorderColor(darkGreenGround);
                            SaxionApp.setFill(darkGreenGround);
                            SaxionApp.drawCircle(275, 285, 25);

                            SaxionApp.setBorderColor(ownGreen);
                            SaxionApp.setFill(ownGreen);
                            SaxionApp.drawCircle(280, 340, 25);

                            SaxionApp.setBorderColor(darkGreenGround);
                            SaxionApp.setFill(darkGreenGround);
                            SaxionApp.drawCircle(285, 395, 25);

                            SaxionApp.setBorderColor(ownGreen);
                            SaxionApp.setFill(ownGreen);
                            SaxionApp.drawCircle(290, 455, 25);

                            SaxionApp.setBorderColor(darkGreenGround);
                            SaxionApp.setFill(darkGreenGround);
                            SaxionApp.drawCircle(295, 515, 25);

                            SaxionApp.setBorderColor(ownGreen);
                            SaxionApp.setFill(ownGreen);
                            SaxionApp.drawCircle(300, 565, 25);

                            SaxionApp.setTextDrawingColor(Color.yellow);
                            SaxionApp.setBorderColor(Color.BLACK);
                            SaxionApp.setBorderSize(5);


                            for (Horse horse : horses) {
                                horse.draw();
                            }

                            SaxionApp.sleep(1.5);

                            int amountMovement1 = SaxionApp.getRandomValueBetween(0, 3);
                            int amountMovement2 = SaxionApp.getRandomValueBetween(0, 3);
                            int amountMovement3 = SaxionApp.getRandomValueBetween(0, 3);
                            int amountMovement4 = SaxionApp.getRandomValueBetween(0, 3);
                            int amountMovement5 = SaxionApp.getRandomValueBetween(0, 3);
                            int amountMovement6 = SaxionApp.getRandomValueBetween(0, 3);

                            horses.getFirst().setX(200 + (200 + amountMovement1 * gridLength) + amountMovement1 * gridLength);
                            horses.get(1).setX(205 + (205 + amountMovement2 * gridLength) + amountMovement2 * gridLength);
                            horses.get(2).setX(210 + (210 + amountMovement3 * gridLength) + amountMovement3 * gridLength);
                            horses.get(3).setX(215 + (215 + amountMovement4 * gridLength) + amountMovement4 * gridLength);
                            horses.get(4).setX(220 + (220 + amountMovement5 * gridLength) + amountMovement5 * gridLength);
                            horses.getLast().setX(225 + (225 + amountMovement6 * gridLength) + amountMovement6 * gridLength);
                        } else if (movementCounter == 2) {
                            int winningHorse = SaxionApp.getRandomValueBetween(1, 7);
                            SaxionApp.setTextDrawingColor(Color.YELLOW);
                            SaxionApp.setBorderSize(3);
                            SaxionApp.setBorderColor(Color.BLACK);
                            if (winningHorse == 1) {
                                SaxionApp.drawText("Horse 1 won!", 550, 50, 50);
                                SaxionApp.drawImage("resources/horse/5420053-copy.png", 300, 200, 550, 200);
                            } else {
                                SaxionApp.drawText("Horse " + winningHorse + " won!", 550, 50, 50);
                                SaxionApp.drawImage("resources/horse/horse" + winningHorse + ".png", 300, 200, 550, 400);
                            }
                            cancel();

                            if (numberButtons.stream().filter(Button::isButtonClicked).anyMatch(e -> e.getText().equals(String.valueOf(winningHorse))) && betAmount > 0) {
                                SaxionApp.setTextDrawingColor(Color.YELLOW);
                                application.money += betAmount * 2;
                                SaxionApp.drawText("You won: " + betAmount * 2 + "!", 400, 625, 60);
                            } else {
                                SaxionApp.setTextDrawingColor(Color.RED);
                                SaxionApp.drawText("You lost...", 400, 625, 60);
                            }
                            replay.draw(true);
                            replayShown = true;
                        }
                    }
                }, 0, 2000);
            }
        }
    }

    private void drawInformationBox() {
        // Draw information text box
        SaxionApp.setFill(Color.BLACK);
        SaxionApp.setBorderColor(Color.BLACK);
        SaxionApp.drawRectangle(100, 630, 250, 100);
        SaxionApp.setTextDrawingColor(Color.WHITE);
        SaxionApp.drawText("Selected amount: " + betAmount, 110, 640, 20);
        StringBuilder sb = new StringBuilder();
        numberButtons.stream().filter(Button::isButtonClicked).forEach(e -> sb.append(e.getText()).append(", "));
        SaxionApp.drawText("Betting on horse: " + sb, 110, 670, 20);
    }

    @Override
    public void run() {

    }

    @Override
    public void init(Application application) {
        if (application.runInit) {
            this.application = application;
            reset();
            System.out.println("Horse Racing");
            SaxionApp.drawImage("resources/horse/background.png", 0, 0, 1024, 768);
            horses.add(new Horse(0, 170, 250, 100, "horse 1", "resources/horse/5420053-copy.png"));
            horses.add(new Horse(5, 195, 250, 200, "horse 2", "resources/horse/horse2.png"));
            horses.add(new Horse(10, 255, 250, 200, "horse 3", "resources/horse/horse3.png"));
            horses.add(new Horse(15, 315, 250, 200, "horse 4", "resources/horse/horse4.png"));
            horses.add(new Horse(20, 375, 250, 200, "horse 5", "resources/horse/horse5.png"));
            horses.add(new Horse(25, 420, 250, 200, "horse 6", "resources/horse/horse6.png"));

            numberButtons.add(new RoundButton(275, 285, 25, "1", ownBlue, ownBlue, Color.black, false));
            numberButtons.add(new RoundButton(280, 350, 25, "2", ownPurple, ownPurple, Color.black, false));
            numberButtons.add(new RoundButton(285, 405, 25, "3", ownOrange, ownOrange, Color.black, false));
            numberButtons.add(new RoundButton(290, 460, 25, "4", ownYellow, ownYellow, Color.black, false));
            numberButtons.add(new RoundButton(295, 520, 25, "5", ownGreen, ownGreen, Color.black, false));
            numberButtons.add(new RoundButton(300, 580, 25, "6", ownPink, ownPink, Color.black, false));

            betButtons.add(new RoundButton(450, 675, 50, "10", Color.red, Color.red, Color.black, false));
            betButtons.add(new RoundButton(575, 675, 50, "20", Color.green, Color.green, Color.black, false));
            betButtons.add(new RoundButton(700, 675, 50, "50", Color.yellow, Color.yellow, Color.black, false));
            betButtons.add(new RoundButton(825, 675, 50, "100", Color.magenta, Color.magenta, Color.black, false));
            betButtons.add(new RoundButton(950, 675, 50, "200", Color.cyan, Color.cyan, Color.black, false));

            reset = new RoundButton(50, 675, 35, "Reset", Color.red, Color.red, Color.black, false);
            start = new RoundButton(450, 100, 50, "Start", Color.orange, Color.orange, Color.black, false);
            replay = new RoundButton(950, 675, 50, "Replay", Color.red, Color.red, Color.black, false);

            for (RoundButton button : betButtons) {
                button.draw(false);
            }

            for (Horse horse : horses) {
                horse.draw();
            }

            for (RoundButton button : numberButtons) {
                button.draw(false);
            }
            reset.draw(true, 20);
            start.draw(true);

            application.runInit = false;
        }
    }

    private void reset() {
        SaxionApp.clear();
        horses.clear();
        numberButtons.clear();
        betButtons.clear();
        movementCounter = 0;
    }
}

