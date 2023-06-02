import java.awt.Color;

import graphics.MazeCanvas;

public class ExitCell extends EdgeCell{
	public static final Color exitColor = Color.red;
	public ExitCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col);
		mazeCanvas.drawCell(row, col, exitColor);
	}
}
