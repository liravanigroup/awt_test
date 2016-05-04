package gameoflife.model;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
public class Cell {
    private boolean isAlive;
    private boolean[] neighborsStain = new boolean[8];

    Cell(boolean isAlive, boolean[] neighborsStain) {
        this.isAlive = isAlive;
        this.neighborsStain = neighborsStain;
    }

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    void updateCellStain() {
        int count = getAliveNeighboursCount();
        if (isAlive) {
            isAlive = count == 2 || count == 3;
        } else {
            if (count == 3)
                isAlive = true;
        }
    }

    private int getAliveNeighboursCount() {
        int count = 0;
        for (boolean isAliveCell : neighborsStain) {
            if (isAliveCell)
                count++;
        }
        return count;
    }

    public void setStain(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
