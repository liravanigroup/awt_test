package gameoflife.model;

/**
 * awt_test
 * Sergii
 * 2016-05-04.
 */

public interface Engine {
    Cell[][] getGeneration();

    void getNextGeneration();

    void resetEngineStain();

    int getAliveCount();

    int getVerticalCellCount();

    int getHorizontalCellCount();

    void setCellStain(int x, int y, boolean isAlive);

    boolean getCellStain(int x, int y);
}
