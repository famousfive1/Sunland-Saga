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
        for (String i : loadFile("/assets/forest1Test.txt"))
            System.out.println(i);
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(GameState GS){
        currentState = GS;
        window.setState(currentState);
    }

    public static void updateWindow() {
        window.updateWindow();
    }

    public static ArrayList<String> loadFile(String path) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Game.class.getResourceAsStream(path)));
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
