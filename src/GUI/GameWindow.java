package GUI;

import States.GameState;

import javax.swing.*;

public class GameWindow {

    JFrame frame;
    GameState currentState;
    UIParts display;

    public GameWindow() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setState(GameState state) {
        currentState = state;
        display = state.getDisplay();
        updateWindow();
    }

    public void updateWindow() {
        frame.getContentPane().removeAll();
        for(JComponent c : display.getComponents())
            frame.add(c);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
