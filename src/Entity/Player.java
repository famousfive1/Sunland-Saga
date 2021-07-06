package Entity;

import States.Game;

import java.awt.image.BufferedImage;

public class Player extends Character {

    public Player(BufferedImage img) {
        super(img);
    }

    public boolean move(char typed) {
        switch (typed) {
            case 'w':
                y--;
                return true;
            case 's':
                y++;
                return true;
            case 'a':
                x--;
                return true;
            case 'd':
                x++;
                return true;
            default:
                return false;
        }
    }
}
