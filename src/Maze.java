

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Maze implements ActionListener {
	private int width, depth;
	private boolean debug;
	mazePanel mazeDisplay;
	MazeCell[][] mazeGraph;
	Path2D.Double mazePath, solutionPath;
	Stack<MazeCell> mazePathStack = new Stack<MazeCell>();
	ArrayList<Point> solution;
	boolean solved;
	

	
	Maze(int width, int depth, boolean debug) {
		this.width = width;
		this.depth = depth;
		this.debug = debug;
		
		mazeGraph = new MazeCell[depth][width];
		fillGraph();
		mazePath = new Path2D.Double();
		
		solutionPath = new Path2D.Double();
		solution = new ArrayList<Point>();
		solution.add(new Point(0,0));
		solved = false;
		
		mazePathStack.push(mazeGraph[0][0]);
		mazeDisplay = new mazePanel();
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!mazePathStack.isEmpty())traverseMaze(mazePathStack.peek());
		else {			
			mazeDisplay.debugTimer.stop();
		}
		mazeDisplay.repaint();
	}

	void traverseMaze(MazeCell currentCell) {		
		if(!mazePathStack.isEmpty()){		
			MazeCell temp = mazePathStack.peek().chooseRandomNeighbor();
			if(!solved && temp != null && !solution.contains(new Point(temp.x, temp.y))){
				solution.add(new Point(temp.x, temp.y));
			}
			//System.out.println(mazePathStack.peek().x + ", " + mazePathStack.peek().y);
			
			if(temp != null) {	
				mazePath.moveTo((mazePathStack.peek().x)*mazeDisplay.getWidth()/width + ((mazeDisplay.getWidth()/width)/2), 
						(mazePathStack.peek().y)*mazeDisplay.getHeight()/depth + ((mazeDisplay.getHeight()/depth)/2));
				mazePathStack.push(temp);
				mazePath.lineTo((mazePathStack.peek().x)*mazeDisplay.getWidth()/width + ((mazeDisplay.getWidth()/width)/2), 
						(mazePathStack.peek().y)*mazeDisplay.getHeight()/depth + ((mazeDisplay.getHeight()/depth)/2));
				
				if(temp.x == width-1 && temp.y == depth-1) {
					solved = true;
				}
			} else {
				
				if(!solved) solution.remove(solution.size()-1);
				mazePathStack.pop();
			}
		}		
	}
	
	private void fillGraph() {
		//Arrays.fill(mazeGraph, new MazeCell());
		for(int i =0; i<width*depth; i ++){
			mazeGraph[(i/width)%depth][i%width] = new MazeCell();
			mazeGraph[(i/width)%depth][i%width].y = (i/width)%depth;
			mazeGraph[(i/width)%depth][i%width].x = i%width;
			
			//System.out.println(mazeGraph[(i/width)%depth][i%width].x + " " + mazeGraph[(i/width)%depth][i%width].y);
			
		}

		mazeGraph[0][0].north = false;
		mazeGraph[0][0].visited = true;
		mazeGraph[depth-1][width-1].south = false;
		
		
	}


	private class mazePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		Timer debugTimer;
		

		public mazePanel(){
			super();
			if(debug)  debugTimer = new Timer(100, Maze.this);
			else debugTimer = new Timer(0, Maze.this);
			this.setPreferredSize(new Dimension(500, 500));
			debugTimer.start();
			this.setBackground(Color.BLACK);
			mazePath.moveTo(getWidth()/width/2, getHeight()/depth/2);
			solutionPath.moveTo(getWidth()/width/2, getHeight()/depth/2);
		}
		
		@Override
		protected void paintComponent(Graphics theGraphics) {			
			super.paintComponent(theGraphics);
	        final Graphics2D g2d = (Graphics2D) theGraphics;
	        
	        //entrance
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        		RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setStroke(new BasicStroke((mazeDisplay.getWidth()/width)/10*8));
	        

	        g2d.setPaint(Color.GREEN);
	        g2d.draw(new Line2D.Double(new Point(getHeight()/depth/2, 0), new Point(getHeight()/depth/2, 0)));
	        g2d.setPaint(Color.RED);
	        g2d.draw(new Line2D.Double(new Point(getHeight()- getHeight()/depth/2, getHeight()+getHeight()/depth/3-5), new Point(getHeight()-getHeight()/depth/2, getHeight()+getHeight()/depth/3-5))); 
	        g2d.setPaint(Color.WHITE);
	        
	        

	        g2d.setStroke(new BasicStroke((int)(mazeDisplay.getWidth()/width)/10*8));
            //mazePath.lineTo(50,50);
            g2d.draw(mazePath);
            if(!debugTimer.isRunning()){
            	g2d.setStroke(new BasicStroke((mazeDisplay.getWidth()/width)/10*7));
                g2d.setPaint(Color.BLUE);
                solutionPath.moveTo(getWidth()/width/2, getHeight()/depth/2/10*9);
                for(Point p : solution){
                	solutionPath.lineTo((p.x)*mazeDisplay.getWidth()/width + ((mazeDisplay.getWidth()/width)/2), 
                			(p.y)*mazeDisplay.getHeight()/depth + ((mazeDisplay.getHeight()/depth)/2));
                }
                solutionPath.lineTo((width-1)*mazeDisplay.getWidth()/width + ((mazeDisplay.getWidth()/width)/2), getHeight() - getHeight()/depth/2/10*9);
                //mazePath.lineTo(50,50);
                g2d.draw(solutionPath);
            }
            //g2d.draw(new Rectangle2D.Float(1,1,100,100));
		}
	}
	
	private class MazeCell {		
		boolean north = true, south= true, west= true, east= true, visited = false;
		int x, y;
		public MazeCell(){
			x = 0;
			y = 0;
		}
		
		ArrayList<MazeCell> getUnvisitedNeighbors() {
			ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
			if(y>0 && !mazeGraph[y-1][x].visited) neighbors.add(mazeGraph[y-1][x]);//north
			if(x>0 && !mazeGraph[y][x-1].visited) neighbors.add(mazeGraph[y][x-1]);//west
			if(x<width-1 && !mazeGraph[y][x+1].visited) neighbors.add(mazeGraph[y][x+1]);//east
			if(y<depth-1 && !mazeGraph[y+1][x].visited) neighbors.add(mazeGraph[y+1][x]);//south
			return neighbors;
		}
		
		MazeCell chooseRandomNeighbor() {
			MazeCell result = null;
			if(getUnvisitedNeighbors().size() > 0){
				result = getUnvisitedNeighbors().get((int) (Math.random()*getUnvisitedNeighbors().size()));
				if(this.x == result.x) {
					if(this.y < result.y){
						this.south = false;
						result.north = false;
					} else if(this.y > result.y){
						this.north = false;
						result.south = false;
					}
				} else {
					if(this.x < result.x){
						this.east = false;
						result.west = false;
					} else if(this.x > result.x){
						this.west = false;
						result.east = false;
					}
				}
				result.visited = true;
			}			
			return result;
		}
		
		
		
		
	}	
}





