package GUI;

import States.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UIParts {

    private ArrayList<JComponent> components;
    private LayoutManager layout;

    public UIParts() {
        components = new ArrayList<>();
        layout = null;
    }

    public void addComponent(JComponent c) {
        components.add(c);
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }

    public LayoutManager getLayout() {
        return layout;
    }
}
