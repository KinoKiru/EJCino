import nl.saxion.app.SaxionApp;

import java.awt.*;

public class RoundButton extends Button {
    private int radius;

    public RoundButton(int x, int y, int radius, String text, boolean buttonClicked) {
        super(x, y, radius * 2, radius * 2, text, buttonClicked);
        this.radius = radius;
    }

    public RoundButton(int x, int y, int radius, String text, Color borderColor, Color fillColor, Color textColor, boolean buttonClicked) {
        super(x, y, radius * 2, radius * 2, text, borderColor, fillColor, textColor, buttonClicked);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        SaxionApp.setFill(this.getFillColor());
        SaxionApp.setBorderColor(this.getBorderColor());
        SaxionApp.setTextDrawingColor(this.getTextColor());
        SaxionApp.setBorderSize(5);
        SaxionApp.drawCircle(this.getX(), this.getY(), this.getRadius());
        SaxionApp.drawText(this.getText(), this.getText().length() == 3 ? this.getX() - 30 : this.getX() - 20, this.getY() - 20, 40);
    }
}
