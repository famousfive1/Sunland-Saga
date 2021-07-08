package States;

import Entity.Player;
import GUI.UIParts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*Load map
Load objects pngs 
Load character/move*/

public class StateWorld extends GameState{
    Player player;
    private int checkPointX = 15;
    private int checkPointY = 11;
    private String[] map ={
            "0000000000000000",
            "0000000000000000",
            "0011000100000000",
            "0001000010000000",
            "0000010000000000",
            "0000000000000000",
            "0000000010000000",
            "0000010000000000",
            "0000000111000000",
            "0000000111000000",
            "0000000000000000",
            "0000000000000000" };

    public StateWorld() //forestmap1
    {
        //load map forest1
        display = new UIParts();

        JLabel background = new JLabel(new ImageIcon(display.loadImg("/assets/Forest1Test.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

        //load player
        player = new Player(display.loadImg("/assets/PlayerCharacter.png"));
        player.setMap(map);
        display.addCharacter(player);
    }

    @Override
    public void handleInput(char typed) {
        System.out.println(typed);
        if(player.move(typed)) {
            //map checkpoint
            if(player.getX() == checkPointX && player.getY() == checkPointY) {
                System.out.println("Checkpoint reached");
            }
            Game.updateWindow();
        }
    }

    public int getCheckPointX() {
        return checkPointX;
    }

    public int getCheckPointY() {
        return checkPointY;
    }
}
