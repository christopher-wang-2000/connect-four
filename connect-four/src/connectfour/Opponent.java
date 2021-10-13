package connectfour;

import java.util.*;

public class Opponent {
	
	static final int noGoodMoves = -2; // findGoodMoves return value
	static final int hasGoodMoves = -1; // findGoodMoves return value
	
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
		int move = findGoodMoves(b, symbol, ConnectFour.p1, goodMoves, 1);
		
		// if there is a move that will force a win on your next turn, do it
		if (move >= 0) {
			b.addPiece(move, symbol);
			return move;
		}
		
		if (move == hasGoodMoves) {
			// pick a random column of the available good moves
			col = goodMoves.get(r.nextInt(goodMoves.size()));
			b.addPiece(col, symbol);
			return col;
		}
		
		// otherwise, there are no good moves, so pick a random column
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
	
	// searches for moves that don't let the player win next turn
	// if a move doesn't lead to a loss, add it to the goodMoves list
	//
	// if a move forces a win on your next turn, return it immediately
	// otherwise, return hasGoodMoves if there are some moves in goodMoves
	// and return noGoodMoves if there aren't
	//
	// iterate keeps track of whether this is the first call or the recursive call
	static int findGoodMoves(Board b, char symbol, char other,
			ArrayList<Integer> goodMoves, int iterate) {
		
		for (int i = 0; i < b.width; i++) {
			
			// temporarily add a piece
			if (b.addPiece(i, symbol)) {
				
				// if the player cannot instantly win, it is a good move
				if(searchVictory(b, other) == -1) {
					goodMoves.add(i);
					
					// check if there are good moves on the player's side
					// if not, this move forces a win, so return immediately
					if (iterate > 0) {
						ArrayList<Integer> dummy = new ArrayList<>();
						if (findGoodMoves(b, other, symbol, dummy, iterate - 1) == noGoodMoves) {
							System.out.println("hello!");
							return i;
						}
					}
					
				}
				
				b.removePiece(i); // remove the temporary piece
			}
			
		}
		if (goodMoves.size() > 0) {
			return hasGoodMoves;
		}
		return noGoodMoves;
	}
	
	
}
