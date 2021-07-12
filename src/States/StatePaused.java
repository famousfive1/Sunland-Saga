package States;

import GUI.UIParts;

import javax.swing.*;

public class StatePaused extends GameState{
    GameState save;

    public StatePaused(GameState save) {
        this.save = save;

        display = new UIParts();

        JLabel l = new JLabel(new ImageIcon("/assets/MainMenu.png"));
        l.setBounds(0, 0, 800, 600);
        display.addComponent(l);

        JButton Resume = new JButton("Resume");
        Resume.setBounds(400-65, 250-15, 150, 50);
        Resume.addActionListener(e -> Game.setCurrentState(save));
        display.addComponent(Resume);

        JButton backToMainMenu = new JButton("Quit Game");
        backToMainMenu.setBounds(400-65, 350-15, 150, 50);
        backToMainMenu.addActionListener(e -> Game.setCurrentState(new StateLost()));
        display.addComponent(backToMainMenu);
    }


    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            Game.setCurrentState(save);
        else System.out.println("Press buttons instead!!");
    }

    @Override
    public void playMusic() {

    }
}
