import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Blackjack extends Screen{
    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {

    }

    @Override
    public void run() {

    }

    @Override
    public void init(Application application) {
        if (application.runInit) {
            SaxionApp.clear();
            SaxionApp.drawImage("resources/blackjack.png", 0, 0, 1024, 768);
            application.drawLayout();
            application.runInit = false;
        }
    }
}
