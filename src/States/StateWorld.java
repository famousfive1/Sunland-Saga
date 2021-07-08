package States;

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
        player = new Player(display.loadImg("/assets/PlayerCharacter.png"));
        display.addCharacter(player);

        //Load collision map
        map = new String[12]; int j = 0;
        for(String i : Game.loadFile("/assets/Forest1Test.txt"))
            map[j++] = i;
    }

    @Override
    public void handleInput(char typed) {
        player.usedMove(typed);
        if(player.move(typed, map))
            Game.updateWindow();
    }

    void changeMap(String path) {

    }
}
