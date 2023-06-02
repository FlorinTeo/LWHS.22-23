import java.util.List;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Cell{ // parameters
	private int row;
	private  int col;
	//private Side side;
	private ArrayList<Side> listOfWalls; 
	private MazeCanvas mazeCanvas;
	private boolean visited;
	
	public Cell(MazeCanvas mazeCanvas, int row, int col) { // constructor 
		this.mazeCanvas = mazeCanvas;
		this.col = col;
		this.row = row;
		listOfWalls = new ArrayList<Side>();
		listOfWalls.add(Side.Top);
		listOfWalls.add(Side.Bottom);
		listOfWalls.add(Side.Left);
		listOfWalls.add(Side.Right);
	
		
		mazeCanvas.drawCell(row, col);
	}
	public ArrayList<Side> getWalls() { // lists the walls
		ArrayList<Side> copy = new ArrayList<Side>();
		for(Side side : listOfWalls) {
			copy.add(side);
		}
		//copy.addAll(listOfWalls);
		return copy;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void  removeWall(Side side) { // removes selected wall
		mazeCanvas.eraseWall(row, col, side);
		listOfWalls.remove(side);
	}
	public boolean getVisited() {
		return visited;
	}
	public void setVisited(boolean visit) {
		if(visit == true) {
			visited = true;
		}else {
			visited = false;
		}
	}
	public List<Side> getPaths(){
		List<Side> listOfPaths = new ArrayList<Side>();
		if(listOfWalls.indexOf(Side.Bottom) < 0) {
			listOfPaths.add(Side.Bottom);
		}
		if(listOfWalls.indexOf(Side.Top) < 0) {
			listOfPaths.add(Side.Top);
		}
		if(listOfWalls.indexOf(Side.Left) < 0) {
			listOfPaths.add(Side.Left);
		}
		if(listOfWalls.indexOf(Side.Right) < 0) {
			listOfPaths.add(Side.Right);
		}
		return listOfPaths;
	}
		
}
