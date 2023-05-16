import java.awt.Color;

import graphics.MazeCanvas;
/** a block cell is a cell that cannot be passed through
 * 
 * @author Abbas
 *
 */
public class BlockCell extends ShadedCell {
	
	private static final Color BLOCK_COLOR = Color.LIGHT_GRAY;

	public BlockCell(MazeCanvas mc, int row, int col) {
		super(mc, row, col, BLOCK_COLOR);
		// TODO Auto-generated constructor stub
	
	}
/**
 * overriding
 * @return always true
 */
		public boolean getVisited() {
			return true;
		}
}
