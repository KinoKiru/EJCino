import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.util.ArrayList;
import java.util.Objects;

public class Slots extends Screen {
    ArrayList<Integer> values = new ArrayList<>();
    Application application;

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
                if (this.application.money >= 100) {
                    this.application.money -= 100;
                    values.clear();
                    SaxionApp.clear();
                    SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
                    SaxionApp.drawImage("resources/slot_machine/slot-machine3.png", 1, 100);
                    SaxionApp.playSound("resources/audio/spin.wav", false);

                    for (int i = 0; i < 3; i++) {
                        int random = (int) (Math.random() * 4) + 1;
                        values.add(random);
                    }
                    if (values.stream().allMatch(i -> Objects.equals(i, values.getFirst()))) {
                        this.application.money += values.getFirst() * 100;
                    }
                } else {
                    SaxionApp.drawBorderedText("Not enough money", 100, 50, 50);
                }
//                SaxionApp.sleep(2); Does allow the audio to finish, but the game loop is paused and images are not updated
            }
        }
    }

    // Will be called before the run method
    public void init(Application application) {
        if (application.runInit) {
            this.application = application;
            SaxionApp.setGameLoopTimeMs(1000);
            SaxionApp.clear();
            System.out.println("Slots init");
            application.runInit = false;
        }
    }

    @Override
    public void run() {
//        SaxionApp.stopSound("resources/audio/spin.wav");
        SaxionApp.clear();
        this.application.drawLayout();
        SaxionApp.drawImage("resources/slot_machine/slot-machine1.png", 0, 100);
        SaxionApp.drawImage("resources/slot_machine/slot-machine2.png", 1, 100);

        if (!this.values.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                SaxionApp.drawImage("resources/slot_machine/slot-symbol" + values.get(i) + ".png", (i * 130) + 235, 400);
            }
            if (values.stream().allMatch(i -> Objects.equals(i, values.getFirst()))) {
                SaxionApp.drawBorderedText("You won! +" + values.getFirst() + "00", 100, 50, 50);
            }
        }
    }
}
