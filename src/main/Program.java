package main;

import gameLogic.Game;
import gui.MainWindow;

/**
 * Created by boris on 8/1/17.
 */
public class Program {

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.getGameInstance().play();
    }
}
