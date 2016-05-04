package gameoflife.grafic;

import gameoflife.model.Engine;

import javax.swing.*;
import java.awt.*;

/**
 * awt_test
 * Sergii
 * 2016-05-03.
 */

public class MainFrame extends JFrame {
    public MainFrame(Engine engine, int pixelSize) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int windowHeight = engine.getHorizontalCellCount() * (pixelSize + 1) + 1;
        int windowWidth = engine.getVerticalCellCount() * (pixelSize + 1) + 1;

        int x = screenSize.width / 2 - windowWidth / 2;
        int y = screenSize.height / 2 - windowHeight / 2;

        setBounds(x, y, windowWidth+16, windowHeight+39);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainPanel panel = new MainPanel(engine, pixelSize, 1);
        add(panel);
        panel.setBounds(0, 0, windowWidth, windowHeight);
        setVisible(true);
    }
}
