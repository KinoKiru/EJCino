import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.Arrays;

public class MenuItem {
    private final Screens text;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private String filePath;

    public MenuItem(Screens text, int x, int y, int width, int height, String filePath) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filePath = filePath;
    }

    public Screens getName() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw() {
        SaxionApp.setBorderColor(Color.YELLOW);
        SaxionApp.setFill(Color.WHITE);
        SaxionApp.turnFillOff();
        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.setTextDrawingColor(Color.BLACK);
        SaxionApp.turnFillOn();
        SaxionApp.drawBorderedText(text.getName(), x + (width / 2) - (text.getName().length() * 6), y + (height - 50), 30);
        int height = Arrays.stream(new String[]{"SLOTS", "BLACKJACK", "POKER"}).anyMatch(s -> s.equalsIgnoreCase(text.getName())) ? 200 : 150;
        int width = Arrays.stream(new String[]{"SLOTS", "BLACKJACK", "POKER"}).anyMatch(s -> s.equalsIgnoreCase(text.getName())) ? 200 : 150;
        SaxionApp.drawImage(filePath, x + 50, y + 50, width, height);
    }
}
