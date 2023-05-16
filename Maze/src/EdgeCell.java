import java.awt.Color;
import java.util.ArrayList;

import javax.print.attribute.standard.Sides;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/**'stores a cell at the edges of the maze
 * 
 * @author Abbas
 *
 */
public class EdgeCell extends ShadedCell{
	
	private final static Color edgeColor = new Color(0, 150, 150);
	
	
		//fields
	private ArrayList<Side> edges;
	
	
	/**
	 * 
	 * @param mc a ref ti the maze canvas
	 * @param row the cells row
	 * @param col the cells column
	 */
	public EdgeCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col, edgeColor);
		//populate the list of edges
		edges = new ArrayList<Side>();
		
		if(row == 0) {
			edges.add(Side.Top);
			
		} else if(row == mc.getRows() - 1) {
			edges.add(Side.Bottom);
		}
		if (col == 0) {
			edges.add(Side.Left);
			
		} else if(col == mc.getCols() - 1) {
			edges.add(Side.Right);
		}
		//left and right colums
	}
	
	
	
	/**
	 * overrider
	 */
	public ArrayList<Side> getWalls() {
		//get all walls
		ArrayList<Side> walls = super.getWalls();
		for(Side side : edges) {
			walls.remove(side);
		}
		return walls;
	}
	
	/**
	 * ovverides the getPaths to remove edges
	 * 
	 * @return a list of valid paths ffrom the cell wtih the edges removed
	 */
	public ArrayList<Side> getPaths() {
		ArrayList<Side> paths = super.getPaths();
		for(Side edge: this.edges) {
			paths.remove(edge);
			
		}
		return paths;
	}

}
