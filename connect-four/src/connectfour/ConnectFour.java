package connectfour;

import java.util.Scanner;

public class ConnectFour {
	
	// default game settings
	static int width = 7;
	static int height = 7;
	static char p1 = 'X'; // symbol for Player 1's pieces
	static char p2 = 'O'; // symbol for Player 2's pieces
	static int win = 4; // number in a row needed to win 

	public static void main(String[] args) {
		
		Board b = new Board(width, height);
		
		b.printBoard();
		
		Scanner in = new Scanner(System.in);
		
		int moves = 0; // keeps track of number of moves so far
		
		// game continues until board is full
		while (moves < width*height) {
			
			int col;
			
			// if it's Player's 1 turn
			if (moves % 2 == 0) {
				col = nextTurn(b, in, Messages.p1Add, p1);
			}
			
			// if it's Player's 2 turn
			else {
				col = nextTurn(b, in, Messages.p2Add, p2);
			}
			
			b.printBoard();
			moves++;
			
			// check if someone won
			if (checkVictory(b, col)) {
				
				// print victory message for winning player
				if (moves % 2 == 0) {
					System.out.println(Messages.p1Victory);
				}
				else {
					System.out.println(Messages.p1Victory);
				}
				
				in.close();
				return;
			}
		}
		
		// if nobody won, print message indicating that game ended in a tie
		System.out.println(Messages.tiedGame);
		in.close();
	}
	
	// controls the handling of each turn, whether by Player 1 or Player 2
	// returns the column to which the piece was added
	static int nextTurn(Board b, Scanner in, String playerMessage, char symbol) {
		
		System.out.println(playerMessage);
	    String columnToAdd = in.nextLine();
	    
	    // reprompt until valid input is obtained
	    while (true) {
	    	
	    	// if the input is not a valid column, reprompt
	    	if (columnToAdd.length() == 0 || !b.validLabel(columnToAdd.charAt(0))) {
	    		System.out.println(Messages.invalidAdd);
			    columnToAdd = in.nextLine();
			    continue;
	    	}
	    	
	    	// get input column
	    	int col = b.getCol(columnToAdd.charAt(0));
	    	
	    	// try adding the piece to the board
	    	if (b.addPiece(col, symbol)) {
	    		System.out.println();
	    		return col; // if successful, return column
	    	}
	    	
	    	// if unsuccessful, re-prompt
	    	System.out.println(Messages.columnFull);
		    columnToAdd = in.nextLine();
	    }
		
	}
	
	// checks if the player who just placed a piece in this column has just won the game
	static boolean checkVictory(Board b, int col) {
		// if more than four in a row, then victory has occurred
		return b.inARow(col, b.colHeights[col]-1) >= 4;
	}
	

}
