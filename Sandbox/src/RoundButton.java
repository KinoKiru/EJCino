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

    /// Default draw method
    @Override
    public void draw() {
        this.draw(false);
    }

    public void draw(boolean ignoreClicked) {
        drawCircle(ignoreClicked);
        SaxionApp.drawText(this.getText(),  this.getX() - (this.getText().length() * 10), this.getY() - 20, 40);
    }

    public void draw(boolean ignoreClicked, int fontSize) {
        drawCircle(ignoreClicked);
        SaxionApp.drawText(this.getText(),  this.getX() - (this.getText().length() * 10) + fontSize, this.getY(), fontSize);
    }

    private void drawCircle(boolean ignoreClicked) {
        SaxionApp.setFill(ignoreClicked ? this.getFillColor() : (this.isButtonClicked() ?  Color.gray : this.getFillColor()));
        SaxionApp.setBorderColor(this.getBorderColor());
        SaxionApp.setTextDrawingColor(this.getTextColor());
        SaxionApp.setBorderSize(5);
        SaxionApp.drawCircle(this.getX(), this.getY(), this.getRadius());
    }
}
