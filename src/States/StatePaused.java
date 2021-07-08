package States;

import GUI.UIParts;

import javax.swing.*;

public class StatePaused extends GameState{
    StateWorld save;

    public StatePaused(StateWorld save) {
        this.save = save;

        display = new UIParts();

        JLabel l = new JLabel(new ImageIcon("/assets/MainMenu.png"));
        l.setBounds(0, 0, 800, 600);
        display.addComponent(l);

        JButton Resume = new JButton("Resume");
        Resume.setBounds(400-65, 150-15, 150, 50);
        Resume.addActionListener(e -> Game.setCurrentState(save));
        display.addComponent(Resume);

        JButton restart = new JButton("Restart");
        restart.setBounds(400-65, 250-15, 150, 50);
        restart.addActionListener(e -> Game.setCurrentState(new StateWorld()));
        display.addComponent(restart);

        JButton backToMainMenu = new JButton("Back to Main Menu");
        backToMainMenu.setBounds(400-65, 350-15, 150, 50);
        backToMainMenu.addActionListener(e -> Game.setCurrentState(new StateMainMenu()));
        display.addComponent(backToMainMenu);
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Press buttons instead!!");
    }
}
