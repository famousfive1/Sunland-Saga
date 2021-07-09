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

        l = new JLabel(new ImageIcon(enemy.getImgScaled()));
        l.setBounds(500, 100, 100, 100);
        display.addComponent(l);

        JButton b = new JButton("Attack 1");
        b.setBounds(200, 400, 80, 40);
        b.addActionListener(e -> attack('e'));
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(400, 400, 80, 40);
        b.addActionListener(e -> attack('f'));
        display.addComponent(b);

        b = new JButton("After player wins, do this");
        b.setBounds(300, 500, 200, 60);
        b.addActionListener(e -> Game.setCurrentState(save));
        display.addComponent(b);
    }



    @Override
    public void handleInput(char typed) {
        System.out.println("Press buttons instead!!");
    }

     void attack(char usedMove){
        player.usedMove(usedMove);
        enemy.takeDamage(200);
        if(usedMove!='f' )
            player.takeDamage(10 + (int)(Math.random()*40));

        if(!enemy.checkHealth()){
            System.out.println("You Won!!!! Congratulations!!!");
            Game.setCurrentState(save);
            //TODO 1. Do something appropriate here
        }

        if(!player.checkHealth()){
            System.out.println("You Died!!! Sorry!!!!");
            Game.setCurrentState(save);
            //TODO 2. Do something appropriate here
        }
    }
}
