package States;

import GUI.GameWindow;
import Utility.MediaPlayer;

import java.io.*;
import java.util.ArrayList;

public class Game {

    static GameWindow window;
    static GameState currentState;

    // Execution starts here
    public static void main(String[] args) {
        window = new GameWindow();
        currentState = new StateMainMenu();
        window.setState(currentState);
        currentState.playMusic();
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameState getCurrentState() {
        return currentState;
    }

    // Change current game state
    public static void setCurrentState(GameState GS){
        MediaPlayer.stop();
        System.out.println("State Change: " + currentState.toString() + " --> " + GS.toString());
        currentState = GS;
        GS.playMusic();
        window.setState(currentState);
    }

    public static void updateWindow() {
        window.updateWindow();
    }

    // Load file and return the lines in an ArrayList
    public static ArrayList<String> loadFile(String path) {
        InputStream fs = Game.class.getResourceAsStream(path);
        if(fs == null) {
            System.out.println("Couldn't load file at path: " + path);
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        ArrayList<String> s = new ArrayList<>();
        String st;
        try {
            while((st = br.readLine()) != null)
                s.add(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
