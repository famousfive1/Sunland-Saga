package Entity;

import java.awt.image.BufferedImage;

public class Character {
    protected int x, y;
    protected BufferedImage img;
    protected int health_bar_value = 100;

    public boolean checkHealth()
    {
        if(health_bar_value <=0)
        return false;
        else
        return true;
    }

    public Character(BufferedImage img) {
        this.img = img;
        x = 0;
        y = 0;
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

    public BufferedImage getImg() {
        return img;
    }
}
