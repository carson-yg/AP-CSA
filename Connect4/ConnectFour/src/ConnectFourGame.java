
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectFourGame {
    
    private ConnectFourGrid grid = new ConnectFourGrid();
    private JFrame frame = new JFrame("Connect Four");
    private JPanel panel;

    
    public static void main(String[] args){
        new ConnectFourGame().start();
    }
    private void start() {
        setUpPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    private void setUpPanel() {
        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                grid.draw(g);
                drawGameInfo(g);
            }
        };
        panel.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                clickedAt(me);
                frame.repaint();
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (grid.getGameEnd() == true) {
                    if(e.getKeyCode() == KeyEvent.VK_Y){
                        grid.clearBoard();
                        frame.repaint();
                    }
                } 
            }
        });

        TimerTask task = new TimerTask(){
          public void run(){
            if(grid.getRedTurn() == false && grid.getGameEnd() == false){
              grid.blackMove();
              frame.repaint();
            }
          }
        };
        Timer timer = new Timer("Timer", true);
        timer.schedule(task, 0, 3000);


        panel.setPreferredSize(new Dimension(1000, 1200));
        panel.setBackground(Color.black);
        frame.add(panel);
        frame.pack();


    }
    protected void clickedAt(MouseEvent me) {
        //System.out.println("You just clicked at: "+me.getX()+", "+me.getY());
        int col = grid.colFromX(me.getX());
        grid.colClicked(col);
    }
    /**
     * Who's turn is it?  How many Checkers played?  Who has won the most games?
     * @param g
     */
    protected void drawGameInfo(Graphics g) {
        String s = "";
        String w = "";
        if(grid.getRedTurn()){
          s = "Red";
        } else {
          s = "Black";
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("Player Turn: " + s,  320, 100);
        if(grid.getGameEnd() == true){
          if(grid.getRedTurn()){
              w = "Black";
          } else {
              w = "Red";
          }
          g.setColor(Color.RED);
          g.drawString("Game won by: " + w, 320, 120);
          g.setColor(Color.GREEN);
          g.drawString("Press Y to start another game.", 320, 140);
        }
    }
  /**
    * Reset the game so that it is ready to be played again.
    */
    public void resetGame(){
      grid.clearBoard();
    }

}
