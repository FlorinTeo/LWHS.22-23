import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/** Handles generating a new maze
 * 
 * @author Abbas
 *
 */
public class Generator {
	
	private MazeCanvas mc;
	private Maze maze; 
	private Color fwdPath;
	/** constructor
	 * 
	 * @param mc
	 * @param maze
	 */
	public Generator(MazeCanvas mc, Maze maze) {
		this.mc = mc;
		this.maze = maze;
		this.fwdPath = new Color(0, 255, 255);
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
	/**
	 * recursively calculates the cell and maze path
	 * @param cell
	 * @param fromSide
	 * @return false ALWAYS
	 */
	private boolean run(Cell cell, Side fromSide) {
		int row = cell.getRow();
		int col = cell.getCol();
		//mark cells visited
		cell.setVisited(true);
		//draw a path so the use rcan see us working on this cell 
		mc.drawPath(row, col, fromSide, this.fwdPath);
		//mc.drawPath(row, col,Side.Center, this.fwdPath);
		mc.drawCenter(row, col, this.fwdPath);
		//remove the cells from walls
		cell.removeWall(fromSide);
		//get the shuffed lcell walls
		ArrayList<Side> shuffledWalls = shuffle(cell.getWalls());
		//traverse through arraylist
		for(Side toSide : shuffledWalls) {
			//get the cell in that direction
			Cell nextCell = maze.getNeighbor(cell, toSide);
			//if not visited i revisit
			if(!nextCell.getVisited()) {
				cell.removeWall(toSide);
				//draw a path to that side
				mc.drawPath(row, col, toSide, fwdPath);
				//recursively call run on the neighbor cell
				run(nextCell, getOpposite(toSide));
				//once I'm done i erase the path to cell
				mc.erasePath(row, col, toSide);
				
			}
		}
		//done with cell erase the paths
		mc.erasePath(row, col, fromSide);
		mc.eraseCenter(row, col);mc.step(10);
		return false;

	}
	/**
	 * 
	 * @return
	 */
	public boolean run() {
		return run(maze.getEntryCell(), Side.Center);
			
		}
	}

