import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.util.ArrayList;

public class Slots extends Screen {
    ArrayList<Integer> values = new ArrayList<Integer>();

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent, Application application) {
        if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_ESCAPE) {
            values.clear();
            SaxionApp.setGameLoopTimeMs(600);
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent, Application application) {
        if (mouseEvent.isLeftMouseButton() && mouseEvent.isMouseDown()) {
            if (mouseEvent.getX() > 512 && mouseEvent.getX() < 1024 && mouseEvent.getY() > 100 && mouseEvent.getY() < 668) {
                SaxionApp.clear();
                SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
                SaxionApp.drawImage("resources/slot_machine/slot-machine3.png", 1, 100);
                values.clear();
                for (int i = 0; i < 3; i++) {
                    int random = (int) (Math.random() * 3) + 1;
                    values.add(random);
                }
            }
        }
    }

    // Will be called before the run method
    public void init() {
        SaxionApp.setGameLoopTimeMs(1000);
        SaxionApp.clear();
        System.out.println("Slots init");
    }

    @Override
    public void run() {
        SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
        SaxionApp.drawImage("resources/slot_machine/slot-machine2.png", 1, 100);

        if (!this.values.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                SaxionApp.drawImage("resources/slot_machine/slot-symbol" + values.get(i) + ".png", (i * 130) + 235, 400);
            }
        }
    }
}
