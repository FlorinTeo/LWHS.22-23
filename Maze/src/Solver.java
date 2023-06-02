import java.util.List;
import java.util.Random;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Solver {
	private Maze m;
	private MazeCanvas mc;
	public Solver(MazeCanvas mc, Maze m) { // this is the method declaration
		this.m = m;
		this.mc = mc;
	}
	public boolean run(Cell cell, Side side) {
		return true;
	}
	public boolean run() { // this is where my error is occuring I am unsure why. all other tests work
		for(int row = 0 ; row < mc.getRows(); row++) {
			for(int col = 0; col < mc.getCols(); col++) {
				m.getCell(row, col).setVisited(false);
			}
		} 
		return run(m.getEntryCell(),  Side.Center);
	}
	public List<Side> shuffle(List<Side> original){
		int size = original.size();
		Side curSide = null;
		int place = 0;
		for(int traverse = 0; traverse < size; traverse ++) {
			curSide = original.get(traverse);
			Random random = new Random();
			place = random.nextInt(size);
			original.remove(curSide);
			original.add(place, curSide );
		}
		return original;
	}
	public Side getOpposite(Side side) {
		if(side.equals(Side.Right)) {
			return Side.Left;
		}else if(side.equals(Side.Left)) {
			return Side.Right;
		}else if(side.equals(Side.Top)) {
			return Side.Bottom;
		}else if(side.equals(Side.Bottom)) {
			return Side.Top;
		}else {
			return Side.Center;
		}
	}
}
