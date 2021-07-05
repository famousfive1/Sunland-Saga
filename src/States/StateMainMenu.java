package States;

import GUI.UIParts;

import javax.swing.*;

public class StateMainMenu extends GameState {

    public StateMainMenu() {
        display = new UIParts();

        JButton b = new JButton("Click");
        b.setBounds(100, 100, 70, 30);
        b.addActionListener(e -> button1Clicked());
        display.addComponent(b);
    }

    public static void button1Clicked() {
        System.out.println("Clicked");
    }

}
