package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;

import javax.swing.*;

public class StateCombat extends GameState {
    Player player;
    Character enemy;
    StateWorld save;

    public StateCombat(Player player, Character enemy, StateWorld save) {
        this.save = save;

        display = new UIParts();

        this.player = player;
        this.enemy = enemy;
        JLabel l = new JLabel(new ImageIcon(player.getImgScaled()));
        l.setBounds(100, 100, 100, 100);
        display.addComponent(l);

        JTextArea healthIndicatorPlayer = new JTextArea("Health : " + player.getHealth());
        healthIndicatorPlayer.setBounds(100, 220, 80, 40);
        display.addComponent(healthIndicatorPlayer);

        l = new JLabel(new ImageIcon(enemy.getImgScaled()));
        l.setBounds(500, 100, 100, 100);
        display.addComponent(l);

        JTextArea healthIndicatorEnemy = new JTextArea("Health : " + enemy.getHealth());
        healthIndicatorEnemy.setBounds(500, 220, 80, 40);
        display.addComponent(healthIndicatorEnemy);


        JButton b = new JButton("Attack 1");
        b.setBounds(200, 400, 80, 40);
        b.addActionListener(e -> {
            attack('e');
            healthIndicatorEnemy.setText("Health : " + enemy.getHealth());
            healthIndicatorPlayer.setText("Health : " + player.getHealth());
        });
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(400, 400, 80, 40);
        b.addActionListener(e -> {
            attack('f');
            healthIndicatorEnemy.setText("Health : " + enemy.getHealth());
            healthIndicatorPlayer.setText("Health : " + player.getHealth());
        });
        display.addComponent(b);

        b = new JButton("After player wins, do this");
        b.setBounds(300, 500, 200, 60);
        b.addActionListener(e -> Game.setCurrentState(save));
        display.addComponent(b);
    }



    @Override
    public void handleInput(char typed) {
        if(typed == 'p')
            Game.setCurrentState(new StatePaused(this));
        else System.out.println("Press buttons instead!!");
    }

     void attack(char usedMove){
        player.usedMove(usedMove);
        enemy.takeDamage(200);

        if(usedMove!='f' )
            player.takeDamage(10 + (int)(Math.random()*40));

        if(!enemy.checkHealth()){
            System.out.println("You Won!!!! Congratulations!!!");
            player.restoreHealth();
            save.setQuestCounter(save.getQuestCounter() + 1);
            if(save.getQuestCounter()==1)
                Game.setCurrentState(new StateWin());

            else
             Game.setCurrentState(save);
            //TODO 1. Do something appropriate here
        }

        if(!player.checkHealth()){
            System.out.println("You Died!!! Sorry!!!!");
            player.restoreHealth();
            Game.setCurrentState(save);
            //TODO 2. Do something appropriate here
        }
    }
}
