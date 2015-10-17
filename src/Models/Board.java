package Models;

/**
 * Class that represent the board
 * 
 * @author zamil.majdy
 * @version 1.0
 */
public class Board {

	/* color enumeration */
	public enum Pawn {
		BLACK, WHITE, BLANK, OUT;
	}

	private static Pawn map[][];
	private static final int BOARD_SIZE = 19;

	/**
	 * constructor
	 */
	public Board() {
		map = new Pawn[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				map[i][j] = Pawn.BLANK;
			}
		}
	}

	/**
	 * Accessor map
	 * 
	 * @param x   row coordinate
	 * @param y   column coordinate
	 * @return char that represent the cell ('.' empty, 'B' for bot/black, 'P' player/white)
	 */
	public static Pawn get(int x, int y) {
		if (!ok(x, y))
			return Pawn.OUT;
		else
			return map[x][y];
	}

	/**
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @param ch 'B' / 'W'
	 */
	public static void set(int x, int y, Pawn ch) {
		if (ok(x, y) && map[x][y].equals(Pawn.BLANK)) {
			map[x][y] = ch;
		}
	}

	/**
	 * check validity of board coordinate
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return boolean
	 */
	public static boolean ok(int x, int y) {
		if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE)
			return true;
		return false;
	}
}
