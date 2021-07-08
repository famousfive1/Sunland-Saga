package States;

import Entity.Character;
import Entity.Player;
import GUI.UIParts;

import javax.swing.*;

public class StateCombat extends GameState {
    Player player;
    Character enemy;

    public StateCombat(Player player, Character enemy) {
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
        b.addActionListener(e -> player.usedMove('e'));
        display.addComponent(b);

        b = new JButton("Attack 2");
        b.setBounds(400, 400, 80, 40);
        b.addActionListener(e -> player.usedMove('e'));
        display.addComponent(b);
    }

    @Override
    public void handleInput(char typed) {
        System.out.println("Press buttons instead!!");
    }
}
