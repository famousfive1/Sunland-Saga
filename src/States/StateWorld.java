package States;

import Entity.Character;
import Entity.NPCs;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
Load map
Load objects pngs 
Load character/ move
*/

public class StateWorld extends GameState{

    Player player;
    private int questCount;
    private int questType = -1;
    private int totalQuestCount;

    // Map related stuff
    int[][] map;
    String[] connections;
    JLabel background;
    JProgressBar questProgressBar;
    JTextArea questDisplay;
    JTextArea livesDisplay;
    JDialog npcDialog;
    String [] enemies = {"Exterminator", "Oberon", "Subtilizer", "Dog", "Wolf"};


    public StateWorld(String playerName, String playerIcon) //forestmap1
    {
        //load map forest1
        display = new UIParts();

        //load player
        player = new Player(playerName, display.loadImg("/assets/" + playerIcon));
        display.addCharacter(player);

        questDisplay = new JTextArea("Current quest : none");
        questDisplay.setBounds(10, 10 , 110, 20);
        display.addComponent(questDisplay);

        livesDisplay = new JTextArea("Lives left : " + 3);
        livesDisplay.setBounds(130, 10, 80, 20);
        display.addComponent(livesDisplay);

        questProgressBar = new JProgressBar();
        questProgressBar.setValue(questCount);
        questProgressBar.setStringPainted(true);
        questProgressBar.setForeground(new Color(0, 75, 180));
        questProgressBar.setString("QUEST PROGRESS : kills : 0");
        questProgressBar.setBounds(610, 10, 180, 20);
        display.addComponent(questProgressBar);

        background = new JLabel();
        display.addComponent(background);

        changeMap("Town1Test");

        questCount = 0;
    }

    @Override
    public void playMusic() {
        MediaPlayer.playInBackground("/assets/homeMusic.wav");
    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            pauseGame();
        else if(player.move(typed, map)) {
            int x = player.getX(), y = player.getY();
            if (map[y][x] == 2) {
                map[y][x] = 0;
                Character randomEnemy = generateEnemy();
                JOptionPane.showOptionDialog(null, "You encountered an :  " + randomEnemy.getName(), "Enemy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        new String[] {"To Arms !!!"}, null);
                Game.setCurrentState(new StateCombat(player, randomEnemy, this));
            }

            else if(map[y][x] == 5)
            {
                System.out.println("NPC encountered");

                String dialogue = """
                        Jason wants to talk to you\s
                        press space if you want to reply
                        Press any other button to ignore
                        """;

                JOptionPane optionPane = new JOptionPane(dialogue, JOptionPane.INFORMATION_MESSAGE,
                        JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

                JDialog dialog = new JDialog();
                dialog.setTitle("Message by Jason");
                dialog.setModal(true);

                dialog.setContentPane(optionPane);

                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setLocationRelativeTo(null);

                dialog.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyChar() > 0) {
                            dialog.dispose();
                            if(e.getKeyChar() == ' ')
                                encounterNPC();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                dialog.setVisible(true);
            }
            else if(map[y][x] >= 6) {
                if(map[y][x] == 6) player.setXY(15, y);
                else if(map[y][x] == 7) player.setXY(x, 11);
                else if(map[y][x] == 8) player.setXY(0, y);
                else player.setXY(x, 0);
                changeMap(connections[map[y][x] - 6]);
            }
            Game.updateWindow();
        }
    }

    private void changeMap(String mapPart) {
        background.setIcon(new ImageIcon(display.loadImg("/assets/"+mapPart+".png")));
        background.setBounds(0, 0, 800, 600);

        //Load collision map
        map = new int[12][16]; int i = 0;

        ArrayList<String> file = Game.loadFile("/assets/"+mapPart+".txt");
        while(i < 12) {
            assert file != null;
            String s = file.get(i);
            for (int j = 0; j < 16; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
            i++;
        }

        connections = new String[4];
        connections[0] = file.get(i++);
        connections[1] = file.get(i++);
        connections[2] = file.get(i++);
        connections[3] = file.get(i++);

        int numEnemies = Integer.parseInt(file.get(i));

        //Add enemies to the map
        i  = 0;
        while(i < numEnemies) {
            int x = (int)(Math.random() * 16);
            int y = (int)(Math.random() * 12);

            if(map[y][x] == 0) {
                map[y][x] = 2;
                i++;
            }
        }

        player.restoreHealth();

        Game.updateWindow();
    }

    private Character generateEnemy() {
        // Do more stuff
        int randomEnemyIndex = (int)( Math.random()*enemies.length);
        return new Character(enemies[randomEnemyIndex], display.loadImg("/assets/enemy.png"),
                Math.min(1000, 500 + (int)(Math.random()*randomEnemyIndex*100*randomEnemyIndex)));
    }

    private void pauseGame() {
        Game.setCurrentState(new StatePaused(this));
    }

    public void setQuestCount(int questCount) {
        this.questCount = questCount;
        totalQuestCount = Math.max(totalQuestCount, questCount);
    }

    public void setQuestType(int questType) {
        this.questType = questType;
    }

    public int getQuestCount() {
        return questCount;
    }

    public void increaseQuestProgressBar() {
        if(questType != -1) {
            questProgressBar.setString("QUEST PROGRESS : kills : " + (3 - questCount));
            questProgressBar.setValue(((3 - questCount)* 100)/3);
        }
    }

    public void setQuestDisplay() {
        if(questType != -1)
            questDisplay.setText("Current Quest : " + questType);
        else
            questDisplay.setText("Current Quest : none");
    }

    public void encounterNPC()
    {
        if(questType == -1)
        {
            NPCs npc = new NPCs("EncounterNPC", display.loadImg("/assets/enemy.png"));
            int a = (int)(Math.random()*5);
            int option = JOptionPane.showOptionDialog(null,
                    npc.getQuestDialouge(a), "NPC",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new String[] {"accept", "decline"}, null);

            if(option == 0)
            {
                questCount = 3;
                questType = a;
                questDisplay.setText("Current quest : " + questType);
                JOptionPane.showOptionDialog(null, "QUEST accepted! \nCurrent quest : To " +
                        npc.getQuestDialouge(a), "Quest", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }
            else
                JOptionPane.showOptionDialog(null, "QUEST declined!", "Quest", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
        }
        else{
            JOptionPane.showOptionDialog(null, "You meet a friendly man", "NPC",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
            new String[] {"Looks like you are already helping someone"}, null);
        }
    }

}
