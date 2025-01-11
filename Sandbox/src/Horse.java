import nl.saxion.app.SaxionApp;

public class Horse {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private String image;

    public Horse(int x, int y, int width, int height, String name, String image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.image = image;
    }

    public void draw() {
        SaxionApp.drawImage(this.image, this.x, this.y, this.width, this.height);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
