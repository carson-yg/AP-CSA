import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

public class GraphicalKnightTour{
	private Timer ticker = new Timer(10, null);
	private JFrame frame = new JFrame("Knight's Tour!");
	private JPanel panel;
	private final int X_SHIFT = 145,Y_SHIFT = 50, SQ = 64;
	private boolean randTour = false;
	private KnightTour kt = new KnightTour();

	public static void main(String[] args){
		GraphicalKnightTour gft = new GraphicalKnightTour();
		gft.setUpUI();

	}

	public void setUpUI(){
		setUpPanel();
		setUpButtons();
    frame.revalidate();
  
	}
	public void setUpButtons(){
		// set up next, prev and run buttons along with speed slider
		JButton next = new JButton("Next"),
				prev = new JButton("Prev"),
				runStop = new JButton("Run");
		JSlider speed = new JSlider( 0, 200, 10);

		JPanel controlPanel = new JPanel();
		panel.add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new GridLayout(0,4));
		controlPanel.add(speed);
		controlPanel.add(next);
		controlPanel.add(prev);
		controlPanel.add(runStop);
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("Next button clicked!");

        if(!kt.trapped())
        {
          kt.makeBestMove();
        }
        frame.repaint();
        //kt.printBoard();
			}
		});
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Prev button clicked!");
			}
		});
		runStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("Run button clicked!");
        //kt.runOnce();
        //kt.runSims(10);
        startTour();
			}
		});
		speed.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				int v = speed.getValue();
				if(v==0)
					v++;
				ticker.setDelay(1000/v);
				System.out.println("new value "+v+" delay is "+ticker.getDelay());
			}  
		});

	}
	public void setUpPanel(){
		panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				kt.draw(g,X_SHIFT, Y_SHIFT, SQ);
			}
		};
		panel.setPreferredSize(new Dimension(800,600));// starting size
		panel.addMouseListener(new MouseAdapter(){
			@Override// Note:  mousePressed usually yields better results than mouseClicked
			public void mousePressed(MouseEvent e){
				handleMousePress(e);
			}
		});// SUPER-ugly syntax, but it DOES make senseâ€¦

		setUpFrame(panel);
	}
	// somewhere else in this class
	public void handleMousePress(MouseEvent e){
		// something like
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		System.out.println("Mouse was pressed!!  at x: "+x+" y: "+y);
		// then, you know where they clicked and which button they pressed
	}

	public void startTour(){
		// get the speed from the slider and change the timer interval
		if(ticker.getActionListeners().length == 0)
			ticker.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(kt.trapped())
          {
            //kt.printBoard();
            stopTour();
          }
					else if(randTour){
						kt.makeRandomMove();
					}
					else{
						kt.makeBestMove();
					}
					frame.repaint();
				}
			});
    ticker.start();
	}
	public void stopTour(){
		ticker.stop();
		frame.repaint();
	}

	public void setUpFrame(JPanel panel){
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
