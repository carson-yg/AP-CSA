import java.awt.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class KnightTour{

  private int[][] board; // stores all the moves
  // more instance variables needed!!
  private int currRow;
  private int currCol;
  private int[] rowMove = {1, -1, 1, -1, 2, -2, 2, -2};
  private int[] colMove = {2, 2, -2, -2, 1, 1, -1, -1};
  private int count;
  private Image img;

public static void main(String[] args){
    KnightTour kt = new KnightTour();
    
    kt.runOnce();
    // run 10 tours and display average number of moves until trapped.
    // You need to create this method
    //kt.runSims(10);
  }
  public KnightTour() {
    board = new int[8][8];
    resetBoard();
    this.setStartingLocation(0,0);

    try{
      img = ImageIO.read(new File("../img/knight.png"));
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  public void runOnce(){
    int num = tour();
    System.out.println("Knight got trapped after "+num+" moves.  Ending location was: (" + currRow + ", " + currCol + ")");
    printBoard();
  }

  public void runSims(int times){
    Random r = new Random();
    int ranRow = r.nextInt(8);
    int ranCol = r.nextInt(8);
    int total = 0;  
    for (int x = 0; x < times; x++){
      this.setStartingLocation(ranRow, ranCol);
      int num = tour();
      total = total + num;
      System.out.println("Knight got trapped after "+num+" moves.  Ending location was: (" + currRow + ", " + currCol + ")");
      printBoard();
      resetBoard();
    }
      double average; 
      average = total/times;
      System.out.println("Average: " + average);
  }
  // erase the last tour and set up variables so they are ready to make a new tour
  public void resetBoard(){
    for (int x = 0; x < 8; x++){
      for (int y = 0; y < 8; y++){
        board[x][y] = -1;
      }
    }
    count = 0;
  }

// This method sets the starting Location of the Knight
// Don't know where the info came from
  public void setStartingLocation(int row, int col){
    //System.out.println("Start Move at (" + row + ", " + col + ")");
    board[row][col] = 0;
    currRow = row;
    currCol = col;
  }
// visit locations on the board until trapped or tour is complete
// returns the number of locations visited.  There will be a loop in this method
  public int tour(){

    while (trapped() == false){
      makeBestMove();
    }
    
    return count;// there is a much better answer!!
  }
// Shift the board on the Graphics so it is not in the upper-lefthand corner of JPanel
  public void draw(Graphics g, int xShift, int yShift, int SQ){
    g.setColor(new Color(100, 120, 70));
    g.fillRect(xShift, yShift, SQ*8, SQ*8);

    img = img.getScaledInstance(SQ/2+SQ/5, SQ, Image.SCALE_SMOOTH);

    g.setColor(new Color(255, 255, 255));
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[0].length; col++){
        if ((row + col) % 2 == 0){
          g.fillRect(xShift + col * SQ, yShift + row * SQ, SQ, SQ);
        }

        if(!(board[row][col] == -1)){
          //System.out.println("drew a num");
          g.setColor(new Color(0, 0, 255));
          g.setFont(new Font("", Font.BOLD, 25));
          g.drawString("" + board[row][col], xShift + col * SQ + SQ/4, yShift + row * SQ + SQ/2);
          g.setColor(new Color(255, 255, 255));
        } 
      }
    }

    g.drawImage(img, xShift + currCol * SQ + SQ/8, yShift + currRow * SQ, null);
  }

  public void draw(Graphics g){
    this.draw(g, 0, 0, 80);
  }
  // displays the current state of the board to the console
  public void printBoard(){
    for(int x = 0; x < 8; x++){
      for(int y = 0; y < 8; y++){
        System.out.print(board[x][y] + "  ");
      }
      System.out.println("\n");
    }
  }
  // Selects and moves the knight to a random location, if possible
  // Precondition:  The knight is not trapped when this method is called
  public void makeRandomMove(){
    boolean bGoodMove = false;
    Random r = new Random();
    while (bGoodMove == false){
      int random = r.nextInt(8);
      int nextRow = currRow + rowMove[random];
      int nextCol = currCol + colMove[random];
      if ((nextRow >= 0 && nextRow < 8) && (nextCol >= 0 && nextCol < 8) && (board[nextRow][nextCol] == -1)){
        currRow = nextRow;
        currCol = nextCol;
        count++;
        board[currRow][currCol] = count;
        bGoodMove = true;
        //System.out.println("Move to (" + currRow + ", " + currCol + ")");
      }
    }
    


    
  }
  // Selects and moves the knight to an optimal location which makes it 
  // more likely a complete tour will be accomplished
  // Precondition:  The knight is not trapped when this method is called
  public void makeBestMove(){
    int prevRow = currRow;
    int prevCol = currCol;
    int nextRow = 0;
    int nextCol = 0;
    int posMoveCounter = 0;
    int prevMoveCounter = 8;
    //System.out.println("currRow" +currRow + "  currCol" + currCol);
    for (int i = 0; i < 8; i++){
      currRow += rowMove[i];
      currCol += colMove[i];
      int tempRow = currRow;
      int tempCol = currCol;
      //System.out.println("currRow" +currRow + "  currCol" + currCol);
      if(!outOfBounds()){
        for(int x = 0; x < 8; x++){
          currRow += rowMove[x];
          currCol += colMove[x];
          
          if(!outOfBounds()){
            posMoveCounter++;
          }

          //System.out.println("currRow" +currRow + "  currCol" + currCol);
          currRow = tempRow;
          currCol = tempCol;
        }
        
        if (posMoveCounter < prevMoveCounter){
            prevMoveCounter = posMoveCounter;
            nextRow = prevRow + rowMove[i];
            nextCol = prevCol + colMove[i];
            //System.out.println("nextRow" +nextRow + "  nextCol" + nextCol + "   posMoveCounter" + posMoveCounter + "   rowMove" + rowMove[i] + "   colMove" + colMove[i] + "   prevMoveCounter" + prevMoveCounter);
        }
        posMoveCounter = 0;
      }

      currRow = prevRow;
      currCol = prevCol;
    }
    
    currRow = nextRow;
    currCol = nextCol;
    //System.out.println("currRow" +currRow + "  currCol" + currCol + "(changed)");
    count++;
    board[currRow][currCol] = count;

    //printBoard(board);
  }
  //determines if the knight is trapped (no unvisited squares available from current location)
  public boolean trapped(){
    for (int x = 0; x < 8; x++){
      int nextRow = currRow + rowMove[x];
      int nextCol = currCol + colMove[x];
      if ((nextRow >= 0 && nextRow < 8) && (nextCol >= 0 && nextCol < 8) && (board[nextRow][nextCol] == -1)){
        return false;
      }
    }
    return true;// Always???  C'mon Mr. Hanson
  }

  public boolean outOfBounds(){
    if ((currRow >= 0 && currRow < 8) && (currCol >= 0 && currCol < 8) && (board[currRow][currCol] == -1)){
      return false;
    }
    return true;
  }
}