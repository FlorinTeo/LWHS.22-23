import java.awt.Color;

import graphics.MazeCanvas;

public class BlockCell extends ShadedCell {
	public static final Color blockCellColor = Color.LIGHT_GRAY;
	public BlockCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas,row, col, blockCellColor);
	}
	public boolean getVisited(){
		return true;
	}
}
