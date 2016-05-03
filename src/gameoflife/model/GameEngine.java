package gameoflife.model;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
public class GameEngine {

    private Cell[][] generation;

    public GameEngine(Cell[][] generation) {
        this.generation = getCellArray(generation);
    }

    public Cell[][] getGeneration() {
        return generation;
    }

    private Cell[][] getCellArray(Cell[][] generation) {
        int height = generation.length, width = generation[0].length;
        Cell[][] cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(generation[y][x].isAlive(), getNeighborsState(generation, x, y));
            }
        }
        return cells;
    }

    public void getNextGeneration() {
        Cell[][] nextGeneration = new Cell[generation.length][generation[0].length];
        int y = 0, x = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                cell.updateCellStain();
            }
            y++;
            x = 0;
        }
        y = 0;
        x = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                nextGeneration[y][x] = new Cell(cell.isAlive(), getNeighborsState(generation, x++, y));
            }
            y++;
            x = 0;
        }
        generation = nextGeneration;
    }

    private boolean[] getNeighborsState(Cell[][] startGenerationStain, int x, int y) {
        boolean[] result = new boolean[8];

        int lenghtY = startGenerationStain.length;
        int lenghtX = startGenerationStain[0].length;

        int yUp = minusOne(lenghtY, y);
        int yDown = plusOne(lenghtY, y);
        int xLeft = minusOne(lenghtX, x);
        int xRight = plusOne(lenghtX, x);

        result[0] = startGenerationStain[yUp][xLeft].isAlive();
        result[1] = startGenerationStain[yUp][x].isAlive();
        result[2] = startGenerationStain[yUp][xRight].isAlive();
        result[3] = startGenerationStain[y][xRight].isAlive();
        result[4] = startGenerationStain[yDown][xRight].isAlive();
        result[5] = startGenerationStain[yDown][x].isAlive();
        result[6] = startGenerationStain[yDown][xLeft].isAlive();
        result[7] = startGenerationStain[y][xLeft].isAlive();

        return result;
    }

    private int plusOne(int lenght, int index) {
        int sum = index + 1;
        if (sum == lenght)
            return 0;
        else
            return sum;
    }

    private int minusOne(int lenght, int index) {
        int different = index - 1;
        if (different == -1)
            return lenght - 1;
        else
            return different;
    }

    public int getAliveCount() {
        int result = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                if (cell.isAlive())
                    result++;
            }
        }
        return result;
    }
}
