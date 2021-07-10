package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;

public class StateCombat extends GameState {
    Player player;
    Character enemy;
    StateWorld save;
    JProgressBar healthIndicatorPlayer;
    JProgressBar healthIndicatorEnemy;
    String fullHealthPlayer;
    String fullHealthEnemy;
    public StateCombat(Player player, Character enemy, StateWorld save) {
        this.save = save;

        display = new UIParts();

        this.player = player;
        this.enemy = enemy;
        JLabel l = new JLabel(new ImageIcon(player.getImgScaled()));
        l.setBounds(100, 100, 100, 100);
        display.addComponent(l);

        healthIndicatorPlayer = new JProgressBar();
        healthIndicatorPlayer.setStringPainted(true);
        fullHealthPlayer = Integer.toString(player.getHealth());
        healthIndicatorPlayer.setString(player.getHealth() + "/" + fullHealthPlayer);
        healthIndicatorPlayer.setValue(player.getHealth());
        healthIndicatorPlayer.setBounds(100, 220, 160, 30);
        display.addComponent(healthIndicatorPlayer);

        l = new JLabel(new ImageIcon(enemy.getImgScaled()));
        l.setBounds(500, 100, 100, 100);
        display.addComponent(l);

        healthIndicatorEnemy = new JProgressBar();
        healthIndicatorEnemy.setStringPainted(true);
        fullHealthEnemy = Integer.toString(enemy.getHealth());
        healthIndicatorEnemy.setString(enemy.getHealth() + "/" + fullHealthEnemy);
        healthIndicatorEnemy.setValue(enemy.getHealth() / Integer.parseInt(fullHealthEnemy) * 100);
        healthIndicatorEnemy.setBounds(500, 220, 160, 30);
        display.addComponent(healthIndicatorEnemy);


        JButton b = new JButton("Attack 1");
        b.setBounds(200, 400, 80, 40);
        b.addActionListener(e -> {
            attack('e');
        });
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(400, 400, 80, 40);
        b.addActionListener(e -> {
            attack('f');

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
        player.reactToMove(usedMove);
        enemy.takeDamage(200);

        if(usedMove!='f' )
            player.takeDamage(10 + (int)(Math.random()*40));


         healthIndicatorEnemy.setValue(enemy.getHealth() / 8);
         healthIndicatorEnemy.setString(enemy.getHealth() + "/" + fullHealthEnemy);
         healthIndicatorPlayer.setValue(player.getHealth());
         healthIndicatorPlayer.setString(player.getHealth() + "/" + 100);
        /*
        * The code below basically checks if someone won after every move and also checks if the player won the required
        * number of quests to become a champion
        *
        */

        if(enemy.getHealth()==0){
            MediaPlayer.stop();
            MediaPlayer.playInBackground("src/assets/homeMusic.wav");
            System.out.println("You Won!!!! Congratulations!!!");
            player.restoreHealth();
            save.setQuestCount(save.getQuestCount() + 1);
            if(save.getQuestCount()==3) {
                Game.setCurrentState(new StateWin());
            }
            else
             Game.setCurrentState(save);
            //TODO 1. Do something appropriate here
        }

        if(player.getHealth()==0){
            MediaPlayer.stop();
            MediaPlayer.playInBackground("src/assets/homeMusic.wav");
            System.out.println("You Died!!! Sorry!!!!");
            player.restoreHealth();
            Game.setCurrentState(save);
            //TODO 2. Do something appropriate here
        }
    }
}
