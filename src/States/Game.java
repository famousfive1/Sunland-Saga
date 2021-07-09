package States;

import GUI.GameWindow;

import java.io.*;
import java.util.ArrayList;

public class Game {

    static GameWindow window;
    static GameState currentState;

    public static void main(String[] args) {
        window = new GameWindow();
        currentState = new StateMainMenu();
        window.setState(currentState);
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(GameState GS){
        System.out.println("State Change: " + currentState.toString() + " --> " + GS.toString());
        currentState = GS;
        window.setState(currentState);
    }

    public static void updateWindow() {
        window.updateWindow();
    }

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
