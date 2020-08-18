package Project2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MS_GUI extends JFrame {
	
	private JPanel mainBoard;
	private MineSweeperBoard MSBoard;
	private int nonBombs;
	
	public MS_GUI()
	{
		setSize(550,550);
		setTitle("Minesweeper");
		setResizable(false);
		
		mainBoard = new JPanel();
		mainBoard.setLayout(new BorderLayout());
		
		MSBoard = new MineSweeperBoard();	
		mainBoard.add(MSBoard);
		
		mainBoard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		add(mainBoard);
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	


	public class MineSweeperBoard extends JPanel implements ActionListener
	{
	
		private JButton[][] board;
		private int rows;
		private int columns;
		private Grid grid;
		
		public MineSweeperBoard()
		{
			this.grid = new Grid();
			int[][] countGrid = grid.getCountGrid();
			boolean[][] bombGrid = grid.getBombGrid();
			
			this.rows = grid.getNumRows();
			this.columns = grid.getNumColumns();
			setLayout(new GridLayout(rows, columns));
			
			board = new JButton[rows][columns];
			
			for (int i = 0; i < board.length; i++)
			{
				for (int j = 0; j < board[i].length; j++)
				{
					board[i][j] = new JButton();
					board[i][j].addActionListener(this);
					board[i][j].setEnabled(true);
					
					if (bombGrid[i][j] == true)
					{
						board[i][j].setName("•");
					}
					else
					{
						board[i][j].setName("" + countGrid[i][j]);
						nonBombs++;
					}
					
					this.add(board[i][j]);
				}
			}
		}
		
	    public boolean isValid(int row, int column)
	    {
	        return (row >= 0 && row < rows && column >= 0 && column < columns);
	    }
	    
//	    public void findAdjCells(int row, int column)
//	    {
//	    	if (isValid(row, column))
//	    	{
//	    		if (board[row][column].getName().equals("0") && board[row][column].isEnabled() == true)
//	    		{
//	    			board[row][column].setText(board[row][column].getName());
//	    			board[row][column].setEnabled(false);
//	    			
//	    			findAdjCells(row - 1, column);
//	    			findAdjCells(row + 1, column);
//	    			findAdjCells(row, column - 1);
//	    			findAdjCells(row, column + 1);
//	    			findAdjCells(row - 1, column + 1);
//	    			findAdjCells(row - 1, column - 1);
//	    			findAdjCells(row + 1, column - 1);
//	    			findAdjCells(row + 1, column + 1);
//	    		}
//	    		else if (!(board[row][column].getName().equals("0")))
//	    		{
//	    			board[row][column].setText(board[row][column].getName());
//	    			board[row][column].setEnabled(false);
//	    			return;
//	    		}
//	    		else
//	    		{
//	    			return;
//	    		}
//	    	}
//	    }
		
		public void findAdjCells(int row, int column)
		{
			
            if (isValid(row - 1, column) && !board[row][column].getName().equals("•"))
            {
            	board[row - 1][column].setEnabled(false);
            	board[row - 1][column].setText(board[row - 1][column].getName());
                if (board[row - 1][column].getName().equals("0"))
                {
                	findAdjCells(row - 1, column);
                }
            }

            if (isValid(row + 1, column) && !board[row][column].getName().equals("•"))
            {
            	board[row + 1][column].setEnabled(false);
            	board[row + 1][column].setText(board[row + 1][column].getName());
                if (board[row + 1][column].getName().equals("0"))
                {
                	findAdjCells(row + 1, column);
                }
            }

            if (isValid(row, column - 1) && !board[row][column].getName().equals("•"))
            {
            	board[row][column - 1].setEnabled(false);
            	board[row][column - 1].setText(board[row][column - 1].getName());
                if (board[row][column - 1].getName().equals("0"))
                {
                	findAdjCells(row, column - 1);
                }
            }

            if (isValid(row, column + 1) && !board[row][column].getName().equals("•")) 
            {
            	board[row][column + 1].setEnabled(false);
            	board[row][column + 1].setText(board[row][column + 1].getName());
                if (board[row][column + 1].getName().equals("0"))
                {
                	findAdjCells(row, column + 1);
                }
            }

            if (isValid(row - 1, column + 1) && !board[row][column].getName().equals("•"))
            {
            	board[row - 1][column + 1].setEnabled(false);
            	board[row - 1][column + 1].setText(board[row - 1][column + 1].getName());
                if (board[row - 1][column + 1].getName().equals("0"))
                {
                	findAdjCells(row - 1, column + 1);
                }
            }

            if (isValid(row - 1, column - 1) && !board[row][column].getName().equals("•"))
            {
            	board[row - 1][column - 1].setEnabled(false);
            	board[row - 1][column - 1].setText(board[row - 1][column - 1].getName());
                if (board[row - 1][column - 1].getName().equals("0"))
                {
                	findAdjCells(row - 1, column - 1);
                }
            }

            if (isValid(row + 1, column - 1) && !board[row][column].getName().equals("•"))
            {
            	board[row + 1][column - 1].setEnabled(false);
            	board[row + 1][column - 1].setText(board[row + 1][column - 1].getName());
                if (board[row + 1][column - 1].getName().equals("0"))
                {
                	findAdjCells(row + 1, column - 1);
                }       
            }

            if (isValid(row + 1, column + 1) && !board[row][column].getName().equals("•"))
            {
            	board[row + 1][column + 1].setEnabled(false);
            	board[row + 1][column + 1].setText(board[row + 1][column + 1].getName());
                if (board[row + 1][column + 1].getName().equals("0"))
                {
                	findAdjCells(row + 1, column + 1);
                }
            }
		}
		
		public void promptMessage()
		{
			int yesNo = JOptionPane.showConfirmDialog(null,  "Play Again?", "Yes or No", JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION)
			{
				new MS_GUI();
			}
			else
			{
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		public void displayLoss()
		{
			JOptionPane.showMessageDialog(null, "You Lost!");
		}
		
		public void displayWin()
		{
			JOptionPane.showMessageDialog(null, "You Won!");
		}
		
		public int theRow(int x)
		{
			return x / 47;
		}
		
		public int theColumn(int y)
		{
			return y / 45;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton btnClicked = (JButton)e.getSource();
			btnClicked.setEnabled(false);
			
			if (btnClicked.getName().equals("•"))
			{
				for (int i = 0; i < board.length; i++)
				{
					for (int j = 0; j < board[i].length; j++)
					{
						if (board[i][j].getName().equals("•"))
						{
							board[i][j].setBackground(Color.red);
						}
						
						board[i][j].setText(board[i][j].getName());
						board[i][j].setEnabled(false);
					}
				}
				displayLoss();
				promptMessage();
			}
			else if (btnClicked.getName().equals("0"))
			{
				btnClicked.setEnabled(false);
				btnClicked.setText(btnClicked.getName());
				findAdjCells(theRow(btnClicked.getX()), theColumn(btnClicked.getY()));
			}
			else
			{
				btnClicked.setText(btnClicked.getName());
				nonBombs--;
				if (nonBombs == 0)
				{
					displayWin();
					promptMessage();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		new MS_GUI();
		
	}

}
