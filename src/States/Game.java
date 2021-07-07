package States;

import GUI.GameWindow;

public class Game {

    static GameWindow window;
    static GameState currentState;

    public static void main(String[] args) {
        window = new GameWindow();
        currentState = new StateMainMenu();
        window.setState(currentState);
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(GameState GS){
        currentState = GS;
        window.setState(currentState);
    }

    public static void updateWindow() {
        window.updateWindow();
    }
}
