package gameoflife.model;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
public class GameEngine implements Engine {

    private Cell[][] generation;

    public GameEngine(int horizontalCellCount, int verticalCellCount) {
        if(horizontalCellCount <= 0 || verticalCellCount <= 0)
            throw new IllegalArgumentException("Cell count should be more 0");
        this.generation = getStartCellArray(horizontalCellCount, verticalCellCount);
    }

    public Cell[][] getGeneration() {
        return generation;
    }

    public int getVerticalCellCount() {
        return generation[0].length;
    }

    public int getHorizontalCellCount() {
        return generation.length;
    }

    @Override
    public void setCellStain(int x, int y, boolean isAlive) {
        generation[y][x].setStain(isAlive);
        generation = updateNeighbors(generation);
    }

    @Override
    public boolean getCellStain(int x, int y) {
        return generation[y][x].isAlive();
    }

    private Cell[][] getStartCellArray(int width, int height) {
        Cell[][] startState = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                startState[y][x] = new Cell(false);
            }
        }
        return updateNeighbors(startState);
    }

    private Cell[][] updateNeighbors(Cell[][] startState) {
        int height = startState.length;
        int width = startState[0].length;
        Cell[][] result = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = new Cell(startState[y][x].isAlive(), getNeighborsState(startState, x, y));
            }
        }
        return result;
    }

    public void getNextGeneration() {
        Cell[][] nextGeneration = new Cell[generation.length][generation[0].length];
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                cell.updateCellStain();
            }
        }
        int x = 0, y = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                nextGeneration[y][x] = new Cell(cell.isAlive(), getNeighborsState(generation, x++, y));
            }
            y++;
            x = 0;
        }
        generation = nextGeneration;
    }

    @Override
    public void resetEngineStain() {
        for (int y = 0; y < getHorizontalCellCount(); y++) {
            for (int x = 0; x < getVerticalCellCount(); x++) {
                generation[y][x] = new Cell(false);
            }
        }
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
