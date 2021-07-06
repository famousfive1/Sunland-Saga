package States;

import GUI.UIParts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*Load map
Load objects pngs 
Load charcter/move*/

public class StateWorld extends GameState{
    public StateWorld()//forestmap1
    {
        //load map forest1
        display = new UIParts();

        JButton b = new JButton("Play");
        b.setBounds(400-35, 350-15, 90, 50);
        b.addActionListener(e -> button1Clicked());
        display.addComponent(b);

        b = new JButton("Exit");
        b.setBounds(400-35, 420-15, 90, 50);
        b.addActionListener(e -> button1Clicked());
        display.addComponent(b);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\kaust\\Desktop\\College stuff\\Computer porgs\\SunlandSaga_KK\\Sunland-Saga\\src\\assets\\MainMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(0, 0, 800, 600);
        display.addComponent(picLabel);
    }

    public static void button1Clicked() {
        System.out.println("clked");
    }
}
