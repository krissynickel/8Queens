import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class a0 {
	
	// main method takes integer argument from user and passes to initialSetup 
	public static void main(String[] args) {
		int numWindows = 0;
		try {
			numWindows = Integer.parseInt(args[0]);
		} catch(Exception e) {
			throw new IllegalArgumentException("Please provide an integer argument.");
		}	
		initialSetup(numWindows);
	}

	// initialSetup uses the passed int to calculate the distance between windows and passes that
	// distance and the users input to makeWindows
	public static void initialSetup(int numWindows) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int distance = width / numWindows;
		makeWindows(numWindows, distance);
	}

	// makeWindows sets up the number of windows/buttons per the users initial input at a distance
	// apart based on the initialSetup calculation, sets up TimerTasks on an action to increment the number
	// displayed on the buttons at increments specific to each button, disabled button after initial action
 	public static void makeWindows(int numWindows, int distance) {
		for (int i = 0; i < numWindows; i++) {
			JFrame frame = new JFrame("Hello");	
			JButton button = new JButton("0");
			frame.getContentPane().add(button);
			frame.setVisible(true);
			frame.setLocation(distance * i, 0);	
			frame.setSize(distance, 100);
			int inc = i;
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					button.setEnabled(false);
					Timer timer = new Timer("Timer");
					TimerTask update = new TimerTask() {
						public void run() {
							int curr = Integer.parseInt(button.getText());
							int newTime = curr + 1;
							String newText = Integer.toString(newTime);
							button.setText(newText);
						}
					};
					timer.scheduleAtFixedRate(update, 1000 * (inc + 1), 1000 * (inc + 1));
				}
			});
		}			
	}

}
