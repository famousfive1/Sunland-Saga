package States;

import GUI.UIParts;
import Utility.MediaPlayer;
import Entity.Player;

import javax.swing.*;

public class StateMainMenu extends GameState {

    public StateMainMenu() {
        display = new UIParts();

        JButton b = new JButton("Start New Game");
        b.setBounds(400-55, 350-15, 130, 50);
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
            int ch = JOptionPane.showOptionDialog(null, "Choose your starting weapon: ", "Weapon",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new String[] {"Sword", "Bow", "Battle Axe"}, null);
            if(ch == -1) return;
            else Player.setWeapon(ch);
            ch = JOptionPane.showOptionDialog(null, "Choose difficulty: ", "Difficulty",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new String[] {"Easy", "Medium", "Hard"},null);
            if(ch == -1) return;

            StateWorld world = new StateWorld(name, "PlayerCharacter.png");
            Game.setCurrentState(world);

            if(ch == 0) world.setDifficulty(2, 5);
            else if(ch == 1) world.setDifficulty(4, 3);
            else if(ch == 2) world.setDifficulty(7, 2);
        }
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Dont type");
    }

    private void showHelp() {
        System.out.println("Help");
        String gameInstructions = """

                1. This is a Quest game, so you have to complete quests to win.
                2. Quests will be given by NPCs in the towns
                3. You can complete the quests by fighting enemies in the forest outside th town
                4. You have only three lives throughout the game
                5. The player starts in the town1 when a new game starts
                6. You can go to different maps by moving your character to the indicated arrows in each map
                7. You can pause in any MAPs by pressing p, you can also quit the game or start again from there
                8. You cannot pause in the beginning or during any fights
                9. There is no time restriction
                10. You will discover more features in the game as you progress

                ALL THE BEST""";

        JOptionPane.showOptionDialog(null, gameInstructions, "Help: Game rules",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"OK"}, null);
    }

    @Override
    public void playMusic() {
        MediaPlayer.playInBackground("/assets/MainMenu.mp3");
    }
}