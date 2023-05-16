import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/** Defines the cells and fields of our maze
 * 
 * @author Abbas
 *
 */
public class Maze {
	//constants
	public static final Color RED = Color.RED;
	public static final Color DARK_RED = new Color(150,0,0);
	private Cell[][] cells;
	private MazeCanvas mc;
	private Cell entryCell;
	private Cell exitCell;
	
	public Maze(MazeCanvas mc) {
		this.mc = mc;
		cells = new Cell[mc.getRows()][mc.getCols()];
	}
	/*
	 * intitializes array for the maze
	 */
	public void initialize() {
		
		
		
		//loop through cells

		int rows = mc.getRows();
		int cols = mc.getCols();
		//peimiter
		int nPerim = 2 * rows + 2* cols -4;
		int iEntry = (int)(Math.random()* nPerim);
		int iExit = (iEntry + 1 + (int)(Math.random() * (nPerim - 1))) % nPerim;
		int edgeCount = 0;
		// only 5% oif cells can be block celss 
		int blockCellMax = (int)(rows * cols * 0.05);
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				//is this an edge??
				if(row == 0 || row == rows -1 || col == 0 || col == cols - 1) {
					
				if(edgeCount == iEntry) {
					cells[row][col] = new EntryCell(mc, row, col);
					entryCell = cells[row][col];
					
				} else if(edgeCount == iExit) {
					cells[row][col]= new ExitCell(mc, row, col);
					exitCell = cells[row][col];
				}
				else {
					this.cells[row][col] = new EdgeCell(mc, row, col);
				}
				edgeCount++;
				} else if(Math.random() < 0.05 && blockCellMax > 1) {
				//block cells	
					blockCellMax--;
					this.cells[row][col] = new BlockCell(mc, row, col);
				}else {
					//normal cell
					this.cells[row][col] = new Cell(mc, row, col);
				}
			}
		}
	}
	/**
	 * gets the cell of the maze
	 * @param row 
	 * @param col 
	 * @return cell row of col
	 */
	public Cell getCell(int row,int col) {
		return cells[row][col];
	}
	
	// practices using the maze canvas
	
	public void genSnake() {
		//loop through all the rows and columns of the maze
		for(int row = 0; row < mc.getRows(); row++) {
			for(int col = 0; col < mc.getCols(); col++) {
			//all cells
				mc.drawCell(row, col);
				mc.eraseWall(row, col, Side.Top);
				mc.eraseWall(row,col, Side.Bottom);
			
			//top cells
				if(row == 0) {
					mc.drawCenter(row, col, DARK_RED);
					mc.drawPath(row, col, Side.Bottom, RED);
					//even
					if(col % 2 == 0) {
						mc.eraseWall(row, col, Side.Left);
						mc.drawPath(row, col, Side.Left, RED);
					}else {
						// odd cols
						mc.eraseWall(row, col, Side.Right);
						mc.drawPath(row, col, Side.Right, RED);
					}
					//bottom cells
				}else if(row == mc.getRows()-1){
					mc.drawCenter(row, col, DARK_RED);
					mc.drawPath(row, col, Side.Top, RED);
					//odd col
					if(col % 2 ==1) {
						mc.eraseWall(row, col, Side.Left);
						mc.drawPath(row, col, Side.Left, RED);
					} else {
						//even col
			
							mc.eraseWall(row, col, Side.Right);
							mc.drawPath(row, col, Side.Right, RED);
					}
				}else {
				
			//middle cells
				mc.drawCenter(row, col, RED);
				mc.drawPath(row, col, Side.Bottom, RED);
				mc.drawPath(row, col, Side.Top, RED);
				
				
			}
		}
	}
}
		/**
		 * gets entry cell
		 * @return entry cell
		 */
	public Cell getEntryCell() {
		return this.entryCell;
	}
	/**
	 * gets exit cell
	 * @return exit cell
	 */
	public Cell getExitCell() {
		return this.exitCell;
	}
	/**
	 * returns the nieghbor cell in the direction of sdide
	 * @param cell
	 * @param side
	 * @return
	 */
	public Cell getNeighbor(Cell cell, Side side) {
		int row = cell.getRow();
		int col = cell.getCol();
		int rows = mc.getRows();
		int cols = mc.getCols();
		//  adjust the row and columbn based on the dir3ection
		
		if(side == Side.Left) {
			col--;
		} else if(side == Side.Right) {
			col++;
		} else if(side == side.Top) {
			row--;
		} else if(side == Side.Bottom) {
			row++;
		}
		//are we outside the maze? if so return null
	
	if(row < 0 || col < 0 || row >= rows || col >= cols) {
		return null;
	}
	return cells[row][col];
	}
	
	
}
