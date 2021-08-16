package GUI;

import Entity.Character;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UIParts {

    // Hold all the elements that need to be displayed on screen
    private final ArrayList<JComponent> components;
    private final ArrayList<Character> entities;
    private final LayoutManager layout;

    public UIParts() {
        components = new ArrayList<>();
        entities = new ArrayList<>();
        layout = null;
    }

    public void addComponent(JComponent c) {
        c.setFocusable(false);
        components.add(c);
    }

    public void addCharacter(Character c) {
        entities.add(c);
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }

    public LayoutManager getLayout() {
        return layout;
    }

    public ArrayList<Character> getEntities() {
        return entities;
    }

    // Load image file
    public BufferedImage loadImg(String path) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPicture;
    }
}
