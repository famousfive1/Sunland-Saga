package States;

import GUI.UIParts;

import javax.swing.*;

public class StateMainMenu extends GameState {

    public StateMainMenu() {
        display = new UIParts();

        JButton b = new JButton("Play");
        b.setBounds(400-35, 350-15, 90, 50);
        b.addActionListener(e -> buttonPlayClicked());
        display.addComponent(b);

        b = new JButton("Exit");
        b.setBounds(400-35, 420-15, 90, 50);
        b.addActionListener(e -> System.exit(0));
        display.addComponent(b);

        JLabel background = new JLabel(new ImageIcon(display.loadImg("/assets/MainMenu.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);
    }

    public static void buttonPlayClicked() {
        System.out.println("State Changed: Main Menu -> World");
        Game.setCurrentState(new StateWorld());
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("I dont care if you type");
    }
}
