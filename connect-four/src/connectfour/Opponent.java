package connectfour;

import java.util.*;

public class Opponent {
	
	static int play(Board b, char symbol) {
		
		Random r = new Random();
		int col = searchVictory(b, symbol);
		
		// if a winning move was found, execute it
		if (col >= 0) {
			b.addPiece(col, symbol); // add the piece there
			return col;
		}
		
		// otherwise, check if any moves will prevent the player from winning
		ArrayList<Integer> goodMoves = new ArrayList<>();		
		if (findGoodMoves(b, symbol, ConnectFour.p1, goodMoves)) {
			// pick a random column of the available good moves
			col = goodMoves.get(r.nextInt(goodMoves.size()));
			b.addPiece(col, symbol);
			return col;
		}
		
		// otherwise, pick a random column
		col = r.nextInt(b.width);
		
		// try adding the piece; if it works, return the column
		while (!b.addPiece(col, symbol)) {
			col = r.nextInt(b.colHeights.length);
		}
		
		return col;
	}
	
	// searches for a move that will instantly win the game
	static int searchVictory(Board b, char symbol) {
		
		for (int i = 0; i < b.width; i++) {
			
			// try adding the piece temporarily
			if (b.addPiece(i, symbol)) {
				
				// if adding a piece here wins the game, return the column
				if (b.inARow(i, b.colHeights[i]-1) >= ConnectFour.win) {
					b.removePiece(i); // remove the temporary piece
					return i;
				}
				b.removePiece(i); // remove the temporary piece
			}
			
		}
		
		return -1; // if no winning move is found
	}
	
	// searches for moves that won't let the player win next turn
	// returns true if there are any, false if there aren't
	static boolean findGoodMoves(Board b, char symbol, char other, ArrayList<Integer> goodMoves) {
		
		for (int i = 0; i < b.width; i++) {
			
			// temporarily add a piece
			if (b.addPiece(i, symbol)) {
				// if the player cannot instantly win, it is a good move
				if(searchVictory(b, other) == -1) {
					goodMoves.add(i);
				}
				b.removePiece(i); // remove the temporary piece
			}
			
		}
		return goodMoves.size() > 0;
	}
	
	
}
