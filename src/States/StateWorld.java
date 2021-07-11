package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
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

    // Map related stuff
    int[][] map;
    String[] connections;
    JLabel background;
    String [] enemies = {"Exterminator", "Oberon", "Subtilizer", "Dog", "Wolf"};


    public StateWorld(String playerName) //forestmap1
    {
        //load map forest1
        display = new UIParts();

        //load player
        player = new Player(playerName, display.loadImg("/assets/PlayerCharacter.png"));
        display.addCharacter(player);

        background = new JLabel();
        display.addComponent(background);

        changeMap("Town1Test");

        questCount = 0;
    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            pauseGame();
        else if(player.move(typed, map)) {
            int x = player.getX(), y = player.getY();
            if (map[y][x] == 2) {
                map[y][x] = 0;
                MediaPlayer.stop();
                MediaPlayer.playInBackground("src/assets/combatMusic.wav");
                Character randomEnemy = generateEnemy();
                JOptionPane.showOptionDialog(null, "You encountered an :  " + randomEnemy.getName(), "Enemy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        new String[] {"To Arms !!!"}, null);
                Game.setCurrentState(new StateCombat(player, randomEnemy, this));
            }

            if(map[y][x] == 4) {
                JOptionPane quest = new JOptionPane();
                String questMessage = "objectives : \n1 : Kill 3 bandits";
                String rewards = "Rewards : \n+50 xp, +1 health potion";
                int option = JOptionPane.showOptionDialog(null,
                        "Jason has a quest for you! \n\n" + questMessage + "\n\n" + rewards,"Jason's quest",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new String[] {"accept", "refuse"}, null);

                if(option == 0) {
                    System.out.println("Quest accepted");
                    JOptionPane.showMessageDialog(null,
                            "Quest accepted \nCurrent Quest : quest name");
                }
                else if(option == 1) {
                    System.out.println("Quest refused");
                    JOptionPane.showMessageDialog(null, "Quest refused");
                }
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

        Game.updateWindow();
    }

    private Character generateEnemy() {
        // Do more stuff
        int randomEnemyIndex = (int)( Math.random()*enemies.length);
        return new Character(enemies[randomEnemyIndex], display.loadImg("/assets/enemy.png"), Math.min(1000, 500 + (int)(Math.random()*randomEnemyIndex*100*randomEnemyIndex)));
    }

    private void pauseGame() {
        Game.setCurrentState(new StatePaused(this));
    }

    public void setQuestCount(int questCount) {
        this.questCount = questCount;
    }

    public int getQuestCount() {
        return questCount;
    }

    public void setQuestType(int questType){
        this.questType = questType;
    }

}
