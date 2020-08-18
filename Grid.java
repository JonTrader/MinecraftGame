package Project2;
import java.util.Random;

public class Grid {

    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;

    public Grid()
    {
        this.numRows = 10;
        this.numColumns = 10;
        this.numBombs = 25;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();
        createCountGrid();
    }

    public Grid(int rows, int columns)
    {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = 25;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();
        createCountGrid();
    }

    public Grid(int rows, int columns, int numBombs)
    {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = numBombs;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();
        createCountGrid();
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumColumns()
    {
        return numColumns;
    }

    public int getNumBombs()
    {
        return numBombs;
    }

    public boolean[][] getBombGrid()
    {
        boolean[][] bombGridCopy = new boolean[numRows][numColumns];
        for (int i = 0; i < numRows; i++)
        {
            System.arraycopy(bombGrid[i], 0, bombGridCopy[i], 0, bombGrid[i].length);
        }
        return bombGridCopy;
    }

    public int[][] getCountGrid()
    {
       int[][] countGridCopy = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++)
        {
            System.arraycopy(countGrid[i], 0, countGridCopy[i], 0, countGrid[i].length);
        }
        return countGridCopy;
    }

    public boolean isBombAtLocation(int row, int column)
    {
        return bombGrid[row][column];
    }

    public int getCountAtLocation(int row, int column)
    {
        return countGrid[row][column];
    }

    private void createBombGrid()
    {
        int counter = 0;
        int randRatio = ((numRows * numColumns) / this.numBombs);


        for (int i = 0; i < bombGrid.length; i++)
        {
            for (int j = 0; j < bombGrid[i].length; j++)
            {
                int yn = new Random().nextInt(randRatio);
                if (counter >= numBombs)
                {
                    return;
                }
                else if (yn == 1)
                {
                    bombGrid[i][j] = true;
                    counter++;
                }
            }
        }
        
        if (counter < numBombs)
        {
        	int randRow = new Random().nextInt(numRows);
        	int randCol = new Random().nextInt(numColumns);
        	do
        	{
        		if (bombGrid[randRow][randCol] == false)
        		{
        			bombGrid[randRow][randCol] = true;
        			counter++;
        		}
        	} while (counter < numBombs);
        }
    }

    private void createCountGrid()
    {
        for (int i = 0; i < bombGrid.length; i++)
        {
            for (int j = 0; j < bombGrid[i].length; j++) {
                int count = 0;

                if (bombGrid[i][j] == true) {
                    count++;
                }
                if (isValid(i - 1, j)) {
                    if (bombGrid[i - 1][j] == true) {
                        count++;
                    }
                }

                if (isValid(i + 1, j)) {
                    if (bombGrid[i + 1][j] == true) {
                        count++;
                    }
                }

                if (isValid(i, j - 1)) {
                    if (bombGrid[i][j - 1] == true) {
                        count++;
                    }
                }

                if (isValid(i, j + 1)) {
                    if (bombGrid[i][j + 1] == true) {
                        count++;
                    }
                }

                if (isValid(i - 1, j + 1)) {
                    if (bombGrid[i - 1][j + 1] == true) {
                        count++;
                    }
                }

                if (isValid(i - 1, j - 1)) {
                    if (bombGrid[i - 1][j - 1] == true) {
                        count++;
                    }
                }

                if (isValid(i + 1, j - 1)) {
                    if (bombGrid[i + 1][j - 1] == true) {
                        count++;
                    }
                }

                if (isValid(i + 1, j + 1)) {
                    if (bombGrid[i + 1][j + 1] == true) {
                        count++;
                    }
                }

                countGrid[i][j] = count;
            }
        }
    }

    public boolean isValid(int row, int column)
    {
        return (row >= 0 && row < numRows && column >= 0 && column < numColumns);
    }

    public static void main(String[] args) {

    }
}
