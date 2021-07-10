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
        if(typed == 'p')
            Game.setCurrentState(new StatePaused(this));
        else System.out.println("Press buttons instead!!");
    }

     void attack(char usedMove){
        player.reactToMove(usedMove);
        enemy.takeDamage(200);

        if(usedMove!='f' )
            player.takeDamage(10 + (int)(Math.random()*40));

        /*
        * The code below basically checks if someone won after every move and also checks if the player won the required
        * number of quests to become a champion
        *
        */

        if(enemy.getHealth()==0){
            System.out.println("You Won!!!! Congratulations!!!");
            player.restoreHealth();
            save.setQuestCount(save.getQuestCount() + 1);
            if(save.getQuestCount()==1){
                Game.setCurrentState(new StateWin());

            }


            else
             Game.setCurrentState(save);
            //TODO 1. Do something appropriate here
        }

        if(player.getHealth()==0){
            System.out.println("You Died!!! Sorry!!!!");
            player.restoreHealth();
            Game.setCurrentState(save);
            //TODO 2. Do something appropriate here
        }
    }
}
