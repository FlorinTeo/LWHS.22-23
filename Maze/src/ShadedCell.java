import graphics.MazeCanvas;
import java.awt.Color;

public class ShadedCell extends Cell {
	private Color shade;
	
	public ShadedCell(MazeCanvas mc, int row, int col, Color color) {
		super(mc, row, col);
		this.shade = color;
		//draw the shade
		mc.drawShade(row, col, shade);
	}

}
