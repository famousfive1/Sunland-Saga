package GUI;

import Entity.Character;
import States.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow {

    JFrame frame;
    GameState currentState;
    UIParts display;

    public GameWindow() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 27)
                    currentState.handleInput('p');
                else currentState.handleInput(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
