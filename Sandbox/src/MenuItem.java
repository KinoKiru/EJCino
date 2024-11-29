import nl.saxion.app.SaxionApp;

import java.awt.*;

public class MenuItem {
    private final Screens text;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public MenuItem(Screens text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    }
}
