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
		
		Scanner in = new Scanner(System.in);
		gameSetup(in);
		
		Board b = new Board(width, height);
		b.printBoard();
		
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
			
			moves++;
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
		return b.inARow(col, b.colHeights[col]-1) >= win;
	}
	
	// allows user to customize settings of the game
	static void gameSetup(Scanner in) {
		
		// obtain input for whether to customize the settings
		System.out.println(Messages.settings);
		String input = in.nextLine();

		while (true) {
			if (input.length() != 0) {
				if (input.charAt(0) == 'Y' || input.charAt(0) == 'y') {
					break; // proceed with settings customization
				}
				else if (input.charAt(0) == 'N' || input.charAt(0) == 'n') {
					return; // resume without settings customization
				}
			}
			// if input is invalid, re-prompt for valid input
			System.out.println(Messages.invalidInput + Messages.settings);
			input = in.nextLine();
		}
		
		// obtain customizable input from user
		width = numberPrompt(in, Messages.widthPrompt);
		height = numberPrompt(in, Messages.heightPrompt);
		p1 = charPrompt(in, Messages.p1Prompt);
		p2 = charPrompt(in, Messages.p2Prompt);
		win = numberPrompt(in, Messages.winPrompt);
		
	}
	
	// prompts the user for a natural number input and returns it
	static int numberPrompt(Scanner in, String message) {
		
		// prompt for initial input
		System.out.println(message);
		String input = in.nextLine();
		
		// keep re-prompting until a natural number is obtained
		while (!stringIsNatural(input)) {
			System.out.println(Messages.invalidInput + message);
			input = in.nextLine();
		}
		return Integer.parseInt(input);
	}
	
	static char charPrompt(Scanner in, String message) {
		
		// prompt for initial input
		System.out.println(message);
		String input = in.nextLine();
		
		// keep re-prompting until a single character is obtained
		while (input.length() != 1 || input.charAt(0) == ' ' || input.charAt(0) == '.') {
			System.out.println(Messages.invalidInput + message);
			input = in.nextLine();
		}
		return input.charAt(0);
	}
	
	// checks if a string is a natural number (positive integer)
	public static boolean stringIsNatural(String s) {
		// if string is too short or too long, it is not valid
		if (s.length() == 0 || s.length() > 9) {
			return false;
		}
		
		// if string has non-numeric characters, it is not valid
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < '0' || s.charAt(i) > '9') {
				return false;
			}
		}
		
		// only natural number if non-zero
		return Integer.parseInt(s) != 0;
		
	}

}
