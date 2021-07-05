package GUI;

import States.GameState;

import javax.swing.*;
import java.util.ArrayList;

public class UIParts {

    private ArrayList<JComponent> components;
    private int width, height;

    public UIParts() {
        this.width = 640;
        this.height = 480;
        components = new ArrayList<>();
    }

    public void addComponent(JComponent c) {
        components.add(c);
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
