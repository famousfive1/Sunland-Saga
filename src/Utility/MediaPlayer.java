package Utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public  class MediaPlayer {
    private static AudioInputStream audioInputStream;
    private static Clip clip;





    public static void play(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
             clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch(Exception e) {
            e.printStackTrace();
        }

        clip.loop(100);
    }

    public static void stop(){
        clip.stop();
        clip.close();


    }
}
