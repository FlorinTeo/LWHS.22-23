import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Generator {
	private final Color generatePathColor = Color.pink;
	private Maze maze;
	private MazeCanvas mazeCanvas;
	
	public Generator(MazeCanvas mazeCanvas, Maze maze) {
		this.maze = maze;
		this.mazeCanvas = mazeCanvas;
	}
	public ArrayList<Side> shuffle(ArrayList<Side> original){
		int size = original.size();
		Side curSide = null;
		int place = 0;
		for(int traverse = 0; traverse < size; traverse ++) {
			curSide = original.get(traverse);
			Random random = new Random();
			place = random.nextInt(size);
			original.remove(curSide);
			original.add(place, curSide );
		}
		return original;
	}
	public Side getOpposite(Side side) {
		if(side.equals(Side.Right)) {
			return Side.Left;
		}else if(side.equals(Side.Left)) {
			return Side.Right;
		}else if(side.equals(Side.Top)) {
			return Side.Bottom;
		}else if(side.equals(Side.Bottom)) {
			return Side.Top;
		}else {
			return Side.Center;
		}
	}
	public boolean run(Cell cell, Side fromSide) { // original run method
		cell.setVisited(true); // sets cell to visited
		int row = cell.getRow(); // row and col are the cell cordinates
		int col = cell.getCol();
		// sets cell to visited
		mazeCanvas.drawPath(row, col, fromSide, generatePathColor);
		mazeCanvas.drawCenter(row, col, generatePathColor);
		cell.removeWall(fromSide);
		ArrayList <Side> walls = shuffle(cell.getWalls());
		for (Side toSide : walls) {
			Cell n = maze.getNeighbor(cell, toSide);
			if(!n.getVisited()) {
				cell.removeWall(toSide);
				mazeCanvas.drawPath(cell.getRow(), cell.getCol(), toSide, this.generatePathColor);
				run(n, getOpposite(toSide));
				//mazeCanvas.erasePath(cell.getRow(), cell.getCol(), toSide);
			}
		}
		mazeCanvas.erasePath(row, col, fromSide);
		mazeCanvas.eraseCenter(row, col);
		mazeCanvas.step(10);
		return false;
	}
	public boolean run() { // overloaded run method uses run method.
		return run(maze.getEntryCell(), Side.Center);
	}
	
}
