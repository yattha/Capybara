import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// Derek Moore & Heather Pedersen

public class Main {
	static JFrame mazeFrame5 = new JFrame("Mazing(5x5)");
	static JFrame mazeFrame10 = new JFrame("Mazing(10x10)");
	public static void main(String[] args) {
		initGUI();
		genStandardMazes();
		//genTestMazes();
		
		
		
		//test.traverseMaze(test.mazeGraph[0][0]);
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
		
		JMenuBar menu = new JMenuBar();
	
		JMenu options = new JMenu("New Maze");
		menu.add(options);
		
		
		JMenuItem gen5 = new JMenuItem("generate 5x5");
		gen5.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				
				Maze test5 = new Maze(5, 5, false);
				mazeFrame5.add(test5.mazeDisplay);
				mazeFrame5.pack();
			}
		});
		options.add(gen5);
		
		
		JMenuItem gen10 = new JMenuItem("generate 10x10");
		gen10.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				
				Maze test5 = new Maze(10, 10, false);
				mazeFrame5.add(test5.mazeDisplay);
				mazeFrame5.pack();
			}
		});
		options.add(gen10);
		
		JMenuItem gen15 = new JMenuItem("generate 15x15");
		gen15.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				
				Maze test5 = new Maze(15, 15, false);
				mazeFrame5.add(test5.mazeDisplay);
				mazeFrame5.pack();
			}
		});
		options.add(gen15);
		
		
		
		
		JMenuItem gen20 = new JMenuItem("generate 20x20");
		gen20.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				
				Maze test5 = new Maze(20, 20, false);
				mazeFrame5.add(test5.mazeDisplay);
				mazeFrame5.pack();
			}
		});
		options.add(gen20);
		
		
		
		JMenuItem gen25 = new JMenuItem("Generate 25x25");
		gen25.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e) {
				
				Maze test5 = new Maze(25, 25, false);
				mazeFrame5.add(test5.mazeDisplay);
				mazeFrame5.pack();
			}
		});
		options.add(gen25);
		mazeFrame5.setJMenuBar(menu);
		mazeFrame5.pack();
				
		
	}
	
	private static void genStandardMazes() {
		Maze test5 = new Maze(5, 5, true);
		mazeFrame5.add(test5.mazeDisplay);
		mazeFrame5.pack();
		
		Maze test10 = new Maze(10, 10, false);
		mazeFrame10.add(test10.mazeDisplay);		
		mazeFrame10.pack();
	}
	
	
	private static void genTestMazes() {
		Maze test5 = new Maze(15, 15, true);
		mazeFrame5.add(test5.mazeDisplay);
		mazeFrame5.pack();
		Maze test10 = new Maze(20, 20, false);
		mazeFrame10.add(test10.mazeDisplay);
		mazeFrame10.pack();
	}
	

}
