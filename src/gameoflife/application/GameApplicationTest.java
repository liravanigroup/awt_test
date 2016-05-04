package gameoflife.application;

import gameoflife.grafic.MainFrame;
import gameoflife.model.Engine;
import gameoflife.model.GameEngine;

/**
 * awt_test
 * Sergii
 * 2016-05-03.
 */

public class GameApplicationTest {
    public static void main(String[] args) {
        Engine engine = new GameEngine(20,6);
        MainFrame frame = new MainFrame(engine, 30);//engine, pixelSize
    }
}





