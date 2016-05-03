package gameoflife.grafic;

import gameoflife.model.Cell;
import gameoflife.model.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * awt_test
 * Sergii
 * 2016-05-03.
 */

class MainPanel extends JPanel {

    private int pixelSize;
    private int borderSize;
    private Cell[][] generation;
    private boolean gameIsRun;

    MainPanel(int windowWidth, int windowHeight, int pixelSize, int borderSize) {
        this.pixelSize = pixelSize;
        this.borderSize = borderSize;
        int countX = windowWidth / (pixelSize + borderSize);
        int countY = windowHeight / (pixelSize + borderSize);
        GameEngine game = new GameEngine(getCellArray(countX, countY));
        this.generation = game.getGeneration();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / (pixelSize + borderSize);
                int y = e.getY() / (pixelSize + borderSize);
                generation[y][x] = new Cell(!generation[y][x].isAlive());
                repaint();
            }
        });

        Button start = new Button("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameIsRun = true;
                GameEngine gam = new GameEngine(generation);
                new Thread() {
                    public void run() {
                        while (gameIsRun) {
                            repaint();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            gam.getNextGeneration();
                            generation = gam.getGeneration();
                        }
                    }
                }.start();
            }
        });
        this.add(start);

        Button pause = new Button("PAUSE");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameIsRun = false;
            }
        });
        this.add(pause);

        Button clear = new Button("CLEAR");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameEngine game = new GameEngine(getCellArray(countX, countY));
                generation = game.getGeneration();
                repaint();
            }
        });
        this.add(clear);
    }

    private Cell[][] getCellArray(int countX, int countY) {
        Cell[][] result = new Cell[countY][countX];
        for (int indexY = 0; indexY < countY; indexY++) {
            for (int indexX = 0; indexX < countX; indexX++) {
                result[indexY][indexX] = new Cell(false);
            }
        }
        return result;
    }

    public void paintComponent(Graphics g) {
        int step = 0;
        int vSteps = 0;
        int hSteps = 0;

        while (getHeight() > hSteps) {
            g.setColor(Color.BLUE);
            g.drawLine(0, step, getWidth(), step);
            step += pixelSize + borderSize;
            hSteps += pixelSize + borderSize;
        }
        step = 0;
        while (getWidth() > vSteps) {
            g.setColor(Color.BLUE);
            g.drawLine(step, 0, step, getHeight());
            step += pixelSize + borderSize;
            vSteps += pixelSize + borderSize;
        }

        for (int indexY = 0; indexY < generation.length; indexY++) {
            for (int indexX = 0; indexX < generation[0].length; indexX++) {
                if (generation[indexY][indexX].isAlive()) {
                    g.setColor(new Color(102, 255, 96));
                    g.fillRect(indexX * pixelSize + indexX * borderSize + borderSize, indexY * pixelSize + indexY * borderSize + borderSize, pixelSize, pixelSize);
                } else {
                    g.setColor(new Color(76, 95, 255));
                    g.fillRect(indexX * pixelSize + indexX * borderSize + borderSize, indexY * pixelSize + indexY * borderSize + borderSize, pixelSize, pixelSize);
                }
            }

        }
    }
}
