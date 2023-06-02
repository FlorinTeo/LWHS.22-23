import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell{
	public ArrayList<Side> listOfEdges;
	public static final Color edgeColor = Color.cyan;
	public EdgeCell (MazeCanvas mazeCanvas,int row, int col){
		super(mazeCanvas, row, col, edgeColor);
		listOfEdges = new ArrayList<Side>();
		if(row <= mazeCanvas.getYMin()) {
			listOfEdges.add(Side.Top);
		}else if(row == mazeCanvas.getRows()-1) {
			listOfEdges.add(Side.Bottom);
		}
		if(col <= mazeCanvas.getXMin()) {
			listOfEdges.add(Side.Left);
		}else if(col == mazeCanvas.getCols()-1) {
			listOfEdges.add(Side.Right);
		}
	}
	
	public ArrayList<Side> getPaths(){
		// determines directions in which the player can move (niether walls nor edges)
		ArrayList<Side> listOfPaths = new ArrayList<Side>();
		if(super.getWalls().indexOf(Side.Bottom) < 0 && listOfEdges.indexOf(Side.Bottom) < 0) {
			listOfPaths.add(Side.Bottom);
		}
		if(super.getWalls().indexOf(Side.Top) < 0 && listOfEdges.indexOf(Side.Top) < 0) {
			listOfPaths.add(Side.Top);
		}
		if(super.getWalls().indexOf(Side.Left) < 0 && listOfEdges.indexOf(Side.Left) < 0) {
			listOfPaths.add(Side.Left);
		}if(super.getWalls().indexOf(Side.Right) < 0 && listOfEdges.indexOf(Side.Right) < 0) {
			listOfPaths.add(Side.Right);
		}
		return listOfPaths;
	}
	public  ArrayList<Side> getWalls() {
		ArrayList<Side> walls = super.getWalls();
		for(Side side : listOfEdges) {
			walls.remove(side);
			
		}
		return walls;
	}

}
