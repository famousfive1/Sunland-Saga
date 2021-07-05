import GUI.GameWindow;
import States.GameState;
import States.StateMainMenu;

public class Game {

    static GameWindow window;
    static GameState currentState;

    public static void main(String[] args) {
        window = new GameWindow();
        currentState = new StateMainMenu();
        window.setState(currentState);
        window.updateWindow();
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameState getCurrentState() {
        return currentState;
    }
}
