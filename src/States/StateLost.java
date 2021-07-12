package States;

import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
import java.awt.*;

public class StateLost extends GameState{
    JLabel background;

    public StateLost() {
        display = new UIParts();

        background = new JLabel();
        background.setForeground(new Color(255, 210, 255));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

        JButton b = new JButton("Back to Main Menu");
        b.setBounds(400-80, 200-25, 180, 50);
        b.addActionListener(e -> Game.setCurrentState(new StateMainMenu()));
        display.addComponent(b);

        b = new JButton("Exit");
        b.setBounds(400-35, 270-15, 90, 50);
        b.addActionListener(e -> System.exit(0));
        display.addComponent(b);
    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            Game.setCurrentState(new StatePaused(this));
    }

    @Override
    public void playMusic() {
        MediaPlayer.stop();
    }
}
