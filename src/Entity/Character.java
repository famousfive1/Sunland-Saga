package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    protected int x, y;
    protected BufferedImage img;
    protected int health_bar_value = 100;

    public boolean checkHealth()
    {
        return health_bar_value > 0;
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

    public Image getImgScaled() {
        return img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    }
}
