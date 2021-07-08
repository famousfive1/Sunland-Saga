package Entity;

import java.awt.image.BufferedImage;

public class Player extends Character {

    public Player(BufferedImage img) {
        super(img);
    }

    public boolean move(char typed, String[] map) {
        int newX = this.x;
        int newY = this.y;

        switch (typed) {
            case 'w':
                newY--;
                break;
            case 's':
                newY++;
                break;
            case 'a':
                newX--;
                break;
            case 'd':
                newX++;
                break;
            default:
                return false;
        }

        if(isValid(newX, newY, map)){
            this.x = newX;
            this.y=newY;
            return true;
        }
        return false;
    }

    static boolean isValid(int newX, int newY, String[] map){
        return newX >= 0 && newY >= 0 && newX < 16 && newY < 12 && map[newY].charAt(newX) == '0';
    }
}
