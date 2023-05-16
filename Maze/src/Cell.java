import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side; 
public class Cell {
	
		private int row;
		private int col;
		private MazeCanvas mc;
		private ArrayList<Side> walls;
		private boolean visited;
		
	public Cell(MazeCanvas mc, int row, int col) {
		this.row = row;
		this.col = col;
		this.mc = mc;
		//draw walls
		mc.drawCell(row, col);
		//create walls and add four sides
		this.walls = new ArrayList<Side>();
		this.walls.add(Side.Top);
		this.walls.add(Side.Bottom);
		this.walls.add(Side.Left);
		this.walls.add(Side.Right);
		this.visited =false; 
	}
	/** 
	 * Returns the visited status
	 * @return
	 */
	public boolean getVisited() {
		return visited;
	}
	/**
	 * sets whether or not the cell has been visited.
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	//copy of row
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	public ArrayList<Side> getWalls(){
		ArrayList<Side> wallsCopy = new ArrayList<Side>();
		for(Side wall : walls) {
			wallsCopy.add(wall);
		}
		return wallsCopy;
	}
	
	/**
	 * erases the wall from the list
	 * @param side
	 */
	public void removeWall(Side side) {
		//erase wall from walls
		this.walls.remove(side);
		//erase the wall graphically from the maze)
		mc.eraseWall(row, col, side);
	}
	
	/** RETURN S THE AVAILABLE PATHS OOT OF CELL (PLACE THAT DONT HVABE WALLS)
	 * @return ArrayList<Side> of paths
	 */
		public ArrayList<Side> getPaths() {
			// add each possible path
			ArrayList<Side> paths = new ArrayList<Side>();
			paths.add(Side.Left);
			paths.add(Side.Right);
			paths.add(Side.Top);
			paths.add(Side.Bottom);
			//remove  walls
			for(Side wall: this.walls) {
				paths.remove(wall);
				
			}
			return paths;
		}
}
