package States;

import GUI.UIParts;


public abstract class GameState {

    public enum States {MAIN_MENU, COMBAT, WORLD, PAUSED}

    UIParts display;

    public UIParts getDisplay() {
        return display;
    }

    public abstract void handleInput(char typed);
}
