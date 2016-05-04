package gameoflife.grafic;

import gameoflife.model.Engine;

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
    private Engine engine;
    private boolean gameIsRun;

    MainPanel(Engine engine, int pixelSize, int borderSize) {
        this.pixelSize = pixelSize;
        this.borderSize = borderSize;
        this.engine = engine;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / (pixelSize + borderSize);
                int y = e.getY() / (pixelSize + borderSize);
                engine.setCellStain(x,y,!engine.getCellStain(x,y));
                repaint();
            }
        });

        Button start = new Button("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameIsRun = true;
                new Thread() {
                    public void run() {
                        while (gameIsRun && engine.getAliveCount()>0) {
                            repaint();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            engine.getNextGeneration();
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
                engine.resetEngineStain();
                gameIsRun = false;
                repaint();
            }
        });
        this.add(clear);
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

        for (int y = 0; y < engine.getHorizontalCellCount(); y++) {
            for (int x = 0; x < engine.getVerticalCellCount(); x++) {
                if (engine.getCellStain(x,y)) {
                    g.setColor(new Color(102, 255, 96));
                    g.fillRect(x * pixelSize + x * borderSize + borderSize, y * pixelSize + y * borderSize + borderSize, pixelSize, pixelSize);
                } else {
                    g.setColor(new Color(76, 95, 255));
                    g.fillRect(x * pixelSize + x * borderSize + borderSize, y * pixelSize + y * borderSize + borderSize, pixelSize, pixelSize);
                }
            }

        }
    }
}
