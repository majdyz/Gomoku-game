package Controllers;

import Views.BoardPanel;
import Views.GameMenu;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * @author zamil.majdy
 * @version 1.1
 *
 * main frame class
 */
public class GraphicController extends JFrame {

	private static final long serialVersionUID = 1L;

	private static BoardPanel board;
	private static GameMenu menu;

	/**
	 * constructor
	 */
	public GraphicController() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		board = new BoardPanel();
		menu = new GameMenu();
		setResizable(false);
		menu.setVisible(true);
		add(menu, BorderLayout.NORTH);
		add(board, BorderLayout.EAST);
		pack();
		setVisible(true);
	}

	/**
	 * reset the frame
	 */
	public static void reset() {
		BoardPanel.reset();
	}
}
