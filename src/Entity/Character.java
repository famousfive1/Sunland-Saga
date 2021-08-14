package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    protected int x, y;
    protected BufferedImage img;
    protected int health;
    private final String name;

    public Character(String name, BufferedImage img, int health) {
        this.name = name;
        this.img = img;
        x = 0;
        y = 0;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }


    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);

        //System.out.println(this.name + " takes " + damage+ " damage");
        //System.out.println(this.name + "'s Current health: "+ health);

    }

    public BufferedImage getImg() {
        return img;
    }

    public Image getImgScaled() {
        return img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    }
}
