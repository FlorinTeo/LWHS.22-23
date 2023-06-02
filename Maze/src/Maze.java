/*
 * @author Samuel Oliver Slutz
 * @ date 2/26/23
 * @ period 2
 */
import java.awt.Color;
import java.util.Random;
import graphics.MazeCanvas.Side;
import graphics.MazeCanvas;

public class Maze {
	private Cell[][] gridOfCells;
	private Cell entryCell;
	private Cell exitCell;
	private int rows;
	private int cols;
	private MazeCanvas mazeCanvas;
	public static final Color  col = Color.RED;
	public Maze(MazeCanvas mazeCanvas){ // constructor
		this.mazeCanvas = mazeCanvas;
		this.rows = mazeCanvas.getRows();
		this.cols = mazeCanvas.getCols(); 
		gridOfCells = new Cell[cols][rows];
	}
	public void genSnake() {
		for(int curRow = 0 ; curRow < rows; curRow++ ) {
				for(int curCol = 0; curCol < cols; curCol++) {
					if(curRow == 0|| curRow == rows - 1) {
						mazeCanvas.drawCell(curRow, curCol,Color.RED.darker()); // applies to all tops + bottoms, basic cell draw
						if(curRow == 0) { // for cells at the minimum row
							if(curCol % 2 == 0) { // for evens
								mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Left);
								mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Bottom);
								mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Left, Color.RED);
							}else { // for odds
								mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Right);
								mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Right, Color.RED);
							}
							mazeCanvas.drawPath( curRow, curCol, MazeCanvas.Side.Bottom, Color.RED); // applies to all of them
							mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Center, Color.RED.darker());
						}else if(curRow == rows - 1) { // for cells at the maximum row
							mazeCanvas.drawCell(curRow, curCol,Color.RED.darker());
							if(curCol % 2 == 0) { // if even
								mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Right);
								mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Right, Color.RED);
							}else { // if odd
								mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Left);
								mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Left, Color.RED);
							}
							// applies to evens + odds
							mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Top); // when you call parameters use the class with methods use the instance of the class
							mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Center, Color.RED.darker());
							mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Top, Color.RED);
						}
					}else { // for most cells except for those special ones on the top and bottom 
						mazeCanvas.drawCell(curRow, curCol, Color.RED);
						mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Top);
						mazeCanvas.eraseWall(curRow, curCol, MazeCanvas.Side.Bottom);
						mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Top, Color.RED);
						mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Bottom, Color.RED);
						mazeCanvas.drawPath(curRow, curCol, MazeCanvas.Side.Center, Color.RED);
					}
							
							
				}
						
			}						
	}
	
	public void initialize() { // initializes cell list
		int numBlockCells = 0; // counts the number of block cells 
		int perimeter = (cols*2) + (rows*2)-4; // calculates the perimeter of this 
		int area = cols*rows;
		Random random = new Random();
		int exit = random.nextInt(perimeter);
		int entrance = random.nextInt(exit + 3/4 * perimeter);
		int edgeCount = -1; // starts as -1 so I 0 can update edge count at the beggining of a for run through for my edgeCell types
		for(int index = 0; index < mazeCanvas.getRows(); index++) { // two loops to traverse cell list, this one gets the y value
			for(int index2 = 0; index2 < mazeCanvas.getCols(); index2++) { // this loop gets the x value to work with and traverse
				if(index == 0|| index == mazeCanvas.getRows()-1|| index2 == 0|| index2 == mazeCanvas.getCols()-1) {
					edgeCount++;
					if(edgeCount == exit) {
						gridOfCells[index2][index] = new ExitCell(mazeCanvas, index, index2); // for exit cell generation
						exitCell = gridOfCells[index2][index];
					}else if(edgeCount == entrance){
						gridOfCells[index2][index] = new EntryCell(mazeCanvas, index, index2); // for entrance cell generation
						entryCell = gridOfCells[index2][index];
					}else{
						gridOfCells[index2][index] = new EdgeCell(mazeCanvas,index,index2); // index must come first becuase it is y,x for cordinates not x,y this is for edgeCells
					}
				}else {
					if(0.05 > Math.random() && numBlockCells < (area - perimeter)*0.05) {
						gridOfCells[index2][index]= new BlockCell(mazeCanvas, index, index2); // this is for block cells
					}else {
						gridOfCells[index2][index] = new Cell(mazeCanvas, index, index2); // this is for basic cells 
					}
				}
			}
		}
	}
	public Cell getEntryCell() {
		return entryCell;
	}
	public Cell getExitCell() {
		return exitCell;
	}
	public Cell getNeighbor(Cell cell, Side side) {
		int rows = cell.getRow();
		int cols = cell.getCol();
		if(side.equals(MazeCanvas.Side.Left)){
			cols--;
		}else if(side.equals(MazeCanvas.Side.Right)) {
			cols ++;
		}else if(side.equals(MazeCanvas.Side.Bottom)) {
			rows ++;
		}else {
			rows--;
		}
		if(rows < 0 || cols < 0 || rows > mazeCanvas.getRows() - 1|| cols > mazeCanvas.getCols() -1) {
			return null;
		}
		Cell neighbor = gridOfCells[cols][rows];
		return neighbor;
	}
	public Cell getCell(int row, int col) {
		return gridOfCells[col][row];
	}
}
	
	

