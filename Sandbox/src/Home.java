import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

public class Home extends Screen {
    private final ArrayList<MenuButton> menuButtons = new ArrayList<>();
    Application application;
    RoundButton addMoneyButton = new RoundButton(775, 22, 17, "+", Color.black, Color.green, Color.black,  true);

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {
        // NOOP
    }

    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        // Check if the mouse is clicked and the x and y coordinates are within the bounds of the menu item
        // If so, set the activeMenuItem to the menu item's name
        if (mouseEvent.isMouseDown() && mouseEvent.isLeftMouseButton()) {
            if (mouseEvent.getX() >= addMoneyButton.getX() - addMoneyButton.getWidth() && mouseEvent.getX() <= addMoneyButton.getX() + addMoneyButton.getRadius() * 2 &&
                    mouseEvent.getY() >= addMoneyButton.getY() && mouseEvent.getY() <= addMoneyButton.getY() + addMoneyButton.getRadius() * 2) {
                application.money += 1000;
                System.out.println("Money: " + application.money);
            }
            for (MenuButton menuButton : menuButtons) {
                if (mouseEvent.getX() >= menuButton.getX() && mouseEvent.getX() <= menuButton.getX() + menuButton.getWidth() &&
                        mouseEvent.getY() >= menuButton.getY() && mouseEvent.getY() <= menuButton.getY() + menuButton.getHeight()) {
                    application.currentScreen = Screens.valueOf(menuButton.getText().toUpperCase().replace(" ", "_"));
                    application.runInit = true;
                }
            }
        }
    }

    public void init(Application application) {
        this.application = application;
        int index = 0;
        for(Screens game : Screens.values()) {
            if (game == Screens.HOME) {
                continue;
            }
            menuButtons.add(new MenuButton(index < 3 ? (index % 3) * 325 + 25 : (175 + (index % 3) * 350), index >= 3 ? 400 : 50, 300, 300, game.getName(),false, "resources/menu/" + game.getName().toLowerCase().replace(" ", "") + ".png"));
            index++;
        }
        SaxionApp.clear();
    }


    @Override
    public void run() {
        SaxionApp.drawImage("resources/las_vegas.png", 0, 0, 1024, 768);
        application.drawLayout();
        for (MenuButton menuButton : menuButtons) {
            menuButton.draw();
        }
        addMoneyButton.draw(true);
    }
}

