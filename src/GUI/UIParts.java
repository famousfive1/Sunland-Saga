package GUI;

import States.GameState;

import javax.swing.*;
import java.util.ArrayList;

public class UIParts {

    private ArrayList<JComponent> components;

    public UIParts() {
        components = new ArrayList<>();
    }

    public void addComponent(JComponent c) {
        components.add(c);
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }
}
