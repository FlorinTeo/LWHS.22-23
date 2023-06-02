import java.awt.Color;

import graphics.MazeCanvas;

public class EntryCell extends EdgeCell {
	public static final Color entranceColor = Color.green;// color of entrance cell
	public EntryCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col);
		mazeCanvas.drawCell(row, col, entranceColor);
	}

}
