import javax.swing.JFrame;

public class Main {
	static JFrame mazeFrame5 = new JFrame("Mazing(5x5)");
	static JFrame mazeFrame10 = new JFrame("Mazing(10x10)");
	public static void main(String[] args) {
		
		genStandardMaze();
		initGUI();
		
		
		//test.traverseMaze(test.mazeGraph[0][0]);
	}
	private static void genStandardMaze() {
		Maze test5 = new Maze(5, 5, true);
		mazeFrame5.add(test5.mazeDisplay);
		
		Maze test10 = new Maze(10, 10, false);
		mazeFrame10.add(test10.mazeDisplay);
		
		
	}
	private static void initGUI() {
		mazeFrame10.setVisible(true);		
		mazeFrame10.pack();
		mazeFrame10.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame10.setResizable(false);
		mazeFrame10.setLocationRelativeTo(null);
		mazeFrame5.setVisible(true);		
		mazeFrame5.pack();
		mazeFrame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame5.setResizable(false);
				
		
	}

}
