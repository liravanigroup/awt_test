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
        try {
            Engine engine = new GameEngine(10, 10);
            MainFrame frame = new MainFrame(engine, 50);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }
}





