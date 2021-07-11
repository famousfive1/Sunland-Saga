package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
import java.awt.*;

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

        JLabel background = new JLabel(new ImageIcon("/assets/MainMenu.png"));
        background.setBounds(0, 0, 20, 20);
        display.addComponent(background);

        JLabel l = new JLabel(new ImageIcon(player.getImgScaled()));
        l.setBounds(150, 70, 150, 150);
        display.addComponent(l);

        healthIndicatorPlayer = new JProgressBar();
        healthIndicatorPlayer.setStringPainted(true);
        fullHealthPlayer = Integer.toString(player.getHealth());
        healthIndicatorPlayer.setString(player.getHealth() + "/" + fullHealthPlayer);
        healthIndicatorPlayer.setValue(player.getHealth());
        healthIndicatorPlayer.setBounds(150, 250, 150, 30);
        healthIndicatorPlayer.setForeground(new Color(0, 180, 0));
        display.addComponent(healthIndicatorPlayer);

        l = new JLabel(new ImageIcon(enemy.getImgScaled()));
        l.setBounds(500, 70, 150, 150);
        display.addComponent(l);

        healthIndicatorEnemy = new JProgressBar();
        healthIndicatorEnemy.setStringPainted(true);
        fullHealthEnemy = Integer.toString(enemy.getHealth());
        healthIndicatorEnemy.setString(enemy.getHealth() + "/" + fullHealthEnemy);
        healthIndicatorEnemy.setValue(enemy.getHealth() / Integer.parseInt(fullHealthEnemy) * 100);
        healthIndicatorEnemy.setBounds(500, 250, 150, 30);
        healthIndicatorEnemy.setForeground(new Color(0, 180, 0));
        display.addComponent(healthIndicatorEnemy);


        JButton b = new JButton("Attack 1");
        b.setBounds(200, 400, 100, 50);
        b.addActionListener(e -> {
            attack('e');
        });
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(500, 400, 100, 50);
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
            MediaPlayer.playInBackground("/assets/homeMusic.wav");
            System.out.println("You Won!!!! Congratulations!!!");
            player.restoreHealth();
            save.setQuestCount(save.getQuestCount() - 1);
            if(save.getQuestCount()==0) {
                save.setQuestType(-1);
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
