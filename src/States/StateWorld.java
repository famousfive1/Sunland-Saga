package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
Load map
Load objects pngs 
Load character/ move
*/

public class StateWorld extends GameState{
    Player player;

    // Map related stuff
    String[] map;
    JLabel background;


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
        map = new String[12]; int j = 0;
        for(String i : Game.loadFile("/assets/Forest1Test.txt"))
            map[j++] = i;

        //Add 10 enemies to the map
        int i  = 0;
        while(i!=10){
            int x = (int)(Math.random()*16 );
            int y = (int)(Math.random()*12);

            if(map[y].charAt(x)!='1')
                map[y] = map[y].substring(0, x) + "2" + map[y].substring(x+1, 16);
            i++;
        }

        for(int m = 0 ; m<12; m++){
            for(int n = 0 ; n<16; n++){
                System.out.print(map[m].charAt(n));
            }
            System.out.println();
        }
    }

    @Override
    public void handleInput(char typed) {
        if(typed == 'c')
        {
            Character enemy = generateEnemy( /* args */ );
            Game.setCurrentState(new StateCombat(player, enemy));
        }
        if(player.move(typed, map))
            Game.updateWindow();

        if(map[player.getY()].charAt(player.getX())=='2'){
            Game.setCurrentState(new StateCombat(player, generateEnemy()));
        }
    }

    void changeMap(String path) {

    }

    private Character generateEnemy() {
        // Do more stuff
        return new Character("Enemy", display.loadImg("/assets/PlayerCharacter.png"), (int)(100 +Math.round(Math.random())*900));
    }
}
