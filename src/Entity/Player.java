package Entity;

import States.Game;
import States.StateWorld;

import java.awt.image.BufferedImage;

public class Player extends Character {

    static String[] map;

    public Player(BufferedImage img) {
        super(img);
    }

    public void setMap(String[] map) {
        Player.map = map;
    }

    public boolean move(char typed) {

        int newX = this.x;
        int newY = this.y;

        switch (typed) {
            case 'w':
                newY--;
                break;
                //return true;
            case 's':
                newY++;
                break;
                //return true;
            case 'a':
                newX--;
                break;
                //return true;
            case 'd':
                newX++;
                break;
                //return true;
            default:
                return false;
        }

        if(isValid(newX, newY)){
            this.x = newX;
            this.y=newY;
            return true;
        }
        return false;
    }

    static boolean isValid(int newX, int newY){
        return newX >= 0 && newY >= 0 && newX < 16 && newY < 12
                && map[newY].charAt(newX) == '0';
    }
}
