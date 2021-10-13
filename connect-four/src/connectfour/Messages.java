// Author: Christopher Wang (christopher.wang@wustl.edu)
// Messages.java: Contains all messages and methods for printing out messages to the console.

package connectfour;

public class Messages {
	
	static final String gameMode = "Do you want to play one-player against a CPU or two-player? (Input 1 or 2)";
	static final String settings = "Do you want to customize the game settings? (Yes/No)";
	static final String widthPrompt = "Enter a number for the width of the board. (Default: 7)";
	static final String heightPrompt = "Enter a number for the height of the board. (Default: 7)";
	static final String p1Prompt = "Enter a single character to represent Player 1's game pieces. (Default: X)";
	static final String p2Prompt = "Enter a single character to represent Player 2's game pieces. (Default: O)";
	static final String winPrompt = "Enter a number for the number of consecutive pieces required to win. (Default: 4)";
	static final String invalidInput = "Invalid input! ";
	
	static final String p1Add = "Player 1: Input the letter of a column to add a piece.";
	static final String p2Add = "Player 2: Input the letter of a column to add a piece.";
	static final String columnFull = "That column is full! Please select another column.";
	static final String invalidAdd = "That column does not exist! Please input a valid column.";
	static final String p1Victory = "Victory to Player 1! Congratulations!";
	static final String p2Victory = "Victory to Player 2! Congratulations!";
	static final String playerVictory = "You won! Congratulations!";
	static final String cpuVictory = "The opponent won! Better luck next time!";
	static final String tiedGame = "Nobody won! The game has ended in a tie.";
	
}
