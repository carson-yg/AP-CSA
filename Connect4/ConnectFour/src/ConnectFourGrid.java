import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JOptionPane;


public class ConnectFourGrid {
  // Square size in the visual representation
	public final int SQ = 40;
  /* Our grid of checkers.  Initially all are null.
     Row 0 is the top row, row 5 is the bottom row.  Col 0 is the left column, and Col 6 is the rightmost column.*/
  
	private Checker[][] grid = new Checker[6][7];
	private boolean gameEnd = false;
  private boolean redTurn = true;

  public boolean getGameEnd(){
    return gameEnd;
  }

  public boolean getRedTurn(){
    return redTurn;
  }
	/**
	 * This method is called when a column is clicked. It processes the click in the following order.  
	 * 1.  The column is  checked to see if it is full.  
	 * If it is full, then the click is ignored.
	 * If not full, then the correct color checker is placed in the lowest 
	 * row possible (biggest row number) in that column.  
	 * 
	 * If a checker was placed, then we need to check to see if there is 
	 * four in a row. I recommend checking to see if there is a four
	 * in a row vertically from the latest checker placed, then four in a row horizontally
	 * then four in a row along each diagonal from the latest checker location.  I have 
	 * created one function below called fourVerts which is intended to return true if the latest 
	 * checker is part of four in a row, vertically.  I recommend making 3 other methods, at least.
	 * 
	 * If the game is not over, then the turn is switched so that the next click will 
	 * place a checker of the opposite color.  If the game is over, then a message is 
	 * displayed and the game should reset on the next click.
	 * 
	 * @param col The column that the user clicked
	 */
	protected void colClicked(int col) {
		Location rowCol = lowestEmptyLoc(col);
    if(rowCol != null){
      if(redTurn){
        grid[rowCol.getRow()][rowCol.getCol()] = new RedChecker();
        redTurn = false;
      }
      if(checkGameOver(rowCol)){
        gameEnd = true;
      } 
    } 
    if(colFull(col)){
      System.out.println("Column is full");
    }
	}

  public void blackMove(){
    Location ran = null; 
    while(ran == null){
          int random = (int) (Math.random() * (7));
          ran = lowestEmptyLoc(random);
    }
    grid[ran.getRow()][ran.getCol()] = new BlackChecker();
    redTurn = true;
    if(checkGameOver(ran)){
          gameEnd = true;
        }         
  }
	
	
	// prints the board contents in the console and prints who won
	private void displayStateInConsole() {

	}
	/**
	 * This method checks to see if the game is over.  It checks to see if the game has been 
	 * won by either player.  More advanced groups should consider how to determine if the 
	 * game can't be won (tie game)
	 * @param loc This is the latest Location where a Checker was added.
	 * @return This returns true if the game is over.  More advanced groups may change this
	 *  to return an int, where 0 means keep going , 1 means latest player won, 2 means tie game
	 *  Alternatively, you can write a gameTied method that is called AFTER checkGameOver...
	 */
	private boolean checkGameOver(Location loc) {
		return fourVerts(loc) || fourHoriz(loc) || fourDiag(loc);
	}
  


	/**
	 * Checks to see if there are four checkers in a row that match each other starting 
	 * with loc and going to the South (because loc was the last checker played).  What is the
	 * maximum value of loc.getRow() such that you don't need to even check the places below?
	 * @param loc The Location of the latest Checker added to the Grid
	 * @return true if loc is the top of a four-in-row (all the same color)
	 */
	private boolean fourVerts(Location loc) {
		int row = loc.getRow();
    if(row > 2){// nope, not enough checkers below me
      return false;
    }
    Checker top = grid[row][loc.getCol()];
    for(int x = 0; x<3; x++){
      Checker below = grid[row+1+x][loc.getCol()];
      if(top.isRed()!= below.isRed()){
        return false;
      }
    }

		return true;
	}

  private boolean fourHoriz(Location loc)
  {
    int count = 1; 
    int row = loc.getRow();
    boolean isRed = grid[row][loc.getCol()].isRed();
    for(int col = loc.getCol() - 1; col >= 0; col--)
    {
      Checker piece = grid[row][col];
      if(piece != null && piece.isRed() == isRed)
      {
        count++;
      }
      else
      {
        break;
      }
    }

    for(int col = loc.getCol() + 1; col < grid[0].length; col++)
    {
      Checker piece = grid[row][col];
      if(piece != null && piece.isRed() == isRed)
      {
        count++;
      }
      else
      {
        break;
      }
    }

    if(count >= 4)
    {
      return true;
    }

    return false;
  }

  private boolean fourDiag(Location loc){
    int row = loc.getRow();
    int col = loc.getCol();
    boolean isRed = grid[row][loc.getCol()].isRed();
    int count = 1;
    // starts with upper left
    int nextRow = row - 1;
    int nextCol = col - 1;
    while (nextRow >=0 && nextCol >=0 && count < 4) {
      Checker piece = grid[nextRow][nextCol];
      if(piece != null && piece.isRed() == isRed) {
        count++;
        nextRow--;
        nextCol--;
      } else {
        break;
      }
    }
    if (count == 4) {
      return true;
    }
    // Check with lower right
    nextRow = row + 1;
    nextCol = col + 1;
    while (nextRow <= 5 && nextCol <= 6 && count < 4) {
      Checker piece = grid[nextRow][nextCol];
      if(piece != null && piece.isRed() == isRed) {
        count++;
        nextRow++;
        nextCol++;
      } else {
        break;
      }
      
    }
    if (count == 4) {
      return true;
    }

    // Check with upper right
    count = 1;
    nextRow = row - 1;
    nextCol = col + 1;
    while (nextRow >= 0 && nextCol <= 6 && count < 4) {
      Checker piece = grid[nextRow][nextCol];
      if(piece != null && piece.isRed() == isRed) {
        count++;
        nextRow--;
        nextCol++;
      } else {
        break;
      }
    }
    if (count == 4) {
      return true;
    }

    // Check with lower left
    nextRow = row + 1;
    nextCol = col - 1;
    while (nextRow <= 5 && nextCol >= 0 && count < 4) {
      Checker piece = grid[nextRow][nextCol];
      if(piece != null && piece.isRed() == isRed) {
        count++;
        nextRow++;
        nextCol--;
      } else {
        break;
      }
    }
    if (count == 4) {
      return true;
    }
    return false;
  }


	/** Finds the lowest empty Location in the specified column or null if the column is full
	 *  The "lowest" Location is the Location with the largest row (or furthest South)with the specified column.
	 * @param col Column to scan
	 * @return Location that is lowest in the column or null if the column is full
	 */
	private Location lowestEmptyLoc(int col) {
    for(int row = grid.length - 1; row >= 0; row--)
    {
      if(grid[row][col] == null)
        return new Location(row, col);
    }
		return null;
	}


	// checks to see that the specified column is full.
	// you can call lowestLoc to help you
	private boolean colFull(int col) {
    if(lowestEmptyLoc(col) != null){
      return false;
    }
		return true;
	}

	/**
	 * This method will be called when it is time to start a new game.
	 */
	public void clearBoard() {
		gameEnd = false;
    redTurn = true;
    for(int r = 0; r < grid.length; r++){
        for(int c = 0; c < grid[r].length; c++){
          grid[r][c] = null;
        }
      }
	}

	public String toString(){
		String s = "";

		return s;
	}

/**
  Draw this board on the specified Graphics.  Do this by filling a blue rectangle the size of each space.  Then draw a circle with a color that matches the state of this space:  white circle if empty (null Checker there), red circle if the Checker is red, black circle if the Checker is not red*/
	public void draw(Graphics g) {
    

    for(int row = 0; row < grid.length; row++)
    {
      for(int col = 0; col < grid[0].length; col++)
      {
        g.setColor(Color.blue);

        g.fillRect(col * SQ, row * SQ, SQ, SQ);
        if(grid[row][col] == null)
        {
          g.setColor(Color.white);
        }
        else if(grid[row][col].isRed())
        {
          g.setColor(Color.red);
        }
        else
        {
          g.setColor(Color.black);
        }
        g.fillOval(col * SQ, row * SQ, SQ - 2, SQ - 2);
      }
    }
		

	}
  // ask this grid which column is associated with the specified x coord.
  public int colFromX(int x){
    return x/SQ;
  }

}