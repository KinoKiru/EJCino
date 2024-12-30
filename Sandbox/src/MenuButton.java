import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.Arrays;

public class MenuButton extends Button {
    private final String filePath;

    public MenuButton(int x, int y, int width, int height, String text, boolean buttonClicked, String filePath) {
        super(x, y, width, height, text, buttonClicked);
        this.filePath = filePath;
    }

    public MenuButton(int x, int y, int width, int height, String text, Color borderColor, Color fillColor, Color textColor, boolean buttonClicked, String filePath) {
        super(x, y, width, height, text, borderColor, fillColor, textColor, buttonClicked);
        this.filePath = filePath;
    }

    @Override
    public void draw() {
        SaxionApp.setBorderColor(Color.YELLOW);
        SaxionApp.setFill(Color.WHITE);
        SaxionApp.turnFillOff();
        SaxionApp.drawRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        SaxionApp.setTextDrawingColor(Color.BLACK);
        SaxionApp.turnFillOn();
        SaxionApp.drawBorderedText(this.getText(), this.getX() + (this.getWidth() / 2) - (this.getText().length() * 6), this.getY() + (this.getHeight() - 50), 30);
        int height = Arrays.stream(new String[]{"SLOTS", "BLACKJACK", "POKER"}).anyMatch(s -> s.equalsIgnoreCase(this.getText())) ? 200 : 150;
        int width = Arrays.stream(new String[]{"SLOTS", "BLACKJACK", "POKER"}).anyMatch(s -> s.equalsIgnoreCase(this.getText())) ? 200 : 150;
        SaxionApp.drawImage(filePath, this.getX() + 50, this.getY() + 50, width, height);
    }
}
