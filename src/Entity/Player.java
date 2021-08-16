package Entity;

import java.awt.image.BufferedImage;

public class Player extends Character {

    private int lives = 3;          // Lives is set based on difficulty
    private boolean inQuest = false;
    static private String weapon = "";

    public Player(String name, BufferedImage img){
        super(name, img, 100);
    }

    static public void setWeapon(int a)
    {
        String[] s = {"Greatsword", "Long Bow", "War Hammer"};
        weapon = s[a];
    }

    static public String getWeapon()
    {
        return weapon;
    }

    public void reactToMove(char typed)
    {
        switch (typed) {
            case 'e':
                //System.out.println("You deal E damage");
                break;
            case 'f':
                //System.out.println("You dash away but take F damage");
                takeDamage(10);

                break;
        }
    }

    // Movement code for character
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

        // Move only if valid
        if(isValid(newX, newY, map)){
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }

    static boolean isValid(int newX, int newY, int[][] map){
        return newX >= 0 && newY >= 0 && newX < 16 && newY < 12 && map[newY][newX] != 1;
    }

    public void restoreHealth(){
        this.health = 100;
    }

    public boolean isInQuest() {
        return inQuest;
    }

    public void setInQuest(boolean inQuest) {
        this.inQuest = inQuest;
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLive() {
        this.lives--;
    }

    public void setLives(int live) { this.lives = live; }
}
