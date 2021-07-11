package States;

import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;

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

    public static void buttonPlayClicked() {
        String name = JOptionPane.showInputDialog("Enter you character name: ");
        if(name != null && !name.equals("")) {
            MediaPlayer.playInBackground("/assets/homeMusic.wav");
            int ch = JOptionPane.showOptionDialog(null, "Choose your starting weapon: ", "Weapon",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new String[] {"Sword", "Bow", "Battle Axe"}, null);
            if(ch == 0)      Game.setCurrentState(new StateWorld(name, "PlayerCharacter.png"));
            else if(ch == 1) Game.setCurrentState(new StateWorld(name, "PlayerCharacter.png"));
            else if(ch == 2) Game.setCurrentState(new StateWorld(name, "PlayerCharacter.png"));
        }
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Dont type");
    }

    private void showHelp() {
        System.out.println("Help");
    }
}