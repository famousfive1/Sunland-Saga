package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.print.attribute.standard.Media;
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

    // Map related stuff
    int[][] map;
    String[] connections;
    JLabel background;

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
                MediaPlayer.play("src/assets/combatMusic.wav");
                JOptionPane.showOptionDialog(null, "You encounter an enemy", "Enemy",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        new String[] {"To Arms !!!"}, null);
                Game.setCurrentState(new StateCombat(player, generateEnemy(), this));
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
        return new Character("Enemy", display.loadImg("/assets/enemy.png"), 900);
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

}
