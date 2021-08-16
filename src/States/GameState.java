package States;

import GUI.UIParts;

public abstract class GameState {

    // Abstract class inherited by all others

    UIParts display;

    public UIParts getDisplay() {
        return display;
    }

    public abstract void handleInput(char typed);

    public abstract void playMusic();
}
