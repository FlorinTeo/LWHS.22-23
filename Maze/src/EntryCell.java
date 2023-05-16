import java.awt.Color;

import graphics.MazeCanvas;
/**
 * an entry cell for the maze colored usually in green or any color that its initialized to
 * @author Abbas
 *
 */
public class EntryCell extends EdgeCell {
	//fields for color
	public static final Color Entry = Color.GREEN;
	//constructor 
	public EntryCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col);
		// TODO Auto-generated constructor stub
		mc.drawShade(row, col, Entry);
	}

}
