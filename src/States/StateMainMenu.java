package States;

import GUI.UIParts;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class StateMainMenu extends GameState {

    public StateMainMenu() {
        display = new UIParts();

        JButton b = new JButton("Play");
        b.setBounds(400-35, 350-15, 90, 50);
        b.addActionListener(e -> buttonPlayClicked());
        display.addComponent(b);

        b = new JButton("Help");
        b.setBounds(400-35, 420-15, 90, 50);
        b.addActionListener(e -> showHelp());
        display.addComponent(b);

        b = new JButton("Exit");
        b.setBounds(400-35, 490-15, 90, 50);
        b.addActionListener(e -> System.exit(0));
        display.addComponent(b);

        JLabel background = new JLabel(new ImageIcon(display.loadImg("/assets/MainMenu.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);
    }

    public void buttonPlayClicked() {
        String name = JOptionPane.showInputDialog("Enter you character name: ");
        if(name != null)
            Game.setCurrentState(new StateWorld(name));

    }

    @Override
    public void handleInput(char typed) {
        System.out.println("I dont care if you type");
    }

    private void showHelp() {
        System.out.println("Help");
    }


}
