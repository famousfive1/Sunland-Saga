package States;

import GUI.UIParts;

import javax.swing.*;

public class StatePaused extends GameState{
    StateWorld save;

    public StatePaused(StateWorld save) {
        this.save = save;

        display = new UIParts();

        JLabel l = new JLabel(new ImageIcon("assets/MainMenu.png"));
        l.setBounds(0, 0, 800, 600);
        display.addComponent(l);

        JButton exit = new JButton("Exit");
        exit.setBounds(0, 0, 100, 50);
        exit.addActionListener(e -> System.exit(0));
        display.addComponent(exit);

        JButton restart = new JButton("Restart");
        restart.setBounds(0, 200, 100, 50);
        restart.addActionListener(e -> Game.setCurrentState(new StateWorld()));
        display.addComponent(restart);

        JButton returnBack = new JButton("Return Back");
        returnBack.setBounds(0, 400, 100, 50);
        returnBack.addActionListener(e -> Game.setCurrentState(save));
        display.addComponent(returnBack);
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Press buttons instead!!");
    }
}
