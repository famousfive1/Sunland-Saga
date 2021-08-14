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
    JButton attack1;
    JButton attack2;
    JButton attack3;

    public StateCombat(Player player, Character enemy, StateWorld save) {
        this.save = save;

        display = new UIParts();

        this.player = player;
        this.enemy = enemy;

        attack1 = addButtonToScreen(player.getWeapon(), 170, 400, 140, 40, e -> {
            attack('f');
            MediaPlayer.playSfx("/assets/sfx/attackTwo.mp3");
        });
       attack2 = addButtonToScreen("Kick", 400, 400, 80, 40, e -> {
            attack('e');
            MediaPlayer.playSfx("/assets/sfx/attackOne.mp3");

        });

        attack3 = addButtonToScreen("Punch", 200, 500, 80, 40, e -> {
            attack('e');
            MediaPlayer.playSfx("/assets/sfx/attackOne.mp3");
        });

        addButtonToScreen("Flee", 400, 500, 80, 40, e -> {
            player.restoreHealth();
            Game.setCurrentState(save);
        });

        addPlayerToScreen(player, 130, 210, 100, 100);
        fullHealthPlayer = Integer.toString(player.getHealth());
        healthIndicatorPlayer = addHealthBarToScreen(player, 100, 330, 160, 30, new Color(0, 180, 0));

        addPlayerToScreen(enemy, 520, 210, 100, 100);
        fullHealthEnemy = Integer.toString(enemy.getHealth());
        healthIndicatorEnemy = addHealthBarToScreen(enemy, 500, 330, 160, 30, new Color(0, 180, 0));

        JLabel back = new JLabel(new ImageIcon(display.loadImg("/assets/CombatBack.png")));
        back.setBounds(0, 0, 800, 600);
        display.addComponent(back);
    }

    @Override
    public void playMusic() {
        MediaPlayer.playInBackground("/assets/combatMusic.mp3");
    }

    @Override
    public void handleInput(char typed) {
        if (typed == 'p')
            Game.setCurrentState(new StatePaused(this));
        else System.out.println("Press buttons instead!!");
    }

    void attack(char usedMove) {

        disengageButtons();

        player.reactToMove(usedMove);
        enemy.takeDamage(200);

        if (usedMove != 'f' && enemy.getHealth() != 0)
            player.takeDamage(10 + (int) (Math.random() * 40));


        healthIndicatorEnemy.setValue((enemy.getHealth() * 100) / Integer.parseInt(fullHealthEnemy));
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
        Timer deathCheckTimer = new Timer(0, e -> {

            if (enemy.getHealth() == 0) {
                System.out.println("Killed Enemy");
                player.restoreHealth();

                save.setQuestDisplay();

                Game.setCurrentState(save);
                MediaPlayer.playSfx("/assets/sfx/combatWon.mp3");
                JOptionPane.showMessageDialog(null, "You Won in the Battle!");

                if (save.getQuestType() != -1&&enemy.getName().equalsIgnoreCase(save.currentQuestEnemy))
                    save.setCurrentQuestKillCount(save.getCurrentQuestKillCount() + 1);



            }

            if (player.getHealth() == 0) {

                System.out.println("You Died!!! Sorry!!!!");
                player.restoreHealth();
                player.decreaseLive();
                save.livesDisplay.setText("Lives left " + player.getLives());

                if (player.getLives() == 0) {
                    System.out.println("You lost the game");
                    JOptionPane.showMessageDialog(null, "You lost all lives. Game Over!!");
                    Game.setCurrentState(new StateLost());
                } else {
                   Game.setCurrentState(save);
                    MediaPlayer.playSfx("/assets/sfx/dead.mp3");
                    JOptionPane.showMessageDialog(null, "You Died in the Battle!");
                }
                //TODO 2. Do something appropriate here
            }

            engageButtons();

        });

        deathCheckTimer.setInitialDelay(600);
        deathCheckTimer.setRepeats(false);
        deathCheckTimer.start();
    }

    private void engageButtons() {
        attack2.addActionListener(f->{
            attack('e');
            MediaPlayer.playSfx("/assets/sfx/attackOne.mp3");
        });

        attack1.addActionListener(f->{
            attack('f');
            MediaPlayer.playSfx("/assets/sfx/attackTwo.mp3");
        });

        attack3.addActionListener(f->{
            attack('e');
            MediaPlayer.playSfx("/assets/sfx/attackOne.mp3");
        });
    }

    private void disengageButtons() {
        attack1.removeActionListener(attack1.getActionListeners()[0]);
        attack2.removeActionListener(attack2.getActionListeners()[0]);
        attack3.removeActionListener(attack3.getActionListeners()[0]);
    }

    private void addPlayerToScreen(Character character, int x, int y, int width, int height) {
        JLabel l = new JLabel(new ImageIcon(character.getImgScaled()));
        l.setBounds(x, y, width, height);
        display.addComponent(l);

    }

    private JProgressBar addHealthBarToScreen(Character character, int x, int y, int width, int height, Color color) {
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

    private JButton addButtonToScreen(String buttonText, int x, int y, int width, int height, ActionListener l) {
        JButton jButton = new JButton(buttonText);
        jButton.setBounds(x, y, width, height);
        jButton.addActionListener(l);
        display.addComponent(jButton);
        return jButton;
    }
}
