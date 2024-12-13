import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.util.ArrayList;

public class Home extends Screen {
    private final ArrayList<MenuItem> menuItems = new ArrayList<>();
    Application application;

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {
        // NOOP
    }

    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        // Check if the mouse is clicked and the x and y coordinates are within the bounds of the menu item
        // If so, set the activeMenuItem to the menu item's name
        if (mouseEvent.isMouseDown() && mouseEvent.isLeftMouseButton()) {
            for (MenuItem menuItem : menuItems) {
                if (mouseEvent.getX() >= menuItem.getX() && mouseEvent.getX() <= menuItem.getX() + menuItem.getWidth() &&
                        mouseEvent.getY() >= menuItem.getY() && mouseEvent.getY() <= menuItem.getY() + menuItem.getHeight()) {
                    application.currentScreen = menuItem.getName();
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
            menuItems.add(new MenuItem(game, (index % 3) * 325 + 25, index >= 3 ? 400 : 50, 300, 300));
            index++;
        }
        SaxionApp.clear();
    }


    @Override
    public void run() {
        application.drawLayout();
        for (MenuItem menuItem : menuItems) {
            menuItem.draw();
        }
    }
}

