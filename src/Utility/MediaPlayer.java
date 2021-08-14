package Utility;

import jaco.mp3.player.MP3Player;

import java.net.URL;

public  class MediaPlayer {
    private static MP3Player mp3Player = new MP3Player();

    public static void playInBackground(String path){
        try {
            URL u = MediaPlayer.class.getResource(path);
            if(u == null) return;
            mp3Player = new MP3Player(u);
        } catch(Exception e) {
            e.printStackTrace();
        }

        mp3Player.play();
        mp3Player.setRepeat(true);
    }

    public static void playSfx(String path) {
        try {
            URL u = MediaPlayer.class.getResource(path);
            if(u == null) return;
            new MP3Player(u).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        if(mp3Player.isStopped()) return;
        mp3Player.stop();
    }
}
