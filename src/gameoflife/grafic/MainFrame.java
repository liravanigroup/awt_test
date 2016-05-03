package gameoflife.grafic;

import javax.swing.*;
import java.awt.*;

/**
 * awt_test
 * Sergii
 * 2016-05-03.
 */

public class MainFrame extends JFrame {
    public MainFrame(int windowWidth, int windowHeight, int pixelSize, int borderSize) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = screenSize.width / 2 - windowWidth / 2;
        int y = screenSize.height / 2 - windowHeight / 2;

        setBounds(x, y, windowWidth, windowHeight);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainPanel panel = new MainPanel(windowWidth, windowHeight, pixelSize, borderSize);
        add(panel);
        panel.setBounds(0, 0, windowWidth, windowHeight);
        setVisible(true);
    }
}
