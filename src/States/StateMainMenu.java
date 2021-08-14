package States;

import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

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
            int chProfession = JOptionPane.showOptionDialog(null, "Choose your character profession: ", "Weapon",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new ImageIcon[] {new ImageIcon("src/assets/playerCharacter_knight.png"),
                            new ImageIcon("src/assets/playerCharacter_archer.png"),
                            new ImageIcon("src/assets/playerCharacter_warrior.png")}, null);
            if(chProfession == -1) return;
            String ch = "";
            if(chProfession == 0) { ch = "playerCharacter_knight.png"; Player.setWeapon(0); }
            else if(chProfession == 1) { ch = "playerCharacter_archer.png"; Player.setWeapon(1); }
            else if(chProfession == 2) { ch = "playerCharacter_warrior.png"; Player.setWeapon(2); }

            int ch_difficulty = JOptionPane.showOptionDialog(null,
                    "Choose difficulty:\n" +
                    "Easy   -(total lives = 5, total quests = 2)\n" +
                    "Medium -(total lives = 3, total quests = 4)\n" +
                    "Hard   -(total lives = 2, total quests = 7)\n",
                    "Difficulty",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new String[] {"Easy", "Medium", "Hard"},null);
            if(ch_difficulty == -1) return;

            StateWorld world = new StateWorld(name, ch);
            Game.setCurrentState(world);

            if(ch_difficulty == 0) world.setDifficulty(2, 5);
            else if(ch_difficulty == 1) world.setDifficulty(4, 3);
            else if(ch_difficulty == 2) world.setDifficulty(7, 2);
        }
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Don't type");
    }

    private void showHelp() {
        System.out.println("Help");
        String gameInstructions = "\n" +
                                  "1. This is a Quest game, so you have to complete quests to win.\n" +
                                  "2. Quests will be given by NPCs in the towns\n" +
                                  "3. You can complete the quests by fighting enemies in the forest outside th town\n" +
                                  "4. You have only three lives throughout the game\n" +
                                  "5. The player starts in the town1 when a new game starts\n" +
                                  "6. You can go to different maps by moving your character to the indicated arrows in each map\n" +
                                  "7. You can pause in any MAPs by pressing p, you can also quit the game or start again from there\n" +
                                  "8. You cannot pause in the beginning or during any fights\n" +
                                  "9. There is no time restriction\n" +
                                  "10. You will discover more features in the game as you progress\n" +
                                  "\n" +
                                  "ALL THE BEST";

        JOptionPane.showOptionDialog(null, gameInstructions, "Help: Game rules",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"OK"}, null);
    }

    @Override
    public void playMusic() {
        MediaPlayer.playInBackground("/assets/MainMenu.mp3");
    }
}