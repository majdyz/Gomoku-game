package Models;

import Models.Board.Pawn;

/**
 * Class that represent player color
 * 
 * @author zamil.majdy
 * @version 1.0
 */

public class Player {
	
	private String color;
	private Pawn mark;
	
	
	/**
	 * colo Accessor
	 * @return color of the player
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Accessor of mark variable
	 * @return player mark
	 */
	public Board.Pawn getMark() {
		return mark;
	}
	
	/**
	 * Class player constructor
	 * 
	 * @param color color player
	 * @param mark  player mark
	 */
	public Player(String color, Pawn mark) {
		this.color = color;
		this.mark = mark;
	}	

	public String toString() {
		return color;
	}
}
