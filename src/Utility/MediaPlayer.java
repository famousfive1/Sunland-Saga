package Utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public  class MediaPlayer {
    private static AudioInputStream audioInputStream;
    private static Clip clip;

    public static void playInBackground(String path){
        try {
            InputStream s = MediaPlayer.class.getResourceAsStream(path);
            if(s == null) return;
            audioInputStream = AudioSystem.getAudioInputStream(s);
             clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch(Exception e) {
            e.printStackTrace();
        }

        clip.loop(100);
    }

    public static void playSfx(String path) {
        Clip clip = null;
        AudioInputStream audioInputStream;
        try {
            InputStream s = MediaPlayer.class.getResourceAsStream(path);
            if (s == null) return;

            audioInputStream = AudioSystem.getAudioInputStream(s);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert clip != null;
        clip.start();



    }



    public static void stop(){
        if(clip == null) return;
        clip.stop();
        clip.close();
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
