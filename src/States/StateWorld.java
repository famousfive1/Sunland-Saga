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

        pause = new JButton("pause");
        pause.setBounds(720, 0, 80, 40);
        pause.addActionListener(e -> Game.setCurrentState(new StatePaused(this)));
        display.addComponent(pause);

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

        for(int m = 0 ; m<12; m++){
            for(int n = 0 ; n<16; n++){
                System.out.print(map[m][n]);
            }
            System.out.println();
        }
    }

    @Override
    public void handleInput(char typed) {
        if(player.move(typed, map)) {
            Game.updateWindow();

            if (map[player.getY()][player.getX()] == 2) {
                map[player.getY()][player.getX()] = 0;
                Game.setCurrentState(new StateCombat(player, generateEnemy(), this));
            }

        }
    }

    void changeMap(String path) {

    }

    private Character generateEnemy() {
        // Do more stuff
        return new Character("Enemy", display.loadImg("/assets/enemy.png"), 900);
    }

    public void pauseButtonCLicked() {

    }
}
