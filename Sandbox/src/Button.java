import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Color borderColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private Color textColor = Color.BLACK;
    private boolean buttonClicked;


    public Button(int x, int y, int width, int height, String text, boolean buttonClicked) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.buttonClicked = buttonClicked;
    }

    public Button(int x, int y, int width, int height, String text, Color borderColor, Color fillColor, Color textColor, boolean buttonClicked) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.textColor = textColor;
        this.buttonClicked = buttonClicked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /// Draw the button
    public void draw() {
        SaxionApp.setBorderColor(borderColor);
        SaxionApp.setFill(fillColor);
        SaxionApp.setTextDrawingColor(textColor);
        SaxionApp.drawRectangle(x, y, width, height);
        SaxionApp.drawBorderedText(text, x + (width / 2) - (text.length() * 6), y + (height - 50), 30);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

    public void setButtonClicked(boolean buttonClicked) {
        this.buttonClicked = buttonClicked;
    }
}
