import javax.swing.plaf.metal.MetalComboBoxUI.MetalComboBoxLayoutManager;
/**
 * runs the maze 
 */
import graphics.MazeCanvas;

public class Program {
	
	 
		public static final int ROWS = 25;
		public static final int COLS = 40;
		public static final int SIZE = 20;

		public static void main(String[] args) {
			
			MazeCanvas mc = new MazeCanvas(ROWS, COLS, SIZE);
			
			Maze maze = new Maze(mc);
			
			mc.open();
			maze.initialize();
			
			Generator generator = new Generator(mc, maze);
		generator.run();
			
			mc.pause();
			Solver solver = new Solver(mc, maze);
			solver.run(); 
			mc.pause();
			
			mc.close();
			
		}
}
