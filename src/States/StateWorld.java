package States;

import Entity.Character;
import Entity.NPCs;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
Load map
Load objects pngs 
Load character/ move
*/

public class StateWorld extends GameState{

    Player player;
    private int currentQuestKillCount;
    private int questType = -1;
    private int totalQuestCount;
    private int neededQuests;

    // Map related stuff
    int[][] map;
    String[] connections;
    JLabel background;
    JProgressBar questProgressBar;
    JTextArea questDisplay;
    JTextArea livesDisplay;
    JDialog npcDialog;
    String [] enemies = {"Exterminator", "Bandit", "Subtilizer", "Dog", "Wolf"};
    String currentQuestEnemy = "";
    int[] questKillTargets = {3, 3, 5, 5, 3};

    public void setDifficulty(int quests, int lives) {
        this.neededQuests = quests;
        this.player.setLives(lives);
        livesDisplay.setText("Lives left: " + player.getLives());
        questDisplay.setText("Quests : 0 / " + neededQuests);
    }

    public String getCurrentQuestEnemy() {
        return currentQuestEnemy;
    }

    public StateWorld(String playerName, String playerIcon) //forestmap1
    {
        //load map forest1
        display = new UIParts();

        //load player
        player = new Player(playerName, display.loadImg("/assets/" + playerIcon));
        display.addCharacter(player);

        questDisplay = new JTextArea("Quests : 0 / " + neededQuests);
        questDisplay.setBounds(10, 10 , 110, 20);
        display.addComponent(questDisplay);

        livesDisplay = new JTextArea("Lives left : " + player.getLives());
        livesDisplay.setBounds(130, 10, 80, 20);
        display.addComponent(livesDisplay);

        questProgressBar = new JProgressBar();
        questProgressBar.setValue(currentQuestKillCount);
        questProgressBar.setStringPainted(true);
        questProgressBar.setForeground(new Color(0, 75, 180));
        questProgressBar.setString("QUEST PROGRESS : kills : 0");
        questProgressBar.setBounds(610, 10, 180, 20);
        display.addComponent(questProgressBar);

        background = new JLabel();
        display.addComponent(background);

        changeMap("Town1Test");

        currentQuestKillCount = 0;
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
            if (map[y][x] == 2)
                handleEnemy(x, y);

            else if(map[y][x] == 5)
                handleNPC();

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

    private void handleNPC() {
        MediaPlayer.playSfx("/assets/sfx/NpcEncounter.wav");
        System.out.println("NPC encountered");

        String dialogue = """
                Village Elder wants to talk to you?\s
                press space if you want to reply
                Press any other button to ignore
                """;

        JOptionPane optionPane = new JOptionPane(dialogue, JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        JDialog dialog = new JDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Message by Jason");
        dialog.setModal(true);

        dialog.setContentPane(optionPane);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();

        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() > 0) {
                    dialog.dispose();
                    if(e.getKeyChar() == ' ') {
                        encounterNPC();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        dialog.setVisible(true);
    }

    private void handleEnemy(int x, int y) {
        map[y][x] = 0;
        Character randomEnemy = generateEnemy();
        MediaPlayer.stop();
        MediaPlayer.playInBackground("/assets/combatMusic.wav");
        JOptionPane.showOptionDialog(null, "You encountered an :  " + randomEnemy.getName(), "Enemy",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                new String[] {"To Arms !!!"}, null);


        Game.setCurrentState(new StateCombat(player, randomEnemy, this));
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
        String enemyCharacter = "";
        int randomEnemyIndex = (int)( Math.random()*enemies.length);

        if(randomEnemyIndex == 0)
            enemyCharacter = "/assets/enemyCharacters_exterminator.png";
        else if(randomEnemyIndex == 1)
            enemyCharacter = "/assets/enemyCharacters_bandit.png";
        else if(randomEnemyIndex == 2)
            enemyCharacter = "/assets/enemyCharacters_subtilizer.png";
        else if(randomEnemyIndex == 3)
            enemyCharacter = "/assets/enemyCharacters_dog.png";
        else if(randomEnemyIndex == 4)
            enemyCharacter = "/assets/enemyCharacters_wolf.png";

        return new Character(enemies[randomEnemyIndex], display.loadImg(enemyCharacter),
                Math.min(1000, 500 + (int)(Math.random()*randomEnemyIndex*100*randomEnemyIndex)));
    }

    private void pauseGame() {
        Game.setCurrentState(new StatePaused(this));
    }

    public void setCurrentQuestKillCount(int currentQuestKillCount) {
        this.currentQuestKillCount = currentQuestKillCount;

        increaseQuestProgressBar();

        if(this.currentQuestKillCount==questKillTargets[this.questType]){
            System.out.println("Current Quest Completed!!");
            this.currentQuestKillCount = 0;
            setQuestType(-1);
            totalQuestCount++;
            questDisplay.setText("Quests : " + totalQuestCount + " / " + neededQuests);
        }

        if(totalQuestCount==neededQuests){
            System.out.println("Congratulations!! You won the game");
            Game.setCurrentState(new StateWin());
        }
    }

    public void setQuestType(int questType) {
        this.questType = questType;
    }

    public int getQuestType() {
        return questType;
    }

    public int getCurrentQuestKillCount() {
        return currentQuestKillCount;
    }

    public void increaseQuestProgressBar() {
        if(questType != -1) {
            questProgressBar.setString("QUEST PROGRESS : kills : " + currentQuestKillCount);
            questProgressBar.setValue(((currentQuestKillCount)* 100)/questKillTargets[questType]);
        }

        if(currentQuestKillCount==questKillTargets[questType]){
            Timer timer = new Timer(0, e->{
                MediaPlayer.playSfx("/assets/sfx/QuestCompleted.wav");
                JOptionPane.showMessageDialog(null, "You Completed Your Current Quest!");
                questProgressBar.setString("QUEST PROGRESS : kills : " + 0);
                questProgressBar.setValue(0);
                currentQuestEnemy = "";
            });
            timer.setInitialDelay(500);
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void setQuestDisplay() {
            questDisplay.setText("Quests : " + totalQuestCount + " / " + neededQuests);
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
                currentQuestEnemy = enemies[a];
                MediaPlayer.playSfx("/assets/sfx/QuestAcceptRelief.wav");
//                questCount = 3;
                questType = a;
                questDisplay.setText("Quests : " + totalQuestCount + " / " + neededQuests);
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
