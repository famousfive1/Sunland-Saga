package Entity;

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

    public boolean checkHealth() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);

    }

    public BufferedImage getImg() {
        return img;
    }
}
