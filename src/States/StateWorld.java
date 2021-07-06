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

    public StateWorld() //forestmap1
    {
        //load map forest1
        display = new UIParts();

        JLabel background = new JLabel(new ImageIcon(display.loadImg("/assets/Forest1Test.png")));
        background.setBounds(0, 0, 800, 600);
        display.addComponent(background);

        //load player
        player = new Player(display.loadImg("/assets/PlayerCharacter.png"));
        display.addCharacter(player);
    }

    @Override
    public void handleInput(char typed) {
        System.out.println(typed);
        switch (typed) {
            case 'w':
                player.setY(player.getY() - 1);
                Game.getWindow().updateWindow();
                break;
            case 's':
                player.setY(player.getY() + 1);
                Game.getWindow().updateWindow();
                break;
            case 'a':
                player.setX(player.getX() - 1);
                Game.getWindow().updateWindow();
                break;
            case 'd':
                player.setX(player.getX() + 1);
                Game.getWindow().updateWindow();
                break;
        }
    }
}
