import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Maze test = new Maze(25,25, true);
		JFrame testFram = new JFrame("mazing");
		testFram.setVisible(true);
		testFram.add(test.mazeDisplay);
		testFram.pack();
		testFram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//test.traverseMaze(test.mazeGraph[0][0]);

	}

}
