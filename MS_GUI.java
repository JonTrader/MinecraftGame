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
		int[][] countGrid;
		boolean[][] bombGrid;
		boolean[][] visited;
		
		public MineSweeperBoard()
		{
			this.grid = new Grid();
			this.countGrid= grid.getCountGrid();
			this.bombGrid = grid.getBombGrid();
			
			
			grid.displayBomb();
			grid.displayCount();
			
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
						board[i][j].setName("�");
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
	    

		
		public void promptMessage()
		{
			int yesNo = JOptionPane.showConfirmDialog(null,  "Play Again?", "Yes or No", JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION)
			{
				this.grid = new Grid();
				this.countGrid = grid.getCountGrid();
				this.bombGrid = grid.getBombGrid();
				
				grid.displayBomb();
				grid.displayCount();
				
				this.rows = grid.getNumRows();
				this.columns = grid.getNumColumns();
				
				for (int i = 0; i < board.length; i++)
				{
					for (int j = 0; j < board[i].length; j++)
					{
						board[i][j].addActionListener(this);
						board[i][j].setEnabled(true);
						
						board[i][j].setText("");
						board[i][j].setBackground(null);
						
						if (bombGrid[i][j] == true)
						{
							board[i][j].setName("�");
						}
						else
						{
							board[i][j].setName("" + countGrid[i][j]);
							nonBombs++;
						}

					}
				}
				
				
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
		


		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton btnClicked = (JButton)e.getSource();
			btnClicked.setEnabled(false);
			
			if (btnClicked.getName().equals("�"))
			{
				for (int i = 0; i < board.length; i++)
				{
					for (int j = 0; j < board[i].length; j++)
					{
						if (board[i][j].getName().equals("�"))
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
//				// Reveal adjacent 0 cells
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
