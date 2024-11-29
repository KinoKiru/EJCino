import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Slots extends Screen {
    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        if (mouseEvent.isLeftMouseButton() && mouseEvent.isMouseDown()) {
            if(mouseEvent.getX() > 0 && mouseEvent.getX() < 512 && mouseEvent.getY() > 100 && mouseEvent.getY() < 668) {
                SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
            }
            if(mouseEvent.getX() > 512 && mouseEvent.getX() < 1024 && mouseEvent.getY() > 100 && mouseEvent.getY() < 668) {
                SaxionApp.drawImage("resources/slot_machine/slot-machine3.png", 1, 100);
            }
        }
    }

    @Override
    public void run() {
        SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
        SaxionApp.drawImage("resources/slot_machine/slot-machine2.png", 1, 100);
    }
}
