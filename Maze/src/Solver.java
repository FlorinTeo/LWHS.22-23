import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/** 
 * handles solving a new maze
 * @author Abbas
 *
 */
public class Solver {
	
	private MazeCanvas mc;
	private Maze maze;
	
	private Color fwdPath;
	private Color bckPath;
	
	/** solver calss construcotr
	 * 
	 * @param mc
	 * @param maze   
	 */
	public Solver(MazeCanvas mc, Maze maze) {
		this.mc = mc;
		this.maze = maze;
		this.fwdPath = new Color(200, 200, 0);
				this.bckPath = new Color(245, 0, 245);
	}
	
	/**
	 * recursively sovles a maze
	 * @param cell current cell we lookin at
	 * 
	 * @param fromSide
	 * @return
	 */
	private boolean run(Cell cell, Side fromSide) {
	    mc.step();
		int row = cell.getRow();
		int col = cell.getCol();
		//mark cells
		cell.setVisited(true);
		
		//draw a path 
		mc.drawCenter(row, col, this.fwdPath);
		mc.drawPath(row, col, fromSide, this.fwdPath);
		//if done
		if(cell == maze.getExitCell()) {
			return true;
		}
		// get the shuffl;ed paths that we want to explore
		ArrayList<Side> paths = this.shuffle(cell.getPaths());
		//traverse
		for(Side toSide : paths) {
			//get the cell in that direction
			Cell neighbor = this.maze.getNeighbor(cell, toSide);
			if(neighbor.getVisited() == false) {
				//draw a path
				mc.drawPath(row, col, toSide, this.fwdPath);
				//sovle recursively
				boolean solved = run(neighbor, getOpposite(toSide));
				if(solved) {
					return true;
					
				} else {
					//otherwise show that we have explored it
					mc.drawPath(row, col, toSide, this.bckPath);
				}
			}
		}
		//show weve already explored this path
		mc.drawCenter(row, col, this.bckPath);
		mc.drawPath(row, col, fromSide, this.bckPath);
		mc.step(10);
		return false;
	}
	
	/** starts the maze solving process
	 * 
	 * @return
	 */
	public boolean run() {
		//set the visit flag for each cell to false
		
		for(int r = 0; r < mc.getRows(); r++) {
			for(int c = 0; c < mc.getCols(); c++) {
				this.maze.getCell(r, c).setVisited(false);
			}
		}
		//starts solving the maze
		
		return run(this.maze.getEntryCell(), Side.Center);
	}
	public ArrayList<Side> shuffle(ArrayList<Side> sides) {
		//shuffle in place
		for(int i= 0; i <sides.size(); i++) {
			//chose a new index
			int newIndex = (int)(Math.random() * sides.size());
			//swap
			Side temp = sides.get(i);
		
			sides.set(i, sides.get(newIndex));
			sides.set(newIndex, temp);
		}
		return sides;
	}
	
	/**retunring opposite side  
	 * 
	 * @param side intial side
	 * @return
	 */
	public Side getOpposite(Side side) {
		if(side == Side.Left) {
			return Side.Right;
		} else if(side == Side.Right ) {
			return Side.Left;
			
		}else if (side == Side.Top) {
			return Side.Bottom;
			
		} else if(side == Side.Bottom) {
			return Side.Top;
		} else { 
			return side;
		}
	}
}
