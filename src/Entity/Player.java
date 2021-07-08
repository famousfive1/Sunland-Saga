package Entity;

import States.Game;

import java.awt.image.BufferedImage;

public class Player extends Character {

    static String[] map ={
                   "0000000000000000" ,
                   "0000000000001000" ,
                   "0011000000000000" ,
                   "0001000000000000" ,
                   "0000000000110000" ,
                   "0000001000110000" ,
                   "0000000000000000" ,
                   "0000000000010000" ,
                   "0000000000000000" ,
                   "0000000000000000" ,
                   "0000000000000000" ,
                   "0000000000000000" };

    public Player(BufferedImage img) {
        super(img);
    }

    public void usedMove(char typed)
    {
        switch (typed) {
            case 'e':
                System.out.println("You deal E damage");
                System.out.println("Current health: "+health_bar_value);
                break;
                //return true;
            case 'f':
                System.out.println("You dash away but take F damage");
                health_bar_value -= 10;
                if(!checkHealth())
                {
                    System.out.println("Player has no health left");
                    System.exit(0);
                }
                System.out.println("Current health: "+health_bar_value);
                break;
                //return true;
            default:
                ;
        }
    }

    public boolean move(char typed) {
        
        int newX=this.x;
        int newY=this.y;

        switch (typed) {
            case 'w':
                newY--;
                System.out.println(typed);
                break;
                //return true;
            case 's':
                newY++;
                System.out.println(typed);
                break;
                //return true;
            case 'a':
                newX--;
                System.out.println(typed);
                break;
                //return true;
            case 'd':
                newX++;
                System.out.println(typed);
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
