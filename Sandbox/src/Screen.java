import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public abstract class Screen {
    private static final String COLOR = "#129CFE";

    // The following methods are abstract and must be implemented by the subclass
    public abstract void keyboardEvent(KeyboardEvent keyboardEvent, Application application);

    public abstract void mouseEvent(MouseEvent mouseEvent, Application application);

    public abstract void run();

    public abstract void init();
}
