package Views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author zamil.majdy
 * @version 1.1
 * 
 *  class for showing the board to screen
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 19;
	private static GridButton board[][];
	int con = 0;

	/**
	 * constructor
	 */
	public BoardPanel() {

		super(new GridLayout(SIZE, SIZE));

		Dimension size = new Dimension(600, 600);

		setPreferredSize(size);

		board = new GridButton[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = new GridButton(i, j);
				add(board[i][j]);
			}
		}
	}

	/**
	 * method for reset the grid
	 */
	public static void reset() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j].reset();
			}
		}
	}

	/**
	 * method for get all button's information on specific coordinate
	 * @param x koordinat x
	 * @param y koordinat y
	 * @return tombol
	 */
	public static GridButton getButton(int x, int y) {
		return board[x][y];
	}
	
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    
	    Image image = null;
	    
	    try {
	    	image = ImageIO.read(new File("./assets/board.png"));
	    }
	    catch (Exception ex) {
	    	System.out.println("error loading board image");
	    }

	    g.drawImage(image,0,0,this);
	}
}
