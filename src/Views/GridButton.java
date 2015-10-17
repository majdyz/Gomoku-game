package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controllers.GameController;
import Models.Board;
import Models.Bot;
import Models.Player;

/**
 * @author zamil.majdy
 * @version 1.10
 *
 *  class that represents GUI Buttons
 */
public class GridButton extends JButton {

	private static final long serialVersionUID = 1L;

	private boolean clicked;
	private boolean winningButton;
	private Color color;
	public int x, y;

	/**
	 * Contructor
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public GridButton(int x, int y) {
		addActionListener(new Click());
		setBackground(Color.red);
		
		clicked = false;
		winningButton = false;
		
		this.x = x;
		this.y = y;

		 setOpaque(false);
		 setContentAreaFilled(false);
		 setBorderPainted(false);
	}
	
	public void repaintButton() {
		Graphics g;
		g = getGraphics();
		paint(g);
	}

	/**
	 * set this button to winning button
	 */
	public void toWinningButton() {
		winningButton = true;
	}
	
	/**
	 * reset the button
	 */
	public void reset() {
		clicked = false;
		winningButton = false;
		repaint();
	}

	public class Click implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Player player = GameController.getPlayerTurn();

			GridButton button = (GridButton) e.getSource();
			
			if (GameController.isWinning()) {
				return;
			}

			if (clicked) {
				if (!winningButton) JOptionPane.showMessageDialog(button, "The cell was already occupied");
				return;
			}

			if (player.getMark() == Board.Pawn.BLACK) {
				button.color = Color.BLACK;
			}

			if (player.getMark() == Board.Pawn.WHITE) {
				button.color = Color.WHITE;
			}
			button.repaintButton();
			GameController.set(x, y, player.getMark());
			clicked = true;

			if (GameController.isWinning()) {
				String s = "";
				if (GameController.isUsingBot() && player.getMark() == Board.Pawn.BLACK) {
					s = "\nYou lose :(";
				}
				JOptionPane.showMessageDialog(button, "Player " + player + " Win!!" + s);
				return;
			}

			GameController.nextTurn();
			
			if (GameController.isWinning()) {
				JOptionPane.showMessageDialog(button, "tie..");
				return;
			}

			if (player.getMark() == Board.Pawn.WHITE && GameController.isUsingBot()) {
				GridButton next = Bot.think();
				next.doClick();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!clicked) {
			return;
		}
		
		if (winningButton) {
			g.setColor(Color.YELLOW);
			g.fillOval(0, 0, super.getWidth(), super.getHeight());
			super.paintComponent(g);
		}
		
		g.setColor(color);
		int gap = 2;
		g.fillOval(gap, gap, super.getWidth() - 2 * gap, super.getHeight() - 2* gap);
	}
}
