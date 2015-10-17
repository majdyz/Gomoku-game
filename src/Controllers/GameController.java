package Controllers;

import java.util.ArrayList;

import Models.Board;
import Models.Player;
import Views.BoardPanel;
import Views.GridButton;


/**
 * @author zamil.majdy
 * @version 1.0
 *
 * class that control the game flow
 */
public class GameController {
	private static Player black, white; // player object
	private static int  step; // step counter
	private static boolean isWinning;
	private static boolean usingBot;

	/**
	 * Controllers.GameController constructor
	 */
	public GameController() {
		new Board();
		step = 1;
		isWinning = true;
		usingBot = false;
		black = new Player("Black", Board.Pawn.BLACK);
		white = new Player("White", Board.Pawn.WHITE);
	}
	
	/**
	 * play the game
	 */
	public static void play () {
		new Board();
		step = 1;
		isWinning = false;
	}
	
	/**
	 * decide the player turn
	 * @return pemain yang mendapat giliran
	 */
	public static Player getPlayerTurn () {
		if (step % 2 != 0) return white;
		else return black;
	}
	
	/**
	 * method for going to the next turn
	 */
	public static void nextTurn () {
		step++;
		if (step > 19*19) isWinning = true;
	}
	
	/**
	 * set array board, place the pawn
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param ch pawn color
	 */
	public static void set (int x,int y, Board.Pawn ch) {
		Board.set(x, y, ch);
		GameController.cekWinningCondition(x, y);
	}
	
	
	/**
	 * check wether this turn is winning condition
	 * 
	 * @param x 		posisi koordinat baris 
	 * @param y 		posisi koordinat kolom
	 */
	public static void cekWinningCondition(int x, int y) {
		/* array that define 4 directions for the pawn */
		int arahX[] = {0,1,1,1};
		int arahY[] = {1,0,1,-1};
		
		/* cek all directions */
		for (int i = 0; i < 4; i++) {
			Board.Pawn ch   = Board.get(x,y);
			ArrayList<GridButton> buttons = new ArrayList<GridButton>();
			int countMin = 1;
			int countAdd = 1; 
			
			while (Board.get(x + arahX[i]*countAdd, y + arahY[i]*countAdd) == ch) {
				buttons.add(BoardPanel.getButton(x + arahX[i]*countAdd, y + arahY[i]*countAdd));
				countAdd++;
			}
			
			while (Board.get(x - arahX[i]*countMin, y - arahY[i]*countMin) == ch) {
				buttons.add(BoardPanel.getButton(x - arahX[i]*countMin, y - arahY[i]*countMin));
				countMin++;
			}
			
			/* if there are 5 pawn in a row, the last player win */
			if (countAdd + countMin - 1 >= 5) {
				isWinning = true;
				buttons.add(BoardPanel.getButton(x, y));

				for (GridButton button : buttons) {
					button.toWinningButton();
					button.repaint();
				}
			}
		}
	}	

	public static boolean isWinning () {
		return isWinning;
	}

	/* accessor */
	public static boolean isUsingBot() {
		return usingBot;
	}

	/* mutator */
	public static void usingBot(boolean bol) {
		usingBot = bol;
	}
}
