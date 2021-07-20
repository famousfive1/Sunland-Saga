package Entity;

import java.awt.image.BufferedImage;

public class NPCs extends Character {

    String[] dialouges = {"Can you kill 3 wolfs and bring me their hide",
    "Can you kill 3 bandits and clear the camp in the forest",
    "Can you kill 5 bandits and bring me the stolen goods",
    "Can you kill 5 wolfs in the forest",
    "Can you kill 3 wolfs and bring me their teeth"};

    public NPCs (String name, BufferedImage img){
        super(name, img, 100);
    }

    public String getQuestDialouge(int a)
    {
        return dialouges[a];
    }

}
