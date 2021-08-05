package Entity;

import java.awt.image.BufferedImage;

public class NPCs extends Character {

    String[] dialouges = {"Can you kill 3 Exterminator and bring me their hide",
    "Can you kill 3 Bandit and clear the camp in the forest",
    "Can you kill 5 Subtilizer and bring me the stolen goods",
    "Can you kill 5 Dog in the forest",
    "Can you kill 3 Wolf and bring me their teeth"};

    public NPCs (String name, BufferedImage img){
        super(name, img, 100);
    }

    public String getQuestDialouge(int a)
    {
        return dialouges[a];
    }

}
