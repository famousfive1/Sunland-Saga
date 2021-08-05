package States;
import GUI.UIParts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StateWin extends GameState{
    JLabel background;

    public StateWin() {


        display = new UIParts();
        background = new JLabel(new ImageIcon(
                display.loadImg("/assets/winningDialogue.png").getScaledInstance(800, 600, Image.SCALE_DEFAULT)));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            Game.setCurrentState(new StatePaused(this));
    }

    @Override
    public void playMusic() {

    }
}
