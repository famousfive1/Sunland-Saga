package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;
import Utility.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

        addButtonToScreen("Attack 1", 200, 400, 80, 40, e->attack('e'));
        addButtonToScreen("Attack 2", 400, 400, 80, 40, e->attack('f'));

        addPlayerToScreen(player, 130, 210, 100, 100);
        fullHealthPlayer = Integer.toString(player.getHealth());
        healthIndicatorPlayer =  addHealthBarToScreen(player, 100, 330, 160, 30, new Color(0, 180, 0));

        addPlayerToScreen(enemy, 520, 210, 100, 100);
        fullHealthEnemy = Integer.toString(enemy.getHealth());
        healthIndicatorEnemy =  addHealthBarToScreen(enemy, 500, 330, 160, 30, new Color(0, 180, 0));

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

    private void addPlayerToScreen(Character character, int x, int y, int width, int height){
        JLabel l = new JLabel(new ImageIcon(character.getImgScaled()));
        l.setBounds(x, y ,width, height);
        display.addComponent(l);

    }

    private JProgressBar addHealthBarToScreen(Character character, int x, int y, int width, int height, Color color){
        JProgressBar healthIndicator = new JProgressBar();
        healthIndicator.setStringPainted(true);
        String fullHealth = Integer.toString(character.getHealth());
        healthIndicator.setString(character.getHealth() + "/" + fullHealth);
        healthIndicator.setValue(character.getHealth());
        healthIndicator.setBounds(x, y, width, height);
        healthIndicator.setForeground(color);
        display.addComponent(healthIndicator);
        return healthIndicator;
    }

    private void addButtonToScreen(String buttonText, int x, int y, int width, int height, ActionListener l){
        JButton jButton = new JButton(buttonText);
        jButton.setBounds(x, y, width, height);
        jButton.addActionListener(l);
        display.addComponent(jButton);
        //return jButton;
    }
}
