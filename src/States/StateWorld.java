package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;

import javax.swing.*;

/*
Load map
Load objects pngs 
Load character/ move
*/

public class StateWorld extends GameState{
    Player player;
    private int questCounter;

    // Map related stuff
    int[][] map;
    JLabel background;
    JButton pause;

    public StateWorld() //forestmap1
    {

        //load map forest1
        display = new UIParts();

        background = new JLabel(new ImageIcon(display.loadImg("/assets/Forest1Test.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

        //load player
        player = new Player("Get Player Name From User", display.loadImg("/assets/PlayerCharacter.png"));
        display.addCharacter(player);

        //Load collision map
        map = new int[12][16]; int i = 0;

        
        for(String s : Game.loadFile("/assets/Forest1Test.txt")) {
            for (int j = 0; j < 16; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
            i++;
        }

        //Add 10 enemies to the map
        i  = 0;
        while(i != 10){
            int x = (int)(Math.random() * 16);
            int y = (int)(Math.random() * 12);

            if(map[y][x] != 1)
                map[y][x] = 2;
            i++;
        }

        questCounter = 0;
    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            pauseGame();
        else if(player.move(typed, map)) {
            Game.updateWindow();

            if (map[player.getY()][player.getX()] == 2) {
                map[player.getY()][player.getX()] = 0;
                Game.setCurrentState(new StateCombat(player, generateEnemy(), this));
            }

        }
    }

    private void changeMap(String path) {

    }

    private Character generateEnemy() {
        // Do more stuff
        return new Character("Enemy", display.loadImg("/assets/enemy.png"), 900);
    }

    private void pauseGame() {
        Game.setCurrentState(new StatePaused(this));
    }

    public void setQuestCounter(int questCounter) {
        this.questCounter = questCounter;
    }

    public int getQuestCounter() {
        return questCounter;
    }
}
