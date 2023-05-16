import java.awt.Color;

import graphics.MazeCanvas;
/** makes an exit cell for the maze to reach
 * 
 * @author Abbas
 *
 */
public class ExitCell extends EdgeCell{
//fields
	public static final Color Exit = Color.RED;
	//constructor and sets a color for an edge cell
	
	public ExitCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col);
		// TODO Auto-generated constructor stub
		mc.drawShade(row, col, Exit);
	}

}
