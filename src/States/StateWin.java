package States;
import GUI.UIParts;

import javax.swing.*;

public class StateWin extends GameState{
    JLabel background;

    public StateWin() {


        display = new UIParts();

        background = new JLabel(new ImageIcon(display.loadImg("/assets/winningDialogue.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

    }

    @Override
    public void handleInput(char typed) {

    }
}
