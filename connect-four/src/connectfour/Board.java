package connectfour;

import java.lang.Math;

public class Board {

	int width, height; // dimensions
	char[][] grid; // board containing characters representing pieces
	char[] colLabels; // labels for each column
	int[] colHeights; // keeps track of the height of each column
	
	// all possible directions to traverse on a grid
	enum Direction {
		UPLEFT, UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT
	}
	
	// constructor using width and height
	Board(int w, int h) {
		
		width = w;
		height = h;
		grid = new char[w][h];
		colLabels = new char[w];
		colHeights = new int[w];
		
		// create column labels using capital letters
		for (int i = 0; i < width; i++) {
			colLabels[i] = (char) ('A' + i);
		}
		
		// fill grid with hyphens as default "empty" character
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = '.';
			}
		}
		
	}
	
	// prints out the board with all pieces on it
	void printBoard() {
		
		// print column labels
		for (int i = 0; i < width; i++) {
			System.out.print(colLabels[i] + " ");
		}
		System.out.println(); // line break after column labels
		
		// print from top down, so start with y at top row
		for (int y = height - 1; y >= 0; y--) {
			
			// print from left to right within each row
			for (int x = 0; x < width; x++) {
				System.out.print(grid[x][y] + " ");
			}
			
			System.out.println(); // line break after each row
		}
		
		System.out.println(); // additional line break after the board
	}
	
	// converts character to uppercase
	char toUpper(char c) {
		
		// if lowercase, convert to uppercase
		// as long as lowercase letters are not also labels (width <= 32)
		if (width <= 32 && c >= 'a' && c <= 'z') {
			return (char) (c - 32);
		}
		
		// if not lowercase, then return original character
		return c;
	}
	
	// checks whether a character corresponds to a valid column label
	boolean validLabel(char label) {
		label = toUpper(label); // if lowercase, convert to uppercase
		return (label >= 'A' && label <= 'A' + width - 1);
	}
	
	// takes the column label char and returns the corresponding index
	int getCol(char label) {
		label = toUpper(label);
		return label - 65;
	}
	
	// tries to add a piece to a column; returns true if successful and false if not
	boolean addPiece(int col, char symbol) {
		
		// if the column reaches the top of the board, return false
		if (colHeights[col] >= height) {
			return false;
		}
		
		// add the piece
		grid[col][colHeights[col]] = symbol;
		// increment the height of the column
		colHeights[col]++;
		return true;
	}
	
	int inARow(int x, int y) {
		
		char symbol = grid[x][y];
		
		// check length along bottom-left to top-right diagonal
		int maxLength = 1 + lineLength(x-1, y-1, symbol, Direction.DOWNLEFT)
		+ lineLength(x+1, y+1, symbol, Direction.UPRIGHT);
		
		// check length along vertical
		maxLength = Math.max(maxLength, 1 + lineLength(x, y-1, symbol, Direction.DOWN)
		+ lineLength(x, y+1, symbol, Direction.UP));
		
		// check length along bottom-right to top-left diagonal
		maxLength = Math.max(maxLength, 1 + lineLength(x+1, y-1, symbol, Direction.DOWNRIGHT)
		+ lineLength(x-1, y+1, symbol, Direction.UPLEFT));
		
		// check length along horizontal
		maxLength = Math.max(maxLength, 1 + lineLength(x+1, y, symbol, Direction.RIGHT)
		+ lineLength(x-1, y, symbol, Direction.LEFT));
		
		return maxLength;
		
	}
	
	// returns the number of consecutive pieces of the same type in a given direction
	// from a given starting point - recursive function
	int lineLength(int x, int y, char symbol, Direction d) {
		
		// if the spot being checked is outside the board or has a different piece
		// then the line does not go any further (base case)
		if (x < 0 || y < 0 || x >= width || y>= height || grid[x][y] != symbol) {
			return 0;
		}
		
		// increment x left or right
		if (d == Direction.UPLEFT || d == Direction.LEFT || d == Direction.DOWNLEFT) {
			x--;
		}
		if (d == Direction.UPRIGHT || d == Direction.RIGHT || d == Direction.DOWNRIGHT) {
			x++;
		}
		
		// increment y up or down
		if (d == Direction.UPLEFT || d == Direction.UP || d == Direction.UPRIGHT) {
			y++;
		}
		if (d == Direction.DOWNLEFT || d == Direction.DOWN || d == Direction.DOWNRIGHT) {
			y--;
		}
		
		return 1 + lineLength(x, y, symbol, d); // recursive step
		
	}
	
	
}
