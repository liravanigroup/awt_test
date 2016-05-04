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
            Engine engine = new GameEngine(50, 60);
            MainFrame frame = new MainFrame(engine, 10);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }
}





