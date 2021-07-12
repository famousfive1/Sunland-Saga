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
        JLabel l = new JLabel(new ImageIcon(player.getImgScaled()));
        l.setBounds(130, 210, 100, 100);
        display.addComponent(l);

        healthIndicatorPlayer = new JProgressBar();
        healthIndicatorPlayer.setStringPainted(true);
        fullHealthPlayer = Integer.toString(player.getHealth());
        healthIndicatorPlayer.setString(player.getHealth() + "/" + fullHealthPlayer);
        healthIndicatorPlayer.setValue(player.getHealth());
        healthIndicatorPlayer.setBounds(100, 330, 160, 30);
        healthIndicatorPlayer.setForeground(new Color(0, 180, 0));
        display.addComponent(healthIndicatorPlayer);

        l = new JLabel(new ImageIcon(enemy.getImgScaled()));
        l.setBounds(520, 210, 100, 100);
        display.addComponent(l);

        healthIndicatorEnemy = new JProgressBar();
        healthIndicatorEnemy.setStringPainted(true);
        fullHealthEnemy = Integer.toString(enemy.getHealth());
        healthIndicatorEnemy.setString(enemy.getHealth() + "/" + fullHealthEnemy);
        healthIndicatorEnemy.setValue(enemy.getHealth() / Integer.parseInt(fullHealthEnemy) * 100);
        healthIndicatorEnemy.setForeground(new Color(0, 180, 0));
        healthIndicatorEnemy.setBounds(500, 330, 160, 30);
        display.addComponent(healthIndicatorEnemy);


        JButton b = new JButton("Attack 1");
        b.setBounds(200, 400, 80, 40);
        b.addActionListener(e -> attack('e'));
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(400, 400, 80, 40);
        b.addActionListener(e -> attack('f'));
        display.addComponent(b);

        JLabel back = new JLabel(new ImageIcon(display.loadImg("/assets/CombatBack.png")));
        back.setBounds(0, 0, 800, 600);
        display.addComponent(back);
    }

    @Override
    public void playMusic() {
        MediaPlayer.playInBackground("/assets/combatMusic.wav");
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

         if(usedMove!='f' && enemy.getHealth()!=0)
             player.takeDamage(10 + (int)(Math.random()*40));


         healthIndicatorEnemy.setValue((enemy.getHealth() * 100)/ Integer.parseInt(fullHealthEnemy));
         healthIndicatorEnemy.setString(enemy.getHealth() + "/" + fullHealthEnemy);

        //The code below executes 500 ms after the functions call
         //This is done to add a visual delay in the enemy attack and the player attack
         Timer timer = new Timer(0, e -> {


             healthIndicatorPlayer.setValue(player.getHealth());
             healthIndicatorPlayer.setString(player.getHealth() + "/" + 100);

         });
         timer.setInitialDelay(500);
         timer.setRepeats(false);
         timer.start();


        //The code inside the lambda below will execute 600ms after the function call.
         //This is done to ensure that the death actually happens sometime after the progress bar gets updated
        Timer deathCheckTimer = new Timer(0, e-> {

            if (enemy.getHealth() == 0) {
                System.out.println("Quest Complete");
                player.restoreHealth();
                save.setQuestCount(save.getQuestCount() - 1);
                if (save.getQuestCount() == 0) {
                    save.setQuestType(-1);
                    player.setInQuest(false);
                    Game.setCurrentState(new StateWin());
                } else
                    Game.setCurrentState(save);

                save.setQuestDisplay();
                save.increaseQuestProgressBar();
                //TODO 1. Do something appropriate here
            }

            if (player.getHealth() == 0) {
                System.out.println("You Died!!! Sorry!!!!");
                player.restoreHealth();
                player.decreaseLive();
                save.livesDisplay.setText("Lives left " + player.getLives());

                if (player.getLives() == 0) {
                    System.out.println("You lost the game");
                    Game.setCurrentState(new StateLost());
                } else
                    Game.setCurrentState(save);
                //TODO 2. Do something appropriate here
            }
        });

        deathCheckTimer.setInitialDelay(600);
        deathCheckTimer.setRepeats(false);
        deathCheckTimer.start();
    }
}
