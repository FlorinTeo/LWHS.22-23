import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell {

    // Pale-red color for the shade of an edge cell
    private static final Color _edgeColor = new Color(255, 224, 224);
    private ArrayList<Side> _edges = new ArrayList<Side>();
    
    public EdgeCell(MazeCanvas mc, int row, int col) {
        super(mc, row, col, _edgeColor);
        if (row == 0) {
            _edges.add(Side.Top);
        } else if (row == mc.getRows()-1) {
            _edges.add(Side.Bottom);
        }
        if (col == 0) {
            _edges.add(Side.Left);
        } else if (col == mc.getCols()-1) {
            _edges.add(Side.Right);
        }
    }
    
    @Override
    public ArrayList<Side> getWalls() {
        ArrayList<Side> walls = super.getWalls();
        for(Side edge : _edges) {
            walls.remove(edge);
        }
        return walls;
    }
    
    @Override
    public ArrayList<Side> getPaths() {
        ArrayList<Side> paths = super.getPaths();
        for (Side side : _edges) {
            paths.remove(side);
        }
        return paths;
    }
}
