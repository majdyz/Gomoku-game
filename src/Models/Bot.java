package Models;

import Models.Board.Pawn;
import Views.BoardPanel;
import Views.GridButton;

/**
 * @author zamil.majdy
 * @version 1.1
 *
 * Goodguy bot (AI) when using 'playing against bot mode'
 */
public class Bot {
	/* board size */
	private static final int SIZE = 19;
	
	/* pawn directions move*/
	private static final int directionX[] = {0,1,1,1};
	private static final int directionY[] = {1,0,1,-1};
	
	/* heuristik constant for calculating combo score */
	private static final int SCORE[][] = {{0,10,100,1000,100000,100000},{0,0,10,100,100000,100000},{0,0,0,0,100000,100000}};
	
	/**
	 * Simple greedy best first search (find highest score)
	 * @return bot's answer
	 */
	public static GridButton think () {
		GridButton ret = null;
		int score = -1;
		
		for (int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				if (Board.get(i,j) != Pawn.BLANK) continue;
				int tmp = getScore(i,j);
				if (tmp > score) {
					ret 	= BoardPanel.getButton(i, j);
					score	= tmp;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * method for calculating the score (best possible action)
	 * @param x coordinate x
	 * @param y coordinate y
	 * @return score
	 */
	public static int getScore (int x,int y) {
		return Math.max((int)(getCombo(x, y, Board.Pawn.BLACK)*1.5-1),getCombo(x, y, Board.Pawn.WHITE));
	}
	
	
	/**
	 * helper method for getscore 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param pawn Color of the pawn
	 * @return score combo
	 */
	public static int getCombo(int x, int y, Board.Pawn pawn) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int maks = 0;

			for (int j = 0; j < 5; j++) {
				int now = compute(x, y, pawn, j, i);
				maks = Math.max(maks, now);
			}

			ret += maks;
		}
		return ret;
	}
	
	
	/**
	 * helper method of getCombo method
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param pawn pawn color
	 * @param count count pawn distribution
	 * @param i index direction
	 * @return
	 */
	public static int compute(int x, int y, Board.Pawn pawn, int count, int i) {
		int countMin = count;
		int countAdd = 4 - count;
		int now = 0;
		int kurang = 0;
		
		if (Board.get(x + directionX[i], y + directionY[i]) == Pawn.BLANK && countMin > 0)
			kurang++;
		
		if (Board.get(x - directionX[i], y - directionY[i]) == Pawn.BLANK && countAdd > 0)
			kurang++;
		
		for (int k=1; k<=countMin; k++) {
			Pawn ch = Board.get(x + directionX[i]*k, y + directionY[i]*k);
			if (ch.equals(pawn)) {
				now++;
			}
			else if (ch != Board.Pawn.BLANK) {
				now = 0;
				return 0;
			}
		}
		
		for (int k=1; k<=countAdd; k++) {
			Pawn ch = Board.get(x - directionX[i]*k, y - directionY[i]*k);
			if (ch.equals(pawn)) {
				now++;
			}
			else if (ch != Board.Pawn.BLANK) {
				now = 0;
				return 0;
			}
		}
		
		countMin++;
		countAdd++;
		
		int block = 0;
		
		if (Board.get(x - directionX[i]*countAdd, y - directionY[i]*countAdd) != Pawn.BLANK) {
			block++;
		}
		
		if (Board.get(x + directionX[i]*countMin, y + directionY[i]*countMin) != Board.Pawn.BLANK) {
			block++;
		}
		
		int ret = SCORE[block][now]; 
		
		if (ret > 200) ret -= kurang*100;
		else ret -= kurang*10;
		
		if (special(x,y, pawn,now+1,i)) ret *= 2;

		return ret;
	}
	
	/**
	 * this method handle the special case on getScore method
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param pawn pawn color
	 * @param count adjacent pawn count
	 * @param i direction index
	 * @return true if special case found
	 */
	public static boolean special (int x,int y, Board.Pawn pawn, int count,int i) {
		int countMin = 1;
		int countAdd = 1; 
		
		while (Board.get(x + directionX[i]*countAdd, y + directionY[i]*countAdd) == pawn) {
			countAdd++;
		}
		
		while (Board.get(x - directionX[i]*countMin, y - directionY[i]*countMin) == pawn) {
			countMin++;
		}
		
		if (countAdd + countMin - 1 < count) return false;
		
		return true;
	}
		
}