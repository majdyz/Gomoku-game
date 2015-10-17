package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controllers.GameController;
import Controllers.GraphicController;

/**
 * @author zamil.majdy
 * 
 * class for dropdown menu (new game menu)
 *
 */
public class GameMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	
	/**
	 * constructor
	 */
	public GameMenu() {

		JMenu gameMenu = new JMenu("New Game");

		JMenuItem botMenuItem = new JMenuItem("Play Against Bot (Good Guy Bot Beta 1.0)");
		botMenuItem.setActionCommand("Bot");

		JMenuItem humanMenuItem = new JMenuItem("Play Against Human");
		humanMenuItem.setActionCommand("Human");

		MenuItemListener menuItemListener = new MenuItemListener();

		botMenuItem.addActionListener(menuItemListener);
		humanMenuItem.addActionListener(menuItemListener);

		gameMenu.add(botMenuItem);
		gameMenu.add(humanMenuItem);

		add(gameMenu);

	}

	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Bot")) {
				GameController.usingBot(true);
			} else {
				GameController.usingBot(false);
			}

			GameController.play();
			GraphicController.reset();
		}
	}
}