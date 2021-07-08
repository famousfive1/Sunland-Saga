package Entity;

import States.Game;

import java.awt.image.BufferedImage;

public class Player extends Character {

    static String[] map ={
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

    public Player(BufferedImage img) {
        super(img);
    }

    public boolean move(char typed) {

        int newX=this.x;
        int newY=this.y;




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
        return newX>=0&&newY>=0 && newX<16&&newY<12&&map[newY].charAt(newX)=='0';
    }
}
