import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.util.ArrayList;

public class Slots extends Screen {
    ArrayList<Integer> values = new ArrayList<Integer>();

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        if (mouseEvent.isLeftMouseButton() && mouseEvent.isMouseDown()) {
            if(mouseEvent.getX() > 512 && mouseEvent.getX() < 1024 && mouseEvent.getY() > 100 && mouseEvent.getY() < 668) {
                SaxionApp.drawImage("resources/slot_machine/slot-machine3.png", 1, 100);
                values.clear();
                for (int i = 0; i < 3; i++) {
                    int random = (int) (Math.random() * 3) + 1;
                    values.add(random);
                }
            }
        }
    }

    @Override
    public void run() {
        SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
        SaxionApp.drawImage("resources/slot_machine/slot-machine2.png", 1, 100);

        if (!this.values.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                SaxionApp.drawImage("resources/slot_machine/slot-symbol" + values.get(i) + ".png",(i * 130) + 235, 400);
            }
        }
    }
}
