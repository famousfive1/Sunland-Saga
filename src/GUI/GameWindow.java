package GUI;

import Entity.Character;
import States.GameState;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 27)
                    System.exit(0);
                currentState.handleInput(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }

    public void setState(GameState state) {
        currentState = state;
        display = state.getDisplay();
        updateWindow();
    }

    public void updateWindow() {
        frame.getContentPane().removeAll();

        frame.setLayout(display.getLayout());

        for(Character c : display.getEntities()) {
            JLabel picLabel = new JLabel(new ImageIcon(c.getImg()));
            picLabel.setBounds(c.getX()*50, c.getY()*50, 50, 50);
            frame.add(picLabel);
        }

        for(JComponent c : display.getComponents())
            frame.add(c);

        SwingUtilities.updateComponentTreeUI(frame);
    }
}
