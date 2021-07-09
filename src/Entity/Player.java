package Entity;

import java.awt.image.BufferedImage;

public class Player extends Character {

    public Player(String name, BufferedImage img){
        super(name, img, 100);
    }

    public void usedMove(char typed)
    {
        switch (typed) {
            case 'e':
                System.out.println("You deal E damage");
                System.out.println("Current health: "+ health);
                break;
            case 'f':
                System.out.println("You dash away but take F damage");
                health -= 10;
                if(!checkHealth())
                {
                    System.out.println("Player has no health left");
                    System.exit(0);
                }
                System.out.println("Current health: "+ health);
                break;
        }
    }

    public boolean move(char typed, int[][] map) {
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

    static boolean isValid(int newX, int newY, int[][] map){
        return newX >= 0 && newY >= 0 && newX < 16 && newY < 12 && (map[newY][newX] == 0 || map[newY][newX] == 2);
    }
}
